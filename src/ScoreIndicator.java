package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A ScoreIndicator class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 12/3/19
 */
public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener scoreListener;
    // constructor-
    /**
     * Construct a ScoreIndicator given the scoreListener.
     * @param scoreListener - the scoreListener
     */
    public ScoreIndicator(ScoreTrackingListener scoreListener) {
        this.scoreListener = scoreListener;
    }
    /**
     * The method draw the score on the screen.
     * @param d - the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
       // d.setColor(Color.WHITE);
   //     d.fillRectangle(0, 25, GameLevel.BOARD_W, 25);
        d.setColor(Color.BLACK);
     //   d.drawRectangle(0, 25, GameLevel.BOARD_W, 25);
        d.drawText(0 + (GameLevel.BOARD_W / 2) - 25, 5 + (25 / 2), "score: " + this.scoreListener.getScore(), 15);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}

