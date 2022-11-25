import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel{

    public MarkovOne() {
        super(1);
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
}
