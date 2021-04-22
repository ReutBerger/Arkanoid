package ass6;

import java.util.List;
/**
 * A Line class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/3/19
 */
public class Line {
    private Point start;
    private Point end;
    // constructors-
    /**
     * Construct a line given start point and end point.
     * @param start - the start point
     * @param end - the start point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Construct a line given x and y coordinates.
     * @param x1 - the x coordinate of the start point
     * @param y1 - the y coordinate of the start point
     * @param x2 - the x coordinate of the end point
     * @param y2 - the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * Return the length of the line.
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * Returns the middle point of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        double mX, mY;
        mX = (this.start.getX() + this.end.getX()) / 2;
        mY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(mX, mY);
    }
    /**
     * Returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }
    /**
     *  Returns the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     * The method check if the lines are equal.
     * @param other - a line to measure the equals to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end()));
    }
    /**
     * The method check if the lines are intersect.
     * @param other - a line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }
    /**
     * The method find the intersection points if it exists.
     * @param other - a line
     * @return the intersection point if the lines intersect,
     *         and null otherwise
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = other.start().getX();
        double y2 = other.start().getY();
        double x3 = this.end.getX();
        double x4 = other.end().getX();
        // Create line equations - for the case that slope exists
        if ((x1 != x3) && (x2 != x4)) {
            double ml1 = slope(start(), end());
            double b1 = (ml1 * x1 * -1) + y1;
            double ml2 = slope(other.start(), other.end());
            double b2 = (ml2 * x2 * -1) + y2;
            // Checks for the slope of the lines
            // Parallel
            if (ml1 == ml2) {
                if (this.pointFindOnLine(other.start())) {
                    return other.start();
                } else  if (this.pointFindOnLine(other.end())) {
                    return other.end();
                }
                return null;
            } else {
                if ((ml1 == 0) && (ml2 != 0)) {
                    double xi = (b1 - b2) / ml2;
                    double yi = b1;
                    Point pi = new Point(xi, yi);
                    return this.pointIntersection(pi, other);
                } else {
                    if ((ml2 == 0) && (ml1 != 0)) {
                        double xi = (b2 - b1) / ml1;
                        double yi = b2;
                        Point pi = new Point(xi, yi);
                        return this.pointIntersection(pi, other);
                    } else {
                        double xi = (b2 - b1) / (ml1 - ml2);
                        double yi = (ml1 * xi) + b1;
                        Point pi = new Point(xi, yi);
                        return this.pointIntersection(pi, other);
                    }
                }
            }
        // The case that slope does not exists
        } else {
            if (x1 == x3 && x2 != x4) {
                double ml2 = slope(other.start(), other.end());
                double b2 = (ml2 * x2 * -1) + y2;
                double xi, yi;
                if (ml2 != 0) {
                    xi = x1;
                    yi = (ml2 * xi) + b2;
                } else {
                     xi = x1;
                     yi = y2;
                }
                Point pi = new Point(xi, yi);
                return this.pointIntersection(pi, other);
            } else {
                if ((x1 != x3) && (x2 == x4)) {
                    double ml1 = slope(start(), end());
                    double b1 = (ml1 * x1 * -1) + y1;
                    double xi, yi;
                    if (ml1 != 0) {
                        xi = x2;
                        yi = (ml1 * xi) + b1;
                    } else {
                        xi = x2;
                        yi = y1;
                    }
                    Point pi = new Point(xi, yi);
                    return this.pointIntersection(pi, other);
                } else {
                    return this.pointIntersection(other.start(), this);
                }
            }
        }
    }
    /**
     * The method checks if the point is between the end points of the line.
     * @param p - a point
     * @return true if the point is between the end points, false otherwise
     */
    public boolean pointFindOnLine(Point p) {
        if ((int) (this.start().distance(p) + this.end().distance(p)) <= (int) this.length()) {
            return true;
        }
        return false;
    }
    /**
     * The method calculates the slope of the line.
     * @param start1 - the start point of line
     * @param end1 - the end point of line
     * @return the slope of the line
     */
    public double slope(Point start1, Point end1) {
        double x1 = start1.getX();
        double y1 = start1.getY();
        double x2 = end1.getX();
        double y2 = end1.getY();
        return (y2 - y1) / (x2 - x1);
    }
    /**
     *  The method checks if the point is between the end points of the line.
     * @param pi - the intersection point
     * @param other - a line
     * @return the intersection point if it is between the end points of the line,
     *         nall otherwise
     */
    public Point pointIntersection(Point pi, Line other) {
        if (this.pointFindOnLine(pi) && other.pointFindOnLine(pi)) {
            return pi;
        } else {
            return null;
        }
    }
    /**
     * The method checks if this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the start of the line.
     * @param rect - a rectangle
     * @return the closet point intersection to start of line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointsList = rect.intersectionPoints(this);
        Point min = null;
        if (pointsList != null) {
            if (pointsList.size() == 1) {
                return pointsList.get(0);
            } else {
                for (Point p : pointsList) {
                    if (min == null) {
                        min = p;
                    } else if (this.start.distance(p) < this.start.distance(min)) {
                        min = p;
                    }
                }
            }
        }
        return min;
    }
}
