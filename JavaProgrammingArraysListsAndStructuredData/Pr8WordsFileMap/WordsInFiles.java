import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> words;
    private String path;

    public WordsInFiles(){
        words = new HashMap<>();
        path = null;
    }
    public WordsInFiles(String path){
        words = new HashMap<>();
        this.path = path;
    }

    private void addWordsFromFile(File f){
        String fName = f.getName();
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(words.containsKey(word)){
                ArrayList<String> buf = words.get(word);
                if(!(buf.contains(fName))){//условие для исключения записи одного и того же имени файла повторно
                    words.get(word).add(f.getName());
                }
            }else{
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(f.getName());
                words.put(word,temp);
            }
        }
    }
    public void buildWordFileMap(){
        words = new HashMap<>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
//        for(String str : words.keySet()){
//            System.out.println(str + " : " + words.get(str).toString());
//        }
    }
    public void maxNumber(){
        int maxN = 0;
        for(String str : words.keySet()){
            //System.out.println(str + " : " + words.get(str).toString());
            if(words.get(str).size() > maxN) maxN = words.get(str).size();
        }
        System.out.println("Max amount of files with one any word: " + maxN);
    }
    public void wordsInNumFiles(int a){
        ArrayList<String> retWords = new ArrayList<>();
        for(String str : words.keySet()){
            //System.out.println(str + " : " + words.get(str).toString());
            if(words.get(str).size() == a) retWords.add(str);
        }

        System.out.println("Words that appears in "+a+" different files:\n"+retWords+"\n"+retWords.size());
    }
    public void printFilesIn(String inputWord){
        ArrayList<String> retFiles = words.get(inputWord);
        System.out.println("Files where appears the word - "+inputWord+ ":");
        for(String outWord : retFiles){
            System.out.println(outWord);
        }
    }
}
