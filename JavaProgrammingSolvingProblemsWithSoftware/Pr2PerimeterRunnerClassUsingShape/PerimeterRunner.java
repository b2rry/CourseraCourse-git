import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int amount = 0;
        for (Point currPt : s.getPoints()) {
            amount++;
        }
        return amount;
    }

    public double getAverageLength (Shape s) {
        double perim = getPerimeter(s);
        int amountSides = getNumPoints(s);
        return perim/amountSides;
    }

    public double getLargestSide(Shape s) {
            Point prevPt = s.getLastPoint();
            double currLargestSide = 0;
            for (Point currPt : s.getPoints()) {
                double currDist = prevPt.distance(currPt);
                if(currLargestSide < currDist){
                    currLargestSide = currDist;
                }
                prevPt = currPt;
            }
            return currLargestSide;
    }

    public double getLargestX(Shape s) {
        double currLargestX = s.getLastPoint().getX();
        for (Point currPt : s.getPoints()) {
            if(currPt.getX() > currLargestX){
                currLargestX = currPt.getX();
            }
        }
        return currLargestX;
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("number of points in this shape: " + numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("average length of the side in this shape: " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("length of the longest side in this shape: " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest value of X coordinate in this shape: " + largestX);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
