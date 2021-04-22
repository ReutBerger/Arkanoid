package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A WideEasyBackground class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public class WideEasyBackground implements Sprite {
    /**
     * The method draw the sprite to the screen.
     * @param d -the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(238, 232, 170));
        d.fillCircle(200, 180, 65);
        for (int i = 0; i < 800; i += 8) {
            d.drawLine(200, 180, i, 265);
        }
        d.setColor(new Color(207, 191, 59));
        d.setColor(Color.ORANGE);
        d.fillCircle(200, 180, 55);
        d.setColor(new Color(255, 215, 0));
        d.fillCircle(200, 180, 45);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
