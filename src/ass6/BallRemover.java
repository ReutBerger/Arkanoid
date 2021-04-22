package ass6;

/**
 * A BallRemover class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 12/5/19
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    // Constructor-
    /**
     * Construct a BallRemover given the game and the num of balls.
     * @param game - an access to board of the game
     * @param removedBalls - the num of the balls in the game
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    /**
     * This method is called whenever the beingHit object is hit,
     * and remove the ball from the game.
     * @param beingHit - the block that was being hit
     * @param hitter - the Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        if (beingHit.getHitPoints() <= 0) {
            hitter.removeFromGame(this.game);
        }
    }
}
