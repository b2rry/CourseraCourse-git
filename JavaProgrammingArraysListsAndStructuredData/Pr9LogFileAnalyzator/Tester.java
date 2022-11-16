import java.util.*;

public class Tester
{

    //private LogAnalyzer analyzer;
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public LogAnalyzer testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        return analyzer;
    }
    public void printAll(){
        LogAnalyzer analyzer = testLogAnalyzer();
        for( LogEntry log : analyzer.returnList()){
            System.out.println(log);
        }
    }
    public void testUniqueIP(){
        LogAnalyzer analyzer = testLogAnalyzer();
        int ips = analyzer.countUniqueIPs();
        System.out.println("Amount of unique IPs: "+ips);
    }

    public void printAllHigherThanNum(int num){
        System.out.println("All logs with status code higher than "+ num);
        LogAnalyzer analyzer = testLogAnalyzer();
        for( LogEntry log : analyzer.returnList()){
            if(log.getStatusCode() > num){
                System.out.println(log);
            }
        }
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        System.out.println("All unique IPs at date: "+ someday);
        LogAnalyzer analyzer = testLogAnalyzer();
        analyzer.countUniqueIPs();
        ArrayList<String> uniqueIPs = analyzer.returnUniqueIPs();
        ArrayList<String> uniqueIPsInDay = new ArrayList<String>();
        for( LogEntry log : analyzer.returnList()){
            if(uniqueIPs.contains(log.getIpAddress())) {
                if (log.getAccessTime().toString().indexOf(someday) != -1) {
                    uniqueIPsInDay.add(log.getIpAddress());
                    uniqueIPs.remove(log.getIpAddress());
                }
            }
        }
        return uniqueIPsInDay;
    }
    public int countUniqueIPsInRange(int more,int less){
        LogAnalyzer analyzer = testLogAnalyzer();
        analyzer.countUniqueIPs();
        ArrayList<String> uniqueIPs = analyzer.returnUniqueIPs();
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();//можно было создать счетчик, но вероятно список может понадобиться
        for( LogEntry log : analyzer.returnList()){
            if(uniqueIPs.contains(log.getIpAddress())) {
                if (log.getStatusCode() >= more && log.getStatusCode() <= less) {
                    uniqueIPsInRange.add(log.getIpAddress());
                    uniqueIPs.remove(log.getIpAddress());
                }
            }
        }
        return uniqueIPsInRange.size();//соответственно можно было передать счетчик
    }
}
