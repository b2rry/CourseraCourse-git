import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private HashMap<String, ArrayList<String>> mapFollows;

    public EfficientMarkovModel(int prNum) {
        super(prNum);
        mapFollows = new HashMap<String,ArrayList<String>>();
        //buildMap();//карта строится на тексте, который инициализируется на созданном объекте
    }

    public void setTraining(String s){
        myText = s.trim();
    }
    public void buildMap(){
        int length = myText.length();
        for(int ind = 0; ind < length-predictNum; ind++){
            String key = myText.substring(ind,ind+predictNum);
            if(!mapFollows.containsKey(key)){
                mapFollows.put(key, createFollowsList(key,ind));
            }
        }
        hashMapInfo(mapFollows);
    }
    public ArrayList<String> createFollowsList(String key,int prInd){
        ArrayList<String> follows = new ArrayList<String>();
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
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-predictNum);
        String appendStr = myText.substring(index,index+predictNum);
        String chAtInd;
        ArrayList<String> follows;
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
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = mapFollows.get(key);
        return follows;
    }
    public void hashMapInfo(HashMap<String,ArrayList<String>> in){
        System.out.println("Size of map: "+ in.size());
        int maxAmount = 0;
        String nkey ="";
        for(String key : in.keySet()){
            if(in.get(key).size() > maxAmount){
                maxAmount = in.get(key).size();
                nkey = key;
            }
        }
        System.out.println("size of largest arrayLisy in map: "+maxAmount + "/"+ nkey);
    }
}
