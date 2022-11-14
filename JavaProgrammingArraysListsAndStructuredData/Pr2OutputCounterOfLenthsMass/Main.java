import edu.duke.*;

public class Main {
    public static void main(String[] args) {
        int[] lengthCounters = new int[30];
        FileResource resource = new FileResource("/home/kirill/IdeaProjects/course-3/pr2_OutputCounterOfLenthsMass/lotsOfWords.txt");
        Counter count = new Counter();
        lengthCounters = count.fillMass(lengthCounters, resource);

        for(int i = 0; i < lengthCounters.length; i++){
            System.out.println(i + " - " + lengthCounters[i]);
        }
        System.out.println("Amount of words with " + count.indexOfMax(lengthCounters) + " letters is the most often length in this file");
    }
}