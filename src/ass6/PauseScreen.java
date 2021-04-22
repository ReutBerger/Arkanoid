package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A PauseScreen class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 24/5/19
 */
public class PauseScreen implements Animation {
    private boolean stop;
    // Constructor-
    /**
     * Construct a PauseScreen.
     */
    public PauseScreen() {
       this.stop = false;
    }
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(238, 169, 184));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    /**
     * The method is in charge of stopping condition.
     * @return if the animation should stop or not
     */
    public boolean shouldStop() {
        return this.stop;
    }
}