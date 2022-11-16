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
    public ArrayList<String> returnUniqueIPs(){
        return uniqueIPs;
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
     public HashMap<String,Integer> returnAmountEachIPMap(){
         HashMap<String,Integer> amountEachIP = new HashMap<String,Integer>();
         for(LogEntry log : records){
             if(amountEachIP.containsKey(log.getIpAddress())){
                 amountEachIP.put(log.getIpAddress(),amountEachIP.get(log.getIpAddress())+1);
             }else{
                 amountEachIP.put(log.getIpAddress(),1);
             }
         }
         return amountEachIP;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> map){
         int maxAppear = 0;
         for(String key : map.keySet()){
             if(map.get(key) > maxAppear){
                 maxAppear = map.get(key);
             }
         }
         return maxAppear;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map, int maxAppear){
         ArrayList<String> iPs = new ArrayList<String>();
         for(String key : map.keySet()){
             if(map.get(key) == maxAppear){
                 iPs.add(key);
             }
         }
         return iPs;
     }
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> daysWithIPOccurred = new HashMap<String,ArrayList<String>>();
         //System.out.println(records.get(0).getAccessTime().toString());
         for(LogEntry log : records){
             String currIP = log.getIpAddress();
             String currDate = log.getAccessTime().toString().substring(4,10);
             if(daysWithIPOccurred.containsKey(currDate)){
                 daysWithIPOccurred.get(currDate).add(currIP);
             }else{
                 ArrayList<String> temp = new ArrayList<String>();
                 temp.add(currIP);
                 daysWithIPOccurred.put(currDate,temp);
             }
         }
         return daysWithIPOccurred;
     }
     public String returnMaxVisitDay(HashMap<String,ArrayList<String>> map){
         int maxVisits = 0;
         String retVal = "";
         for(String key : map.keySet()){
             if(map.get(key).size() > maxVisits){
                 maxVisits = map.get(key).size();
                 retVal = key;
             }
         }
         return retVal;
     }
     //uniqueIPVisitsOnDay
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String date, ArrayList<String> uniqueIPs){
         ArrayList<String> listIP = map.get(date);
         HashMap<String,Integer> appearsOfIPs = new HashMap<String,Integer>();
         int maxAppears = 0;
         for(String currIp : listIP){
             if(appearsOfIPs.containsKey(currIp)){
                 appearsOfIPs.put(currIp,appearsOfIPs.get(currIp)+1);
                 if (appearsOfIPs.get(currIp)>maxAppears) maxAppears = appearsOfIPs.get(currIp);
             }else{
                 appearsOfIPs.put(currIp,1);
                 if (appearsOfIPs.get(currIp)>maxAppears) maxAppears = appearsOfIPs.get(currIp);
             }
         }
         ArrayList<String> retVal = new ArrayList<String>();
         for(String temp : uniqueIPs){
             retVal.add(temp);
         }
         for(String ip : uniqueIPs){
             if(appearsOfIPs.get(ip) != maxAppears){
                 retVal.remove(ip);
             }
         }
         return retVal;
    }
}
