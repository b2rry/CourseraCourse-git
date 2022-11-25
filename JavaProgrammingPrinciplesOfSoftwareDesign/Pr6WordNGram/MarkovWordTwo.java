import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    private int indexOf(String[] words, String target, int start){
        for(int pos = start; pos<words.length; pos++){
            if(words[pos].equals(target)) return pos;
        }
        return -1;
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
            //System.out.print("//"+key1+"/"+key2+"//");
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1,String key2) {
        //setTraining("and and one and two three and and four and five");
        ArrayList<String> follows = new ArrayList<String>();
        int beginPos = 0;
        while(true){
            int foundPos = indexOf(myText,key1,beginPos);
            if(foundPos == -1) break;
            if(myText[foundPos+1].equals(key2)){//возможно исключение!
                follows.add(myText[foundPos+2]);
                beginPos = foundPos + 2;
            }else{
                beginPos++;
            }
        }
        return follows;
    }
}
