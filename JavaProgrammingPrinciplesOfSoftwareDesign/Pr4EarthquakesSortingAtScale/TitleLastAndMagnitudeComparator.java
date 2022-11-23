import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        //System.out.println("info1 блять - "+qe1.getInfo());
        String name1 = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(32)+1);
        //System.out.println(name1);
        //System.out.println("info2 блять - "+qe2.getInfo());
        String name2 = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(32)+1);
        //System.out.println(name2);
        int answer = name1.compareTo(name2);
        if(answer == 0){
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        return answer;
    }
}
