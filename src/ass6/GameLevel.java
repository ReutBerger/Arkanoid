package ass6;

import java.util.List;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
/**
 * A Game class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private Counter numOfLife;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private int speed;
    public static final int BOARD_W = 800;
    public static final int BOARD_H = 600;
    public static final int BLOCK_W = 50;
    public static final int BLOCK_H = 30;
    public static final int PADDLE_H = 30;
    // Constructor-
    /**
     * Construct a game level.
     * @param levelInfo - the level information
     * @param keyboardSensor - the keyboardSensor
     * @param animationRunner - the animationRunner
     * @param gui - the gui
     * @param currentScore - the currentScore
     * @param numOfLives - the num of lives
     * @param sleeper - the sleeper
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     GUI gui, Counter currentScore, Counter numOfLives, Sleeper sleeper) {
        this.levelInfo = levelInfo;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.gui = gui;
        this.numOfBlocks = new Counter();
        this.numOfBalls = new Counter();
        this.score = currentScore;
        this.numOfLife = numOfLives;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
    }
    /**
     * The method adds the new collidable to other collidables in the game environment.
     * @param c - a collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * The method adds the new sprite to other sprites in the SpriteCollection.
     * @param s - a sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     *
     * The method initialize a new level: create the Blocks, Background, Listeners, score and lives,
     * and add them to the game screen.
     */
    public void initialize() {
        // Create listeners -
        BlockRemover blockR = new BlockRemover(this, this.numOfBlocks);
        BallRemover ballR = new BallRemover(this, this.numOfBalls);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        // Create Background -
        this.addSprite(this.levelInfo.getBackground());
        // Create blocks -
        this.numOfBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
        List<Block> blocks = this.levelInfo.blocks();
        for (Block b: blocks) {
            b.addToGame(this);
            b.addHitListener(blockR);
            b.addHitListener(scoreListener);
        }
        // Create board obstacles (the dim of the game) -
        Block upBlock = new Block(0, 25, BOARD_W, BLOCK_H);
        upBlock.addToGame(this);
        upBlock.setHit(0);
        Block downBlock = new Block(0, BOARD_H, BOARD_W, BLOCK_H);
        downBlock.addToGame(this);
        downBlock.setHit(0);
        downBlock.addHitListener(ballR);
        Block leftBlock = new Block(0, 0, BLOCK_H, BOARD_H);
        leftBlock.addToGame(this);
        leftBlock.setHit(0);
        Block rightBlock = new Block(BOARD_W - BLOCK_H, 0, BLOCK_H, BOARD_H);
        rightBlock.addToGame(this);
        rightBlock.setHit(0);
        // Create appear of the name of the level on the board
        LevelNameIndicator name = new LevelNameIndicator(this.levelInfo.levelName());
        this.addSprite(name);
        // Create appear of score and life on the board
        LivesIndicator livesI = new LivesIndicator(this.numOfLife);
        this.addSprite(livesI);
        ScoreIndicator scoreI = new ScoreIndicator(scoreListener);
        this.addSprite(scoreI);
    }
    /**
     * The method playOneTurn -- start the animation loop,
     * and add paddle and balls to the game.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2000, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }
    /**
     * The method create balls and paddle.
     */
    public void createBallsOnTopOfPaddle() {
        // Create paddle -
        Point p = new Point((BOARD_W / 2) - (this.levelInfo.paddleWidth() / 2), BOARD_H - BLOCK_H - PADDLE_H);
        Rectangle rect = new Rectangle(p, this.levelInfo.paddleWidth(), PADDLE_H);
        this.paddle = new Paddle(rect, this.gui, this.speed);
        this.paddle.addToGame(this);
        // Create balls-
        this.numOfBalls.increase(this.levelInfo.numberOfBalls());
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(this.environment);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }
    /**
     * The method remove the collidable from the game.
     * @param c - the collidable
     */
    public void removeCollidable(Collidable c) {
         this.environment.removeCollidable(c);
    }
    /**
     * The method remove the sprite from the game.
     * @param s - the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // All blocks remove from the board
        if (this.numOfBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        // All balls remove from the board
        if (this.numOfBalls.getValue() == 0) {
            // remove the paddle
            this.removeCollidable(this.paddle);
            this.removeSprite(this.paddle);
            this.numOfLife.decrease(1);
            this.running = false;
        }
        // Pause case-
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }
    /**
     * The method is in charge of stopping condition.
     * @return if the animation should stop or not
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * Return the num of lives.
     * @return the num of lives
     */
    public int getNumOfLives() {
        return this.numOfLife.getValue();
    }
    /**
     * Return the num of blocks.
     * @return the num of blocks
     */
    public int getNumOfBlocks() {
        return this.numOfBlocks.getValue();
    }
    /**
     * The method sets the paddle speed.
     * @param s - the new paddle speed
     */
    public void setPaddleSpeed(int s) {
        this.speed = s;
    }
}
