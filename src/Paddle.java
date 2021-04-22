package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
 * A Paddle class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle paddle;
    private int speed;
  //  private long time = 0;
    private biuoop.KeyboardSensor keyboard;
    // Constructor-
    /**
     * Construct a paddle given rectangle and gui.
     * @param paddle - a rectangle
     * @param gui - a gui
     * @param speed - the speed
     */
    public Paddle(Rectangle paddle, GUI gui, int speed) {
        this.paddle = paddle;
        Color[] col = {Color.orange};
        this.paddle.setColor(col);
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
    }
    /**
     * The method move the paddle to the left.
     */
    public void moveLeft() {
        Point p;
        if (this.paddle.getUpperLeft().getX() - this.speed  <= GameLevel.BLOCK_H) {
            p = new Point(GameLevel.BLOCK_H, this.paddle.getUpperLeft().getY());
        } else {
            p = new Point(this.paddle.getUpperLeft().getX() - this.speed, this.paddle.getUpperLeft().getY());
        }
        this.paddle = new Rectangle(p, this.paddle.getWidth(), this.paddle.getHeight());
        Color[] col = {Color.orange};
        this.paddle.setColor(col);
    }
    /**
     * The method move the paddle to the right.
     */
    public void moveRight() {
        Point p;
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + this.speed
            >= GameLevel.BOARD_W -  GameLevel.BLOCK_H) {
            p = new Point(GameLevel.BOARD_W - 30 - this.paddle.getWidth(), this.paddle.getUpperLeft().getY());
        } else {
            p = new Point(this.paddle.getUpperLeft().getX() + this.speed, this.paddle.getUpperLeft().getY());
        }
        this.paddle = new Rectangle(p, this.paddle.getWidth(), this.paddle.getHeight());
        Color[] col = {Color.orange};
        this.paddle.setColor(col);
    }
    /**
     * The method check which side to move.
     */
    /*
    public void move() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }*/
    // Sprite
    /**
     *  The method sets the time that passed from the create of the paddle.
     */
    public void timePassed() {
        //  this.time++;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * The method draws the paddle on the given DrawSurface.
     * @param d - the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.paddle.getUpperLeft().getX();
        int y = (int) this.paddle.getUpperLeft().getY();
        int width = (int) this.paddle.getWidth();
        int height = (int) this.paddle.getHeight();
        //move();
        d.setColor(this.getCollisionRectangle().getColor()[0]);
        d.fillRectangle(x, y, width, height);
    }
    // Collidable
    /**
     * Return the "collision shape" of the object.
     * @return the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit based on the region the object meet.
     * @param hitter - the ball that was done the hit
     * @param collisionPoint - the Point of the collision
     * @param currentVelocity - the current velocity
     * @return the new velocity of the object after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity vel = null;
        double x = collisionPoint.getX();
        double region0 = this.paddle.getUpperLeft().getX();
        double region1 = this.paddle.getUpperLeft().getX() + ((this.paddle.getWidth() / 5) * 1);
        double region2 = this.paddle.getUpperLeft().getX() + ((this.paddle.getWidth() / 5) * 2);
        double region3 = this.paddle.getUpperLeft().getX() + ((this.paddle.getWidth() / 5) * 3);
        double region4 = this.paddle.getUpperLeft().getX() + ((this.paddle.getWidth() / 5) * 4);
        double region5 = this.paddle.getUpperLeft().getX() + this.paddle.getWidth();
        if (currentVelocity.getDy() >= 0) {
            if (x >= region0 && x < region1) {
                vel = Velocity.fromAngleAndSpeed(-60, 6);
            } else if (x >= region1 && x < region2) {
                vel = Velocity.fromAngleAndSpeed(-30, 6);
            } else if (x >= region2 && x <= region3) {
                vel = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            } else if (x > region3 && x <= region4) {
                vel = Velocity.fromAngleAndSpeed(30, 6);
            } else if (x > region4 && x <= region5) {
                vel = Velocity.fromAngleAndSpeed(60, 6);
            }
        } else if (this.paddle.getLeft().pointFindOnLine(collisionPoint)) {
                vel = Velocity.fromAngleAndSpeed(-60, 6);
            } else if (this.paddle.getRight().pointFindOnLine(collisionPoint)) {
                vel = Velocity.fromAngleAndSpeed(60, 6);
            }
        if (vel == null) {
            vel = currentVelocity;
        }
        return vel;
    }
    /**
     * The method add this paddle to the game.
     * @param g - an access to board of the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}

