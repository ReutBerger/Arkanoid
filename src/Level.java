package ass6;

import java.util.ArrayList;
import java.util.List;
/**
 * A Level class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 16/6/19
 */
public class Level implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private int rowHeight;
    private int blocksStartX;
    private int blocksStartY;
    private String blockDefinitions;
    // Constructor-
    /**
     * Construct a new level.
     */
    public Level() {
        this.levelName = null;
        this.background = null;
        this.numberOfBalls = 0;
        this.numberOfBlocksToRemove = 0;
        this.blocks = new ArrayList<Block>();
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.rowHeight = 0;
        this.blocksStartX = 0;
        this.blocksStartY = 0;
     }
    /**
     * Return the number of the balls in the level.
     * @return the number of the balls in the level
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * The method sets the num of balls.
     * @param num - the new num of balls.
     */
    public void setNumOfBalls(int num) {
        this.numberOfBalls = num;
    }
    /**
     * The method initial velocity of each ball.
     * @return a list of the velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }
    /**
     * The method add velocity.
     * @param v - the new velocity
     */
    public void addVelocity(Velocity v) {
        this.initialBallVelocities.add(v);
    }
    /**
     * Return the speed of the paddle.
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * The method sets the paddle Speed.
     * @param speed - the new paddle Speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }
    /**
     * Return the width of the paddle.
     * @return the width of the paddle
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * The method sets the paddle Width.
     * @param width - the new paddleWidth
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }
    /**
     * Return the name of the level.
     * @return the name of the level
     */
    @Override
    public String levelName() {
        return this.levelName;
    }
    /**
     * The method sets the name of the level.
     * @param s - the new name of the level
     */
    public void setLevelName(String s) {
        this.levelName = s;
    }
    /**
     * Returns a sprite with the background of the level.
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * The method sets the background of the level.
     * @param b - the new background of the level.
     */
    public void setBackground(Background b) {
        this.background = b;
    }
    /**
     * Return the list of blocks.
     * @return the list of blocks
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * The method add block to the list.
     * @param b - the new block
     */
    public void addBlock(Block b) {
      this.blocks.add(b);
    }
    /**
     * Return the number of blocks in the level.
     * @return the number of blocks in the level
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**
     * The method sets the num of blocks.
     * @param num - the new num of blocks.
     */
    public void setNumOfBlocks(int num) {
        this.numberOfBlocksToRemove = num;
    }
    /**
     * Return the number of row Height in the level.
     * @return the number of row Height in the level
     */
    public int rowHeight() {
        return this.rowHeight;
    }
    /**
     * The method sets the row Height.
     * @param num - the new row Height
     */
    public void setRowHeight(int num) {
        this.rowHeight = num;
    }
    /**
     * Return the number of blocks Start X in the level.
     * @return the number of blocks Start X in the level
     */
    public int blocksStartX() {
        return this.blocksStartX;
    }
    /**
     * The method sets the num of blocks Start X.
     * @param x - the new num of blocks Start X
     */
    public void setblocksStartX(int x) {
        this.blocksStartX = x;
    }
    /**
     * Return the number of blocks Start Y in the level.
     * @return the number of blocks Start Y in the level
     */
    public int blocksStartY() {
        return this.blocksStartY;
    }
    /**
     * The method sets the num of blocks Start Y.
     * @param y - the new num of blocks Start Y.
     */
    public void setblocksStartY(int y) {
        this.blocksStartY = y;
    }
    /**
     * Return the Block Definitions of the level.
     * @return the Block Definitions of the level
     */
    public String blockDefinitions() {
        return this.blockDefinitions;
    }
    /**
     * The method sets the Block Definitions.
     * @param b - the new Block Definitions.
     */
    public void setBlockDefinitions(String b) {
        this.blockDefinitions = b;
    }
}
