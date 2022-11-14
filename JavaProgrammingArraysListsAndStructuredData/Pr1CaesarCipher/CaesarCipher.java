import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        boolean lowFlag = false;
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(currChar >= 97 && currChar <= 122){
                lowFlag = true;
                currChar -= 32;
            }
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(lowFlag){
                    newChar += 32;
                }
                encrypted.setCharAt(i, newChar);
                lowFlag = false;
            }
        }
        return encrypted.toString();
    }

    public String encryptX2(String input, int key, int key2) {
        String halfEncrypted = encrypt(input, key);
        return encrypt(halfEncrypted, key2);
    }

    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public void testEnX2() {
        int key = 8;
        int key2 = 21;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptX2(message, key, key2);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 52-key-key2);
        System.out.println(decrypted);
    }
}

