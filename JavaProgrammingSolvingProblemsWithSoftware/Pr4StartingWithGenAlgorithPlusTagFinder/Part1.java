import edu.duke.*;
import java.io.*;
public class Part1 {
    public String findSimpleGene(String dna){

        int start = dna.indexOf("atg");
        if (start == -1) {
            System.out.println("error 1!");
            return "";
        }
        int stop = dna.indexOf("taa", start+3);
        if (stop == -1) {
            System.out.println("error 2!");
            return "";
        }
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            System.out.println("error 3!");
            return "";
        }
    }

    public void testSimpleGene() {
        String g1 = "cccatggggtttaaataataataggagagagagagagagttt";
        String g2 = "atggggtttaaataataatag";
        String g3 = "atgcctag";
        String g4 = "";
        //String g5 = "ATGCCCTAG";
        //String g6 = "ATGCCCTAG";
        String g7 = "actatgcccccccccgggttttaacgcgcg";
        String result = findSimpleGene(g3);
        System.out.println("Gene: "+ result);
    }
}
