package ass6;

/**
 * A ScoreTrackingListener class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 12/3/19
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    // Constructor-
    /**
     * Construct a ScoreTrackingListener given the scoreCounter.
     * @param scoreCounter - the scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
     }
    @Override
    /**
     * This method is called whenever the beingHit object is hit,
     * and update the score according to the hit.
     * @param beingHit - the block that was being hit
     * @param hitter - the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
    /**
     * Return the current score(num).
     * @return the current score
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
}
