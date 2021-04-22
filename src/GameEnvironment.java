package ass6;

import java.util.List;
import java.util.ArrayList;
/**
 * A GameEnvironment class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    // Constructor-
    /**
     * Construct a GameEnvironment - create an empty list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * Add the given collidable to the list.
     * @param c - a new collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Return the list of the Collidables.
     * @return the list of the Collidables
     */
    public List<Collidable> listCol() {
        return this.collidables;
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory - a line
     * @return the Closest Collision if exists and null if not
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r;
        Collidable closetRect = null;
        Point closetPoint = null, tempPoint;
        for (Collidable c : this.collidables) {
            r = c.getCollisionRectangle();
            if (closetPoint == null) {
                closetPoint = trajectory.closestIntersectionToStartOfLine(r);
                closetRect = c;
            } else {
                tempPoint = trajectory.closestIntersectionToStartOfLine(r);
                if (tempPoint != null) {
                    if (trajectory.start().distance(closetPoint) > trajectory.start().distance(tempPoint)) {
                        closetPoint = tempPoint;
                        closetRect = c;
                    }
                }
            }
        }
        if (closetPoint == null) {
            return null;
        }
        return new CollisionInfo(closetPoint, closetRect);
    }
    /**
     * The method remove the collidable from the list of the Collidables.
     * @param c - the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}

