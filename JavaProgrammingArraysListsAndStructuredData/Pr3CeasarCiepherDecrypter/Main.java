import edu.duke.*;

public class Main {
    public static void main(String[] args) {
        FileResource resource = new FileResource("/home/kirill/IdeaProjects/course-3/pr2_OutputCounterOfLenthsMass/mysteryTwoKeysPractice.txt");

        String mainString = resource.asString();
        System.out.println(mainString);

        Decrypter count = new Decrypter();

        String[] stringsDifKeys = count.parseString(mainString);
        System.out.println(stringsDifKeys[0]);
        System.out.println(stringsDifKeys[1]);

        int key1 = count.returnKeyE(stringsDifKeys[0]);
        int key2 = count.returnKeyE(stringsDifKeys[1]);
        System.out.println(key1);
        System.out.println(key2);

        String shiftAlf1 = count.shiftAlf(key1);
        String shiftAlf2 = count.shiftAlf(key2);
        System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println(shiftAlf1);
        System.out.println(shiftAlf2);

        stringsDifKeys[0] = count.decrypter(stringsDifKeys[0],shiftAlf1);
        stringsDifKeys[1] = count.decrypter(stringsDifKeys[1],shiftAlf2);

        String resStrDecr = "";
        for(int i = 0; i < mainString.length()/2; i++){
           resStrDecr += stringsDifKeys[0].charAt(i);
           resStrDecr += stringsDifKeys[1].charAt(i);

        }
        System.out.println(resStrDecr);
    }
}