package ass6;

/**
 * A velocity class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/3/19
 */
public class Velocity {
    private double dx;
    private double dy;
    // constructor-
    /**
     * Construct a velocity given the change in position on the 'x' and the 'y'.
     * @param dx - the change in position on the 'x'
     * @param dy - the change in position on the 'y'
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    // accessories-
    /**
     * Return the change in position on the 'x'.
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * Return the change in position on the 'y'.
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * The method sets the dx value.
     * @param dx1 - new dx value
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }
    /**
     * The method sets the dy value.
     * @param dy1 - new dy value
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }
    /**
     * The method takes a point with position (x,y)
     * and return a new point with position (x+dx, y+dy).
     * @param p - a point
     * @return the new point with velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     * The method construct a velocity given angle and speed.
     * @param angle - an angle
     * @param speed - a speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }
}

