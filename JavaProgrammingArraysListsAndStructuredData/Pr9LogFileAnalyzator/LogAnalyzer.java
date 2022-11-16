import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private String path = "/home/kirill/IdeaProjects/course-3/pr9_LogEntry/";
     private ArrayList<String> uniqueIPs;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(path + filename);
         for(String line : fr.lines()){
             //WebLogParser buf = new WebLogParser();
             //LogEntry log = buf.parseEntry(line);
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public ArrayList<LogEntry> returnList(){
         return records;
     }

     public int countUniqueIPs() {
        uniqueIPs = new ArrayList<String>();
         int uniqueIpCounter = 0;
         for(LogEntry log : records){
             String currIp = log.getIpAddress();
             if(!(uniqueIPs.contains(currIp))){
                 uniqueIPs.add(currIp);
                 uniqueIpCounter++;
             }
         }
         return uniqueIpCounter;
     }
     public ArrayList<String> returnUniqueIPs(){
         return uniqueIPs;
     }
}
