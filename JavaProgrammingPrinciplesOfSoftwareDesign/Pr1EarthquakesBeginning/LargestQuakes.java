import java.util.ArrayList;
import java.util.Collections;

public class LargestQuakes {
    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        ArrayList<QuakeEntry> sortedOfMag = getLargest(list,5);
        for(QuakeEntry loc : sortedOfMag){
            System.out.println(loc);
        }
        //System.out.println(indexOfLargest(list) + " ");
        System.out.println("number found: "+sortedOfMag.size());
    }
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        ArrayList<Double> magnitudes = new ArrayList<Double>();
        int ind = 0;
        double maxMagnitude = 0;
        for(QuakeEntry loc : quakeData){
            magnitudes.add(loc.getMagnitude());
            if(maxMagnitude < loc.getMagnitude()){
                maxMagnitude = loc.getMagnitude();
            }
        }
        return magnitudes.indexOf(maxMagnitude);
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<Double> magnitudes = new ArrayList<Double>();
        for(QuakeEntry line : quakeData){
            double buf = line.getMagnitude();
            magnitudes.add(buf);
        }
        ArrayList<Double> magnitudesSorted = new ArrayList<Double>();
        for(Double num : magnitudes){
            magnitudesSorted.add(num);
        }
        Collections.sort(magnitudesSorted);
        if(quakeData.size() < howMany) howMany = quakeData.size();
        int amount = quakeData.size();
        for (int ind = amount-1; ind >= amount - howMany; ind--){
            Double bufNum = magnitudesSorted.get(ind);
            int objInd = magnitudes.indexOf(bufNum);
            magnitudes.remove(objInd);
            ret.add(quakeData.get(objInd));
            quakeData.remove(objInd);
        }
        return ret;
    }
}

