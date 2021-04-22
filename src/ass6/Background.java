package ass6;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 * A Background class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 17/6/19
 */
public class Background implements Sprite {
    private Color color;
    private Image image;
    // Constructors-
    /**
     * Construct a Background given color.
     * @param color - the color
     */
    public Background(Color color) {
        this.color = color;
        this.image = null;
    }
    /**
     * Construct a Background given image.
     * @param image the image
     */
    public Background(Image image) {
        this.image = image;
        this.color = null;
     }
    /**
     * The method draw the sprite to the screen.
     * @param d -the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        } else if (this.image != null) {
            d.drawImage(0, 0, this.image);
        }
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
