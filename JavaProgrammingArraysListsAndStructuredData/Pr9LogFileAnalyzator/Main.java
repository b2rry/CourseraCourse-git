import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tester start = new Tester();
        //Main a = new Main();
        //a.firstPart(start);

        start.printAll();
        start.testAmountEachIP();
    }
    public void firstPart(Tester start){
        //start.printAll();
        System.out.println("\n");
        start.testUniqueIP();
        System.out.println("\n");
        //start.printAllHigherThanNum(400);
        System.out.println("\n");
        ArrayList<String> list = start.uniqueIPVisitsOnDay("Mar 18");
        for(String str : list){
            System.out.println(str);
        }
        System.out.println("\n");
        int uniqueIpInRange = start.countUniqueIPsInRange(200,299);
        System.out.println("Amount of unique IPs in your range: "+ uniqueIpInRange);
    }
}