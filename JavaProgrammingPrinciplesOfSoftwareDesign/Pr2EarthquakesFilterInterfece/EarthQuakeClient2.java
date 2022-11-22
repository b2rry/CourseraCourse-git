import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    public ArrayList<QuakeEntry> create(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        return list;
    }
    public void quakesWithFiltersMagDep() {
        ArrayList<QuakeEntry> list = create();
        Filter mag = new MagnitudeFilter(4,5);
        Filter dep = new DepthFilter(-35000,-12000);
        ArrayList<QuakeEntry> answer  = filter(list, mag);
       answer = filter(answer, dep);
        for (QuakeEntry qe: answer) {
            System.out.println(qe);
        }

        System.out.println("found "+answer.size()+" quakes");
    }
    public void quakesWithFiltersDistPhrase() {
        ArrayList<QuakeEntry> list = create();

        Location tokio =  new Location(35.42, 139.43);
        Filter dist = new DistanceFilter(tokio,10000000);
        Filter phrase = new PhraseFilter("end","Japan");
        ArrayList<QuakeEntry> answer  = filter(list, dist);
        answer = filter(answer, phrase);
        for (QuakeEntry qe: answer) {
            System.out.println(qe);
        }

        System.out.println("found "+answer.size()+" quakes");
    }
    public void quakesWithAllFilters() {
        ArrayList<QuakeEntry> list = create();

        Location tokio =  new Location(35.42, 139.43);
        Location tulsa =  new Location(36.1314, -95.9372);
        Location denver =  new Location(39.7392, -104.9903);
        Location billund =  new Location(55.7308, 9.1153);

        Filter dist = new DistanceFilter(denver,1000000);
        Filter mag = new MagnitudeFilter(0,5);
        Filter dep = new DepthFilter(-4000,-2000);
        Filter phrase = new PhraseFilter("any","a");

        MatchAllFilters filterA = new MatchAllFilters();
        filterA.addFilter(mag);
        filterA.addFilter(dep);
        filterA.addFilter(phrase);
        filterA.addFilter(dist);

        ArrayList<QuakeEntry> answer  = filter(list, filterA);
        for (QuakeEntry qe: answer) {
                System.out.println(qe);
            }
        System.out.println("found "+answer.size()+" quakes");
    }
}
