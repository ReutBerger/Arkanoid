package ass6;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * A Ball class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/3/19
 */
public class Ball implements Sprite {
    private int radius;
    private java.awt.Color color;
    private Point center;
    private Velocity vel;
    private GameEnvironment collidates;
    //private long time = 0;
    public static final int SIZE_R = 8;
    // constructors-
    /**
     * Construct a ball given center point, radius size and color.
     * @param center - the center point of the ball
     * @param r - the radius of the ball
     * @param color - the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    /**
     * Construct a ball given x and y coordinates of the center point,
     * radius size and color.
     * @param x - the x coordinates of the center point
     * @param y - the y coordinates of the center point
     * @param r - the radius of the ball
     * @param color - the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }
    /**
     * Construct a ball with access to collidables in the game.
     * @param environment - a list of the all collidables in the game
     */
    public Ball(GameEnvironment environment) {
        this.radius = SIZE_R;
        this.center = new Point(GameLevel.BOARD_W / 2, GameLevel.BOARD_H -  (2 * GameLevel.BLOCK_H) - SIZE_R);
        this.color = Color.WHITE;
        this.collidates = environment;
        // Create a random angle
        this.vel = Velocity.fromAngleAndSpeed(Math.random() * 360 , (24 / SIZE_R) + 1);
    }
    // accessories
    /**
     * Return the x coordinates of the center point.
     * @return the x coordinates of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Return the y coordinates of the center point.
     * @return the y coordinates of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Return the size of the radius.
     * @return the size of the radius
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * Return the color of the ball.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Return the velocity of the ball.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.vel;
    }
    /**
     * The method draws the ball on the given DrawSurface.
     * @param surface - the DrawSurface
     */
    public void drawOn(DrawSurface surface) {
       // this.moveOneStep();
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }
    /**
     * The method sets the velocity of the ball.
     * @param v - the new velocity
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }
    /**
     * The method sets the velocity of the ball.
     * @param dx - the change in position on the 'x'
     * @param dy - the change in position on the 'y'
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }
    /**
     * The method moves the ball one step.
     * The ball does not go outside of the screen-
     * when it hits the border to the left or to the right,
     * it change its horizontal direction,
     * and when it hits the border on the top or the bottom,
     * it change its vertical direction.
     */
    public void moveOneStep() {
        Line ln;
        Point pointMoving = this.vel.applyToPoint(this.center);
        ln = new Line(this.center, pointMoving);
        double x = this.center.getX();
        double y = this.center.getY();
        double dx = this.vel.getDx();
        double dy = this.vel.getDy();
        CollisionInfo cI = this.collidates.getClosestCollision(ln);
        // For the case there is a collision
        if (cI != null) {
            Point p = cI.collisionPoint();
            Rectangle rect = cI.collisionObject().getCollisionRectangle();
            if (rect.getUp().pointFindOnLine(p) && (this.vel.getDy() > 0)) {
                y = p.getY() - this.radius;
            } else if (rect.getDown().pointFindOnLine(p) && (this.vel.getDy() < 0)) {
                y = p.getY() + this.radius;
            } else {
                y = p.getY();
            }
            if (rect.getLeft().pointFindOnLine(p) && (this.vel.getDx() > 0)) {
                x = p.getX() - this.radius;
            } else if (rect.getRight().pointFindOnLine(p) && (this.vel.getDx() < 0)) {
                x = p.getX() + this.radius;
            } else {
                x = p.getX();
            }
            this.vel = cI.collisionObject().hit(this, p, this.vel);
            this.center = new Point(x, y);
        // For the case the ball hits the board blocks
        } else {
            this.center = this.vel.applyToPoint(this.center);
            double x2 = this.getX();
            double y2 = this.getY();
            if ((y2 < GameLevel.BLOCK_H && dy < 0) || (y2 > (GameLevel.BOARD_H) && dy > 0)) {
                this.setVelocity(dx, -dy);
                this.center = this.vel.applyToPoint(this.center);
            }
            if ((x2 < GameLevel.BLOCK_H && dx < 0) || (x2 > (GameLevel.BOARD_W - GameLevel.BLOCK_H) && dx > 0)) {
                this.setVelocity(-dx, dy);
                this.center = this.vel.applyToPoint(this.center);
            }
        }
        // For the case the ball gets stuck in the paddle
        for (Collidable c: this.collidates.listCol()) {
            if (c.getCollisionRectangle().getUpperLeft().getY() == (GameLevel.BOARD_H - (2 * GameLevel.BLOCK_H))) {
                Rectangle rect = c.getCollisionRectangle();
                Point p = c.getCollisionRectangle().getUpperLeft();
                double minX = p.getX();
                double minY = p.getY();
                double maxX = minX + rect.getWidth();
                double maxY = minY + rect.getHeight();
                if ((this.center.getX() >= minX && this.center.getX() <= maxX)
                        && (this.center.getY() >= minY && this.center.getY() <= maxY)) {
                    this.setVelocity(-dx, dy);
                    this.center = this.vel.applyToPoint(this.center);
                }
            }
        }
    }
    /**
     * The method sets the time that passed from the create of the ball.
     */
    public void timePassed() {
    //    this.time++;
        this.moveOneStep();
    }
    /**
     * The method add the new ball to the game.
     * @param g - an access to board of the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * The method remove the ball from the game.
     * @param game - an access to board of the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
