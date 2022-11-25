import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{

    public MarkovModel(int prNum) {
        super(prNum);
    }


    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-predictNum);
        String appendStr = myText.substring(index,index+predictNum);
        String chAtInd ="";
        ArrayList<String> follows = null;
        sb.append(appendStr);
        for(int k=0; k < numChars; k++){
            follows = getFollows(appendStr);
            index = myRandom.nextInt(follows.size());
            chAtInd = follows.get(index);
            appendStr = appendStr.substring(1) + chAtInd;//хз
            sb.append(appendStr.substring(predictNum-1));
        }

        return sb.toString();
    }
}
