import edu.duke.*;
import java.util.*;

public class CodonCounter {
    private HashMap<String, Integer> dnaCodons;
    private String path;
    public CodonCounter(){
        dnaCodons = new HashMap<>();
        path = null;
    }
    public CodonCounter(String path){
        dnaCodons = new HashMap<>();
        this.path = path;
    }

    public void buildCodonMap(int start, String dnaStr){
        dnaCodons = new HashMap<>();
        String resDnaStr = dnaStr.substring(start);
        resDnaStr = resDnaStr.substring(0,resDnaStr.length()-(resDnaStr.length()%3));
        //System.out.println(resDnaStr);
        for(int ind = 0; ind < resDnaStr.length(); ind+=3){
            String currCodon = resDnaStr.substring(ind,ind+3);
            if(dnaCodons.containsKey(currCodon)){
                dnaCodons.put(currCodon,dnaCodons.get(currCodon)+1);
            }else{
                dnaCodons.put(currCodon,1);
            }
        }
        System.out.println(dnaCodons);
    }
    public String getCurrMostCommonCodon(){
        int maxVal = 0;
        String retVal = null;
        for(String temp : dnaCodons.keySet()){
            if(dnaCodons.get(temp) > maxVal){
                maxVal = dnaCodons.get(temp);
                retVal = temp;
            }
        }
        return retVal;
    }
    public void printCurrCodonCounts(int a,int b){
        System.out.println("All codons with frequency more or equal then " + a + " and less or equal then " + b + ":");
        for(String temp : dnaCodons.keySet()){
            if(dnaCodons.get(temp) >= a && dnaCodons.get(temp) <= b){
                System.out.println(temp + " " + dnaCodons.get(temp));
            }
        }
    }
    public void codonFileAnalizator(String codonStr, int more,int less){

        int frameNum = 0;

        String codonString;
        FileResource fr;
        if(codonStr == null) {
            if (path == null) {
                fr = new FileResource();
            } else {
                fr = new FileResource(path);
            }
            codonString = fr.asString();
        }else{
            codonString = codonStr;
        }
        codonString = codonString.toUpperCase().trim();
        System.out.println(codonString);

        while(frameNum < 3){
            System.out.println("\nFor start pos: "+ frameNum + "\nAll original codons: ");
            buildCodonMap(frameNum, codonString);
            System.out.println("Amount of original codons: "+ dnaCodons.size());
            System.out.println("The most common codon: "+ getCurrMostCommonCodon());
            printCurrCodonCounts(more,less);
            frameNum++;
        }
    }
}
