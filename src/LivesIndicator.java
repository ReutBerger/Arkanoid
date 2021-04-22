package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A LivesIndicator class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 12/3/19
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    // constructor-
    /**
     * Construct a LivesIndicator given the num of lives.
     * @param lives - the num of lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    /**
     * The method draw the num of lives to the screen.
     * @param d - the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(0 + (GameLevel.BOARD_W / 5) - 20, 5 + (25 / 2), "lives: " + this.lives.getValue() , 15);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}

