package ass6;

import java.util.List;
/**
 * A LevelInformation interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public interface LevelInformation {
    /**
     * Return the number of the balls in the level.
     * @return the number of the balls in the level
     */
    int numberOfBalls();
    /**
     * The method initial velocity of each ball.
     * @return a list of the velocities
     */
    List<Velocity> initialBallVelocities();
    /**
     * Return the speed of the paddle.
     * @return the speed of the paddle
     */
    int paddleSpeed();
    /**
     * Return the width of the paddle.
     * @return the width of the paddle
     */
    int paddleWidth();
    /**
     * Return the name of the level.
     * @return the name of the level
     */
    String levelName();
    /**
     * Returns a sprite with the background of the level.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * The method make up this level.
     * @return a list of the blocks in this level
     */
    List<Block> blocks();
    /**
     * Return the number of blocks in the level.
     * @return the number of blocks in the level
     */
    int numberOfBlocksToRemove();
}
