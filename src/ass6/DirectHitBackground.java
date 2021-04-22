package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A DirectHitBackground class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public class DirectHitBackground implements Sprite {
    /**
     * The method draw the sprite to the screen.
     * @param d -the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 200, 135);
        d.drawCircle(400, 200, 100);
        d.drawCircle(400, 200, 65);
        d.drawLine(230, 200, 570, 200);
        d.drawLine(400, 40, 400, 370);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
