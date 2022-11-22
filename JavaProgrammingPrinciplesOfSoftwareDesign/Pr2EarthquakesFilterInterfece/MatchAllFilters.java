import java.util.ArrayList;

public class MatchAllFilters implements Filter{
    private ArrayList<Filter> filters;

    public MatchAllFilters(){
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        filters.add(f);
    }
    public boolean satisfies(QuakeEntry qe) {
        //System.out.println("For "+filters.size()+" filters");
        boolean satisfyFlag = true;
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                satisfyFlag = false;
                break;
            }
        }
        return satisfyFlag;
    }
}
