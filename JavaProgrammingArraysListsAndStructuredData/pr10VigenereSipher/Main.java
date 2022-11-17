import edu.duke.FileResource;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.testTryKeyLengthMethod();
        main.testBreakVigenereMethod();
    }
    //*************************************************
    public void testCaesarCipher(){
        CaesarCipher obj1 = new CaesarCipher(10);
        String test1 = obj1.encrypt("Hello, my name is Kirill.");
        System.out.println(test1);
        String test2 = obj1.decrypt(test1);
        System.out.println(test2);
    }
    public void testCaesarCracker(){
        CaesarCracker obj1 = new CaesarCracker('l');
        CaesarCipher obj2 = new CaesarCipher(10);
        String test1 = obj2.encrypt("Hello, my name is Kirill.");
        String test2 = obj1.decrypt(test1);
        System.out.println(test1);
        System.out.println(test2);
    }
    public void testVigenereCipher(){
        int[] key = {1,2,3,4};
        VigenereCipher obj1 = new VigenereCipher(key);
        String test1 = obj1.encrypt("Hello, my name is Kirill.");
        System.out.println(test1);
        String test2 = obj1.decrypt(test1);
        System.out.println(test2);
    }
    public void testSliceStringMethod(){
        VigenereBreaker obj1 = new VigenereBreaker();
        String test1 = obj1.sliceString("abcdefghijklm",2,5);
        System.out.println(test1);
    }
    public void testTryKeyLengthMethod(){
        VigenereBreaker obj1 = new VigenereBreaker();
        FileResource fr = new FileResource();
        String testStr = fr.asString();
        int[] test1 = obj1.tryKeyLength(testStr, 4, 'e');
        for(int i = 0; i<test1.length; i++) {
            System.out.println(test1[i]);
        }
    }
    public void testBreakVigenereMethod(){
        VigenereBreaker obj1 = new VigenereBreaker();
        FileResource fr = new FileResource();
        String test1 = obj1.breakVigenere(fr.asString());
        System.out.println(test1);
    }
}