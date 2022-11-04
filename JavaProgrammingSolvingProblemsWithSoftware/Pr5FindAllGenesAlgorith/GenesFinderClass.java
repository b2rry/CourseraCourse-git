public class GenesFinderClass {

    public void outputAllGenesFromDna(String dna){ //and amount of it
        int startPos = 0;
        int geneCounter = 0;
        boolean noGeneFlag = false;
        while(!noGeneFlag){
            String newGene = findNextGene(dna,startPos);
            startPos = dna.indexOf(newGene)+newGene.length();//предполагается что одинаковых генов в цепочке нету
            if(newGene.isEmpty()) noGeneFlag = true;
            else {
                System.out.println((geneCounter+1) + " gene in this dna: " + newGene);
                geneCounter++;
            }
        }
        System.out.println("Amount of genes in this dna - " + geneCounter);
    }

    public String findNextGene(String dna, int startPos){
        boolean exitFlag = false;
        boolean findStartGeneFlag = true;
        int localStartPos = -1;
        int localEndPos = -1;
        String foundGene = "";
        int dnaLen = dna.length();
        do{
            char currChar = dna.charAt(startPos);
            while(findStartGeneFlag && startPos < dnaLen - 3){
                currChar = dna.charAt(startPos);
                if(currChar == 'A'){
                    startPos++;
                    currChar = dna.charAt(startPos);
                    if(currChar == 'T'){
                        startPos++;
                        currChar = dna.charAt(startPos);
                        if(currChar == 'G'){
                            startPos++;
                            currChar = dna.charAt(startPos);
                            findStartGeneFlag = false;
                            localStartPos = startPos - 3;
                        }else startPos -= 2;
                    }else startPos--;
                }
                if(findStartGeneFlag == true) startPos++;
            }
            if(currChar == 'T' && startPos <= dnaLen - 3){
                startPos++;
                currChar = dna.charAt(startPos);
                if(currChar == 'A'){
                    startPos++;
                    currChar = dna.charAt(startPos);
                    if(currChar == 'A'){
                        //найден
                        startPos++;
                        localEndPos = startPos;
                        exitFlag = true;
                    }else if(currChar == 'G'){
                        //найден
                        startPos++;
                        localEndPos = startPos;
                        exitFlag = true;
                    }else startPos -= 2;
                }else if(currChar == 'G'){
                    startPos++;
                    currChar = dna.charAt(startPos);
                    if(currChar == 'A'){
                        //найден
                        startPos++;
                        localEndPos = startPos;
                        exitFlag = true;
                    }else startPos -= 2;
                }else startPos--;
            }
            if(startPos > dnaLen - 3) exitFlag = true;
            startPos += 3;
            if(startPos >= dnaLen) exitFlag = true;
        }while(!exitFlag);
        if(localStartPos >= 0 && localEndPos > 0){
            if(localEndPos == dnaLen) foundGene = dna.substring(localStartPos);
            else foundGene = dna.substring(localStartPos,localEndPos);
        }
        return foundGene;
    }

}//ATGCCCCCCTAA