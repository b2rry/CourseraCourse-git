import edu.duke.URLResource;

public class Part4 {
    public void searchYoutubeLinks(){
        URLResource res = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int numOfLink = 1;
        for (String line : res.lines()) {
            //System.out.println(line);
            String bufLine = line.toLowerCase();
            int startPos = bufLine.indexOf("youtube.com");
            if(startPos > 0){
                do{
                    startPos--;
                }while(bufLine.charAt(startPos) != '\"');
                int startInd = startPos;
                do{
                    startPos++;
                }while(bufLine.charAt(startPos) != '\"');
                int endInd = startPos;
                System.out.println(numOfLink + ". " + line.substring(startInd, endInd+1));
                numOfLink++;
            }
        }
    }
}