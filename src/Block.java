package ass6;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;
/**
 * A Block class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private List<HitListener> hitListeners;
//    private long time = 0;
    private int hitPoints = 2;
    private Map<Integer, Color> colors = null;
    private Map<Integer, Image> images = null;
    private Color stroke = null;
    // Constructors-
    /**
     * Construct a block given a Rectangle,stroke color, num Of hits, colors, images
     * and create an empty list of hitListeners.
     * @param block - the rectangle
     * @param stroke - the color of the stroke
     * @param hitPoints - the num of hits
     * @param colors - the color of the block
     * @param images - the image of the block
     */
    public Block(Rectangle block, Color stroke, int hitPoints,
                 Map<Integer, Color> colors, Map<Integer, Image> images) {
        this.block = block;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.hitPoints = hitPoints;
        this.colors = colors;
        this.images = images;
    }
    /**
     * Construct a block given a Rectangle,
     * and create an empty list of hitListeners.
     * @param block - a Rectangle
     */
    public Block(Rectangle block) {
        this.block = block;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Construct a block given x and y coordinate of the upper left point,
     * width and height of the block.
     * And create an empty list of hitListeners.
     * @param x - the 'x' coordinate of the upper left point
     * @param y - the 'y' coordinate of the upper left point
     * @param width - the width of the block
     * @param height - the height of the block
     */
    public Block(double x, double y, double width, double height) {
        this.block = new Rectangle(new Point(x, y), width, height);
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Return the "collision shape" of the object.
     * @return the block
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter - the ball that was done the hit
     * @param collisionPoint - the Point of the collision
     * @param currentVelocity - the current velocity
     * @return the new velocity of the object after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hitPoints--;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (this.block.getUp().pointFindOnLine(collisionPoint)) { // && (currentVelocity.getDy() >= 0))
            dy = -1 * currentVelocity.getDy();
        } else if (this.block.getDown().pointFindOnLine(collisionPoint)) { //&& (currentVelocity.getDy() <= 0))
            dy = -1 * currentVelocity.getDy();
        }
        if (this.block.getLeft().pointFindOnLine(collisionPoint)) { //&& (currentVelocity.getDx() >= 0))
            dx = -1 * currentVelocity.getDx();
        } else if (this.block.getRight().pointFindOnLine(collisionPoint)) { //&& (currentVelocity.getDx() <= 0))
            dx = -1 * currentVelocity.getDx();
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    /**
     * The method sets the number of hits with the block.
     * @param num - number of hits
     */
    public void setHit(int num) {
        this.hitPoints = num;
    }
    /**
     * The method draws the block on the given DrawSurface.
     * @param d - the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int height = (int) this.block.getHeight();
        if ((this.colors != null) || (this.images != null)) {
            if (this.colors.size() != 0) {
                if (this.colors.containsKey((this.hitPoints))) {
                    d.setColor(this.colors.get(this.hitPoints));
                    d.fillRectangle(x, y, width, height);
                }
            } else if (this.images.size() != 0) {
                if (this.images.containsKey((this.hitPoints))) {
                    d.drawImage(x, y, this.images.get(this.hitPoints));
                }
            }
        } else {
            d.setColor(Color.GRAY);
            d.fillRectangle(x, y, width, height);
        }
        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle(x, y, width, height);
        }
    }
    /**
     * The method sets the time that passed from the create of the block.
     */
    public void timePassed() {
    //    this.time++;
    }
    /**
     * The method add the new block to the game.
     * @param g - an access to board of the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * The method remove the block from the game.
     * @param game - an access to board of the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * The method add the hitListener as a listener to hit events.
     * @param hl - the hitListener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * The method remove the hitListener from the list of listeners to hit events.
     * @param hl - the hitListener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * The method notify all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter - the ball that was done the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Return the num of hits that was left.
     * @return the num of hits
     */
    public int getHitPoints() {
        return this.hitPoints;
    }
    /**
     * Return the map of the fill colors of the block.
     * @return the map of the fill colors of the block
     */
    public Map<Integer, Color> getFillColors() {
        return this.colors;
    }
    /**
     * The method sets the map colors.
     * @param c - the new map colors
     */
    public void setFillColors(Map<Integer, Color> c) {
        this.colors = c;
    }
    /**
     * Return the map of the fill images of the block.
     * @return the map of the fill images of the block
     */
    public Map<Integer, Image> getFillImages() {
        return this.images;
    }
    /**
     * The method sets the map images.
     * @param i - the new map images
     */
    public void setFillImages(Map<Integer, Image> i) {
        this.images = i;
    }
}

