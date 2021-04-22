package ass6;

/**
 * A Point class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/3/19
 */
public class Point {
    private double x;
    private double y;
    // constructor
    /**
     * Construct a point given x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @param other a point to measure the distance to
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    /**
     * The method check if the points are equal.
     * @param other a point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }
    // Return the x and y values of this point
    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }
   /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
}

