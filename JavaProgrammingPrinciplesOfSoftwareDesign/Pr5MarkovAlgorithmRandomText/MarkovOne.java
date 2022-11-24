import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String appendStr = myText.substring(index,index+4);
        String chAtInd ="";
        ArrayList<String> follows = null;
        sb.append(appendStr);
        for(int k=0; k < numChars; k++){
            follows = getFollows(appendStr);
            index = myRandom.nextInt(follows.size());
            chAtInd = follows.get(index);
            appendStr = appendStr.substring(1) + chAtInd;//хз
            sb.append(appendStr.substring(3));
        }

        return sb.toString();
    }
    public ArrayList<String> getFollows(String key){
        FileResource fr = new FileResource();
        myText = fr.asString();
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
}
