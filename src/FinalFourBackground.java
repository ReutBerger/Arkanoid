package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A FinalFourBackground class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public class FinalFourBackground implements Sprite {
    /**
     * The method draw the sprite to the screen.
     * @param d -the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 154, 204));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 10; i++) {
            d.drawLine(90 + (i * 9), 480, 50 + (i * 9), 800);
            d.drawLine(610 + (i * 9), 535, 520 + (i * 9), 800);
        }
        d.fillCircle(90, 450, 23);
        d.fillCircle(600, 500, 21);
        d.fillCircle(115, 480, 28);
        d.fillCircle(625, 535, 28);
        d.setColor(new Color(166, 166, 166));
        d.fillCircle(130, 440, 30);
        d.fillCircle(645, 508, 28);
        d.setColor(new Color(158, 158, 158));
        d.fillCircle(165, 455, 32);
        d.fillCircle(685, 525, 32);
        d.fillCircle(150, 480, 23);
        d.fillCircle(665, 540, 22);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
