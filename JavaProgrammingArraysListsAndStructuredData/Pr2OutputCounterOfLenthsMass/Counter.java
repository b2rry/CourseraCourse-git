import edu.duke.*;

public class Counter {
    public int[] fillMass(int[] mass, FileResource resource){
        int wordLen = 0;
        for(String word : resource.words()){
            word = word.toLowerCase();
            if(!isLetter(word.charAt(0))){
                word = word.substring(1);
            }
            if(!isLetter(word.charAt(word.length()-1))){
                word = word.substring(0,word.length()-1);
            }
            wordLen = word.length();
            if(mass.length <= wordLen){
                mass[29]++;
            }else{
                mass[wordLen]++;
            }
        }
        return mass;
    }
    public boolean isLetter(char ch){
        if(ch >= 97 && ch <= 122){
            return true;
        }else{
            return false;
        }
    }

    public int indexOfMax(int[] mass){
        int maxInd = 0;
        for(int i = 0; i < mass.length; i++){
            if(mass[i] > mass[maxInd]){maxInd = i;}
        }
        return maxInd;
    }
}
