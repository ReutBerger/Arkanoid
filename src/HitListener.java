package ass6;

/**
 * A HitListener interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/5/19
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that was being hit
     * @param hitter - the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
