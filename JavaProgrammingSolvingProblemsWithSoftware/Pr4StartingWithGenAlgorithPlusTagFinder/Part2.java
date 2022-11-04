public class Part2 {
    public String findSimpleGene(String dna, String startCodone, String stopCodone){
        boolean upperCaseFlag = false;
        if((int)dna.charAt(0) >= 65 && dna.charAt(0) <= 90){
            upperCaseFlag = true;
            dna = dna.toLowerCase();
        }
        int start = dna.indexOf(startCodone);
        if (start == -1) {
            System.out.println("error 1!");
            return "";
        }
        int stop = dna.indexOf(stopCodone, start+3);
        if (stop == -1) {
            System.out.println("error 2!");
            return "";
        }
        if ((stop - start) % 3 == 0) {
            if(upperCaseFlag) return dna.substring(start, stop+3).toUpperCase();
            else return dna.substring(start, stop+3);
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
        String g5 = "ATGCCCTAA";
        String g6 = "ATGCCCTAG";
        String g7 = "actatgcccccccccgggttttaacgcgcg";
        String result = findSimpleGene(g2, "atg", "taa");
        System.out.println("Enter: "+ g5);
        System.out.println("Gene: "+ result);
    }
}
