import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {

    public int maxStrSizeInMass(String[] mass){
        int maxLength = 0;
        for(int i = 0; i < mass.length; i++){
            if(mass[i].length() > maxLength) maxLength = mass[i].length();
        }
        return maxLength;
    }
    public String[] initializeStringMass(String[] mass){
        for(int i = 0; i < mass.length; i++){
            mass[i] = "";
        }
        return mass;
    }
    public int maxNumInIntList(ArrayList<Integer> list){
        int maxNum = 0;
        for(int i : list){
            if(i > maxNum) maxNum = i;
        }
        return maxNum;
    }
    public String sliceString(String encryptedMessage, int whichSlice, int totalSlices) { //abcdefghijklmnop
        String retMessage = "";
        for(int ind = whichSlice; ind < encryptedMessage.length(); ind += totalSlices){
            retMessage = retMessage + encryptedMessage.charAt(ind);
        }
        return retMessage;
    }

    public int[] tryKeyLength(String encryptedMassage, int keyLength, char mostCommon) {
        int[] key = new int[keyLength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        for(int numSlice = 0; numSlice < keyLength; numSlice++){
            String currFragmentedStr = sliceString(encryptedMassage,numSlice,keyLength);
            key[numSlice] = cracker.getKey(currFragmentedStr);
        }
        return key;
    }
    public HashSet<String> returnDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String line : fr.lines()){
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    public int countWords(String message, HashSet<String> dictionary){
        message = message.toLowerCase();
        String[] words = message.split("\\W+");
        int counter = 0;
        int repeted = 0;
        for(int wordNum = 0; wordNum < words.length; wordNum++){
            for(String wordFromDictionary : dictionary) {
                if (wordFromDictionary.equals(words[wordNum])) {
                    counter++;
                    break;
                }
            }
            repeted++;
            if(repeted >= 40) break;//количество проверяемых на действительность первых слов для расшифрованного сообщения
        }
        return counter;
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> lettersAmountsInDictionary = new HashMap<Character,Integer>();
        for(String word : dictionary){
            for(char letter : word.toCharArray()){
                if(lettersAmountsInDictionary.containsKey(letter)){
                    lettersAmountsInDictionary.put(letter,lettersAmountsInDictionary.get(letter)+1);
                }else{
                    lettersAmountsInDictionary.put(letter,1);
                }
            }
        }
        int maxOccurrences = 0;
        char mostCommon = 'Ж';
        for(char letter : lettersAmountsInDictionary.keySet()){
            if(lettersAmountsInDictionary.get(letter) > maxOccurrences){
                maxOccurrences = lettersAmountsInDictionary.get(letter);
                mostCommon = letter;
            }
        }
        //System.out.println("Found most common letter in dictionary: "+mostCommon);
        //System.out.println(lettersAmountsInDictionary);
        return mostCommon;
    }
    public String decryptWithKeyLength(String encryptedMassage, int keyLength, char mostCommon){
        String[] fragmentedStringsMass = new String[keyLength];
        fragmentedStringsMass = initializeStringMass(fragmentedStringsMass);
        int[] key = tryKeyLength(encryptedMassage, keyLength, mostCommon);
        for(int numSlice = 0; numSlice < keyLength; numSlice++){
            String currFragmentedStr = sliceString(encryptedMassage,numSlice,keyLength);
            CaesarCipher runCipher = new CaesarCipher(key[numSlice]);
            String decryptedCurrFragmentedStr = runCipher.decrypt(currFragmentedStr);
            fragmentedStringsMass[numSlice] += decryptedCurrFragmentedStr;
        }
        StringBuilder decryptedMessage = new StringBuilder("");
        int maxLength = maxStrSizeInMass(fragmentedStringsMass);
        for(int charInd = 0; charInd < maxLength; charInd++) {
            for (int numSlice = 0; numSlice < keyLength; numSlice++) {
                if(fragmentedStringsMass[numSlice].length() > charInd) {
                    decryptedMessage.append(fragmentedStringsMass[numSlice].charAt(charInd));
                }
            }
        }
        return decryptedMessage.toString();
    }
    public String breakVigenere (String encryptedMassage, int maxKeyAmountValue, ArrayList<HashMap> dictionariesAndLetters) {
        HashMap<String,ArrayList<Integer>> amountRealWordsInDecMessagesOfLanguages = new HashMap<String,ArrayList<Integer>>();
        HashMap<String,HashSet<String>> dictionarIES = dictionariesAndLetters.get(0);
        HashMap<String,Character> commonLettERS = dictionariesAndLetters.get(1);
        for(String language : commonLettERS.keySet()){
            amountRealWordsInDecMessagesOfLanguages.put(language,new ArrayList<>(101));
            for(int ind = 0; ind < 101; ind++) {
                amountRealWordsInDecMessagesOfLanguages.get(language).add(ind, 0);
            }
        }
        //ArrayList<Integer> amountRealWordsInDecMessages = new ArrayList<>(101);
        for(int currKeyLength = 1; currKeyLength <= maxKeyAmountValue; currKeyLength++){
            System.out.print("Processed languages in key "+currKeyLength+": ");
            for(String language : dictionarIES.keySet()) {
                String currDecryptedMassage = decryptWithKeyLength(encryptedMassage, currKeyLength, commonLettERS.get(language));
                //amountRealWordsInDecMessages.add(currKeyLength, countWords(currDecryptedMassage, dictionary));
                int amount = countWords(currDecryptedMassage, dictionarIES.get(language));
                ArrayList<Integer> buf = amountRealWordsInDecMessagesOfLanguages.get(language);
                buf.add(currKeyLength,amount);
                amountRealWordsInDecMessagesOfLanguages.put(language, buf);
                System.out.print(language+" ");
            }
            System.out.println();
        }
        System.out.println("\n");
        int maxAmountOfWordsInLanguage = 0;
        int maxAmountOfWords = 0;
        int foundKeyInLanguage = 0;
        int foundKey = 0;
        String foundLanguage = "";

        for(String language : dictionarIES.keySet()) {
            ArrayList<Integer> buf = amountRealWordsInDecMessagesOfLanguages.get(language);
            maxAmountOfWordsInLanguage = maxNumInIntList(buf);
            int size = buf.size();
            for (int i = 0; i < size; i++) {
                if (buf.get(i) == maxAmountOfWordsInLanguage) {
                    System.out.println("Language: "+language+" Found key in it: " + i +" Maximum real words(from 40 first): "+maxAmountOfWordsInLanguage);
                    foundKeyInLanguage = i;
                    break;
                }
            }
            if(maxAmountOfWordsInLanguage > maxAmountOfWords){
                maxAmountOfWords = maxAmountOfWordsInLanguage;
                foundKey = foundKeyInLanguage;
                foundLanguage = language;
            }
        }
        System.out.println("\nFound language: "+foundLanguage);
        System.out.println("Found key: "+foundKey);
        System.out.println("Maximum real words in array (from 40 first): "+ maxAmountOfWords+"\n");
        String decryptedMassage = decryptWithKeyLength(encryptedMassage,foundKey,commonLettERS.get(foundLanguage));
        return decryptedMassage;
    }
    public ArrayList<HashMap> dictionariesSettings(){
        System.out.println("Select all dictionaries");
        DirectoryResource dr = new DirectoryResource();
        HashMap<String,HashSet<String>> dictionarIES = new HashMap<String,HashSet<String>>();
        HashMap<String,Character> commonLettERS = new HashMap<String,Character>();
        ArrayList<HashMap> dictionariesAndLetters = new ArrayList<HashMap>();
        for(File f : dr.selectedFiles()){
            String fName = f.getName();
            FileResource fr = new FileResource(f);
            HashSet<String> dictionary = returnDictionary(fr);
            dictionarIES.put(fName,dictionary);
            char mostCommon = mostCommonCharIn(dictionary);
            commonLettERS.put(fName,mostCommon);
            System.out.println("Found most common letter in " + fName + " language: " + mostCommon);
        }
        dictionariesAndLetters.add(dictionarIES);//0
        dictionariesAndLetters.add(commonLettERS);//1
        return dictionariesAndLetters;
    }
}
