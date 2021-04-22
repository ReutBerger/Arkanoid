package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A Green3Background class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public class Green3Background implements Sprite {
    /**
     * The method draw the sprite to the screen.
     * @param d -the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 139, 0));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.fillRectangle(70, 440, 110, 160);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(80 + (i * 20), 450 + (j * 35), 10, 30);
            }
        }
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(110, 380, 30, 60);
        d.setColor(new Color(99, 99, 99));
        d.fillRectangle(120, 200, 10, 180);
        d.setColor(new Color(207, 191, 59));
        d.setColor(Color.ORANGE);
        d.fillCircle(125, 200, 15);
        d.setColor(Color.RED);
        d.fillCircle(125, 200, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(125, 200, 5);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
