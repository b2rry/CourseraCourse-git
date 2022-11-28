import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer,ArrayList<String>> map;

    public EfficientMarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
        map = new HashMap<Integer,ArrayList<String>>();
    }
    public int returnOrder(){
        return myOrder;
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
        System.out.println("Start creating map");
        buildMap();
        System.out.println("End creating map");
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
                if(endFlag){
                    return pos;
                }
            }
            endFlag = true;
        }
        return -1;
    }
    public ArrayList<String> getFollows(WordGram key){
        //возможен ли вариант несуществующего набора рандомных слов...?
        return map.get(key.hashCode());
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
    public void buildMap(){
        int length = myText.length;
        for(int ind = 0; ind < length-myOrder; ind++){
            WordGram gram = new WordGram(myText,ind,myOrder);
            int key = gram.hashCode();
            if(!map.containsKey(key)){
                map.put(key, getFollowsList(gram));
            }
        }
        hashMapInfo(map);
    }
    private ArrayList<String> getFollowsList(WordGram kGram) {
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
    public void hashMapInfo(HashMap<Integer,ArrayList<String>> in){
        System.out.println("INFO:");
        System.out.println("Size of map: "+ in.size());
        int maxAmount = 0;
        for(int key : in.keySet()){
            if(map.get(key).size() > maxAmount){
                maxAmount = map.get(key).size();
            }
        }
        System.out.println("Maximum size of list in map: "+maxAmount);
    }
}
