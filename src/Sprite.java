package ass6;

import biuoop.DrawSurface;
/**
 * A Sprite interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public interface Sprite {
    /**
     * The method draw the sprite to the screen.
     * @param d -the DrawSurface
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
