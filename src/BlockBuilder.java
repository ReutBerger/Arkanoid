package ass6;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;
/**
 * A BlockBuilder class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 18/6/19
 */
public class BlockBuilder implements BlockCreator {
    private int height;
    private int width;
    private Color stroke;
    private int hitPoints;
    private Map<Integer, Color> colors;
    private Map<Integer, Image> images;
    /**
     * Construct a BlockBuilder - default.
     */
    public BlockBuilder() {
        this.stroke = null;
        colors = null;
        images = null;
    }
    /**
     * Return the height of the block.
     * @return the height of the block
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * The method sets the height of the block.
     * @param h - the new height of the block
     */
    public void setHeight(int h) {
        this.height = h;
    }
    /**
     * Return the width of the block.
     * @return the width of the block
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * The method sets the width of the block.
     * @param w - the new width of the block
     */
    public void setWidth(int w) {
        this.width = w;
    }
    /**
     * Return the stroke of the block.
     * @return the stroke of the block
     */
    public Color getStrokeColor() {
        return this.stroke;
    }
    /**
     * The method sets the stroke of the block.
     * @param s - the new stroke of the block
     */
    public void setStrokeColor(Color s) {
        this.stroke = s;
    }
    /**
     * Return the hitPoints of the block.
     * @return the hitPoints of the block
     */
    public int getHitPoints() {
        return this.hitPoints;
    }
    /**
     * The method sets the hitPoints of the block.
     * @param hPoints - the new hitPoints of the block
     */
    public void setHitPoints(int hPoints) {
        this.hitPoints = hPoints;
    }
    /**
     * Return the map colors of the block.
     * @return the map colors of the block
     */
    public Map<Integer, Color> getFillColors() {
        return this.colors;
    }
    /**
     * The method sets the map colors of the block.
     * @param c - the new map colors of the block
     */
    public void setFillColors(Map<Integer, Color> c) {
        this.colors = c;
    }
    /**
     * Return the map images of the block.
     * @return the map images of the block
     */
    public Map<Integer, Image> getFillImages() {
        return this.images;
    }
    /**
     * The method sets the map images of the block.
     * @param i - the new map images of the block
     */
    public void setFillImages(Map<Integer, Image> i) {
        this.images = i;
    }
    /**
     * Create a block at the specified location.
     * @param xpos - x position
     * @param ypos - y position
     * @return the new block
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block b;
        Point p = new Point((double) xpos, (double) ypos);
        Rectangle rect = new Rectangle(p, this.width, this.height);
        b = new Block(rect, this.stroke, this.hitPoints, this.colors, this.images);
        return b;
    }
}
