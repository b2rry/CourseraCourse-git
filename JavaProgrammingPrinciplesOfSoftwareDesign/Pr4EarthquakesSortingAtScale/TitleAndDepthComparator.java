import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int answer = qe1.getInfo().compareTo(qe2.getInfo());
        if(answer == 0){
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        }
        return answer;
    }
}
