import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry line : quakeData){
            if (line.getMagnitude() > magMin) answer.add(line);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry line : quakeData){
            double currDist = from.distanceTo(line.getLocation());
            if (currDist < distMax) answer.add(line);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double depthMin, double depthMax) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry line : quakeData){
            double currDepth = line.getDepth();
            if (currDepth < depthMax && currDepth > depthMin) answer.add(line);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where,String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry line : quakeData){
            if(where.equals("start")){
                if(line.getInfo().indexOf(phrase) != -1) answer.add(line);
            }else if(where.equals("end")){
                if(line.getInfo().indexOf(phrase) != -1) answer.add(line);
            } else if (where.equals("any")) {
                if(line.getInfo().indexOf(phrase) != -1) answer.add(line);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filtMagList = filterByMagnitude(list, 5);
        for(QuakeEntry loc : filtMagList){
            System.out.println(loc);
        }
        System.out.println("read data for "+filtMagList.size()+" quakes");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list,1000000,city);
        for(QuakeEntry loc : answer){
            System.out.println(loc);
            System.out.println(loc.getLocation().distanceTo(city));
        }
        System.out.println("found "+answer.size()+" quakes");
    }
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredData = filterByDepth(list, -8000,-5000);
        for(QuakeEntry loc : filteredData){
            System.out.println(loc);
        }
        System.out.println("read data for "+filteredData.size()+" quakes");
    }
    public void quakesOfPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredData = filterByPhrase(list, "any","Creek");
        for(QuakeEntry loc : filteredData){
            System.out.println(loc);
        }
        System.out.println("read data for "+filteredData.size()+" quakes");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
