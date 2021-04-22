package ass6;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * A Rectangle class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 28/3/19
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line up;
    private Line down;
    private Line left;
    private Line right;
    private Color[] color = null;
    // constructor-
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        Point upperRight, lowerLeft, lowerRight;
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        this.up = new Line(this.upperLeft, upperRight);
        this.down = new Line(lowerLeft, lowerRight);
        this.left = new Line(this.upperLeft, lowerLeft);
        this.right = new Line(upperRight, lowerRight);
    }
    /**
     * Return the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Return the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Return the color of the rectangle.
     * @return the color of the rectangle
     */
    public Color[] getColor() {
        return this.color;
    }
    /**
     * The method sets the color of the rectangle.
     * @param color1 -  a new color
     */
    public void setColor(Color[] color1) {
       this.color = color1;
    }
    /**
     * Returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the up line of the rectangle.
     * @return the up line of the rectangle
     */
    public Line getUp() {
        return this.up;
    }
    /**
     * Returns the down line of the rectangle.
     * @return the down line of the rectangle
     */
    public Line getDown() {
        return this.down;
    }
    /**
     * Returns the left line of the rectangle.
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return this.left;
    }
    /**
     * Returns the right line of the rectangle.
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return this.right;
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line - a specified line
     * @return (possibly empty) List of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point tempPi;
        List<Point> pointsList = new ArrayList<Point>();
        if (line.intersectionWith(this.up) != null) {
            tempPi = line.intersectionWith(this.up);
            pointsList.add(tempPi);
        }
        if (line.intersectionWith(this.down) != null) {
            tempPi = line.intersectionWith(this.down);
            pointsList.add(tempPi);
        }
        if (line.intersectionWith(this.left) != null) {
            tempPi = line.intersectionWith(this.left);
            pointsList.add(tempPi);
        }
        if (line.intersectionWith(this.right) != null) {
            tempPi = line.intersectionWith(this.right);
            pointsList.add(tempPi);
        }
        if (pointsList.isEmpty()) {
            return null;
        }
        return pointsList;
    }
}
