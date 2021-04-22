package ass6;

/**
* A CollisionInfo class.
* @author Reut Berger
* @version 1.8.0_201
* @since 31/3/19
*/
public class CollisionInfo {
    private Point p;
    private Collidable col;
    // Constructor-
    /**
     * Construct a collisionInfoiven point and collidable of the collision.
     * @param p - the point of the collision
     * @param c - the collidable of the collision
     */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.col = c;
    }
    /**
     * Return the point at which the collision occurs.
     * @return a point
     */
    public Point collisionPoint() {
        return this.p;
    }
    /**
     * Return the collidable object involved in the collision.
     * @return a collidable
     */
    public Collidable collisionObject() {
        return this.col;
    }
}

