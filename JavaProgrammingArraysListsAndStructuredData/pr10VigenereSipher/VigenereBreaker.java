import java.util.*;
import edu.duke.*;

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
    public int maxNumInIntMass(int[] mass){
        int maxNum = 0;
        for(int i = 0; i < mass.length; i++){
            if(mass[i] > maxNum) maxNum = mass[i];
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
            if(repeted == 20) break;
        }
        return counter;
    }
    public String decryptWithKeyLength(String encryptedMassage, int keyLength){
        char mostCommon = 'e'; // нужно узнавать

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
    public String breakVigenere (String encryptedMassage, HashSet<String> dictionary, int maxKeyAmountValue) {
        int[] amountRealWordsInDecMessages = new int[101];
        for(int currKeyLength = 1; currKeyLength <= maxKeyAmountValue; currKeyLength++){
            String currDecryptedMassage = decryptWithKeyLength(encryptedMassage,currKeyLength);
            amountRealWordsInDecMessages[currKeyLength] = countWords(currDecryptedMassage, dictionary);
            System.out.print("1 ");
        }
        int maxAmountOfWords = maxNumInIntMass(amountRealWordsInDecMessages);
        int foundKey = 0;
        for( int i = 0; i < amountRealWordsInDecMessages.length; i++){
            if(amountRealWordsInDecMessages[i] == maxAmountOfWords){
                System.out.println("Found key length: " + i);
                foundKey = i;
                break;
            }
        }
        System.out.println("Maximum real words in array (from 20 first): "+ maxAmountOfWords+"\n");
        String decryptedMassage = decryptWithKeyLength(encryptedMassage,foundKey);
        return decryptedMassage;
    }
}
