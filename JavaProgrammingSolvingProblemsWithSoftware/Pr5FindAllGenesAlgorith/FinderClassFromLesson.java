public class FinderClassFromLesson {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaStr.length();
    }
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp,tgaIndex);
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    public void printAllGenes(String dna){
        int counter = 1;
        int longerCounter = 0;
        int cgGrCounter = 0;
        int lengthOfLongestGene = 0;
        dna = dna.toUpperCase();
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(counter + ". " + currentGene);
            boolean fl = longerThen(currentGene);
            if(fl == true) longerCounter++;
            boolean fl2 = cgGreaterThen(currentGene);
            if(fl2 == true) cgGrCounter++;
            counter++;
            if(lengthOfLongestGene < currentGene.length()) lengthOfLongestGene = currentGene.length();
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
        System.out.println("\nLength of longest Gene is - " + lengthOfLongestGene);
        System.out.println("Amount > 60 - " + longerCounter);
        System.out.println("Amount greater then 0.35 CG genes - " + cgGrCounter);
        System.out.println("Amount CTG gene in this dna - " + amountCTG(dna));

    }
    public boolean longerThen(String dna){
        if(dna.length() > 60){
            System.out.println("longer then 60");
            return true;
        }
        else{
            System.out.println("shorter then 60");
            return false;
        }
    }
    public boolean cgGreaterThen(String dna){
        int ind = 0;
        int count = 0;
        int l = dna.length();
        do{
            char letter = dna.charAt(ind);
            if(letter == 'C' || letter == 'G'){
                count++;
            }
            ind++;
        }while(ind < l);
        System.out.println((double)count/l);
        if(((double)count/l) > 0.35){
            System.out.println("Greater Then 0.35");
            return true;
        }
        else{
            System.out.println("Lower Then 0.35");
            return false;
        }
    }

    public int amountCTG(String dna){
        int counter = 0;
        int start = 0;
        while (true) {
            int pos = dna.indexOf("CTG", start);
            if (pos == -1) {
                break;
            }
            counter += 1;
            start = pos + 3;
        }
        return counter;
    }
}
