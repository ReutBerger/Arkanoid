package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A LevelNameIndicator class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public class LevelNameIndicator implements Sprite {
    private String name;
    // constructor-
    /**
     * Construct a LevelNameIndicator given the name of the level.
     * @param name - the name of the level
     */
    public LevelNameIndicator(String name) {
        this.name = name;
    }
    /**
     * The method draw the score on the screen.
     * @param d - the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameLevel.BOARD_W, 25);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, GameLevel.BOARD_W, 25);
        d.setColor(Color.BLACK);
        d.drawText(155 + (GameLevel.BOARD_W / 2), 5 + (25 / 2), "Level Name: " + this.name, 15);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
