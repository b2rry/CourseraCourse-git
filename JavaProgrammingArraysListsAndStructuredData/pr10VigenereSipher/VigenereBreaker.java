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

    public String breakVigenere (String encryptedMassage) {
        int keyLength = 4; // нужно узнавать
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
}
