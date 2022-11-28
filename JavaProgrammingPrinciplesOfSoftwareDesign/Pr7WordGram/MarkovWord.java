import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
    }
    public int returnOrder(){
        return myOrder;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    private int indexOf(String[] words, WordGram target, int start){
        boolean endFlag = true;
        for(int pos = start; pos<words.length-target.length(); pos++){
            if(words[pos].equals(target.wordAt(0))){
                for(int nextWInd = 1; nextWInd < target.length(); nextWInd++){
                    if(!words[pos+nextWInd].equals(target.wordAt(nextWInd))){
                        endFlag = false;
                        break;
                    }
                }
                if(endFlag == true){
                    return pos;
                }
            }
            endFlag = true;
        }
        return -1;
    }
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int beginPos = 0;
        while(true){
            int foundPos = indexOf(myText,kGram,beginPos);
            if(foundPos == -1) break;
            follows.add(myText[foundPos+kGram.length()]);
            beginPos = foundPos+kGram.length();
        }
        return follows;
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
