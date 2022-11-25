import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int predictNum;
    
    public AbstractMarkovModel(int prNum) {
        myRandom = new Random();
        predictNum = prNum;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int prInd = 0;
        while(true) {
            int nextInd = myText.indexOf(key, prInd);
            if (nextInd != -1 && nextInd != myText.length()-key.length()) {
                follows.add(myText.substring(nextInd + key.length(), nextInd + key.length()+1));
            }else{
                break;
            }
            prInd = nextInd+1;
        }
        return follows;
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public String toString(){
        return this.getClass().getName() + " " +predictNum;
    }
}
