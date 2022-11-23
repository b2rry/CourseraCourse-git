import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
       int counter = 0;
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            counter++;
            if(isSorted(in)) break;
        }
        System.out.println(counter);
        return in;
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxInd = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxInd).getDepth()) {
                maxInd = i;
            }
        }
        return maxInd;
    }
    public ArrayList<QuakeEntry> sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i< 50; i++) {
            int maxInd = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMax = in.get(maxInd);
            in.set(i,qMax);
            in.set(maxInd,qi);
        }
        return in;
    }
    public ArrayList<QuakeEntry> onePassBubbleSort(ArrayList<QuakeEntry> in, int amountUses){
        //int ind = 0;
        QuakeEntry bufObj;
        for(int ind = 0; ind < in.size()-amountUses-1; ind++) {
            if (in.get(ind).getMagnitude() > in.get(ind + 1).getMagnitude()) {
                bufObj = in.get(ind);
                in.set(ind,in.get(ind+1));
                in.set(ind+1,bufObj);
            }
        }
        return in;
    }
    public ArrayList<QuakeEntry> sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int steps = 0;
        for(int sortElems = 0; sortElems < in.size()-1; sortElems++){
            in = onePassBubbleSort(in,sortElems);
            steps++;
            if(isSorted(in)) break;
        }
        System.out.println("amount of steps for sorting: "+steps);
        return in;
    }
    public boolean isSorted(ArrayList<QuakeEntry> in){
        for(int i = 0; i<in.size()-1;i++){
            if(in.get(i).getMagnitude() > in.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
}
