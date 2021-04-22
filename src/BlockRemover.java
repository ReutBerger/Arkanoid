package ass6;

/**
 * A BlockRemover class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/5/19
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    // Constructor-
    /**
     * Construct a BlockRemover given the game and the num of blocks.
     * @param game - an access to board of the game
     * @param removedBlocks - the num of the blocks in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * This method is called whenever the beingHit object is hit,
     * and remove the blocks that are hit and reach 0 hit-points from the game.
     * @param beingHit - the block that was being hit
     * @param hitter - the Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() <= 0) {
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}
