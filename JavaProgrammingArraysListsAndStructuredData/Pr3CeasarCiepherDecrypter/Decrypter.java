import edu.duke.*;

public class Decrypter {

    public String decrypter(String inputStr, String shiftAlf){
        StringBuilder decryptedStr = new StringBuilder(inputStr);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean lowFlag = false;
        for(int i = 0; i < decryptedStr.length(); i++) {
            char currChar = decryptedStr.charAt(i);
            if(currChar >= 97 && currChar <= 122){
                lowFlag = true;
                currChar -= 32;
            }
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftAlf.charAt(idx);
                if(lowFlag){
                    newChar += 32;
                }
                decryptedStr.setCharAt(i, newChar);
                lowFlag = false;
            }
        }
        return decryptedStr.toString();
    }
    public String shiftAlf(int key){
        String alf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftA = alf.substring(key) + alf.substring(0,key);
        return shiftA;
    }
    public int returnKeyE(String str){
        int key = 0;
        int[] mass = new int[26];
        String alf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        str = str.toUpperCase();
        for(int i = 0; i< str.length(); i++){
            if(str.charAt(i) >= 65 && str.charAt(i) <= 90){
                mass[alf.indexOf(str.charAt(i))]++;
            }
        }
        int localMax = 0;
        for(int i = 0; i < mass.length; i++) {
            if (mass[i] > localMax) {
                localMax = mass[i];
                key = i;
            }
        }
        if(key > 4){return 30-key;}
        else{return 4-key;}
    }
    public String[] parseString(String str){
        String[] parseStringsMass = new String[2];
        parseStringsMass[0] = "";
        parseStringsMass[1] = "";
        for(int ind = 0; ind < str.length(); ind++) {
            if (ind % 2 == 0) {
                parseStringsMass[0] += str.charAt(ind);
            } else {
                parseStringsMass[1] += str.charAt(ind);
            }
        }
        return parseStringsMass;
    }
}
