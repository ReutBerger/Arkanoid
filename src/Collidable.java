package ass6;

/**
 * A Collidable interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 28/3/19
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return a rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter - the ball that was done the hit
     * @param collisionPoint - the Point of the collision
     * @param currentVelocity - the current velocity
     * @return the new velocity of the object after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}