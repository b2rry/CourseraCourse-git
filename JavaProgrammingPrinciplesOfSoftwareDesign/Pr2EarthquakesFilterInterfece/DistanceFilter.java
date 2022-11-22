public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDist;

    public DistanceFilter(Location inputLoc, double inputDist) {
        loc = inputLoc;
        maxDist = inputDist;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(loc) < maxDist;
    }
}
