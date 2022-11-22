public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;

    public DepthFilter(double min, double max) {
        minDep = min;
        maxDep = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDep && qe.getDepth() <= maxDep;

    }
}
