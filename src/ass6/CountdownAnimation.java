package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.Sleeper;
/**
 * A CountdownAnimation class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 24/5/19
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private int leftCount;
    /**
     * Construct a CountdownAnimation object.
     * @param numOfSeconds - the number of seconds that the countdown will appear on the screen.
     * @param countFrom - the number to countFrom back to 1
     * @param gameScreen - the game Screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.leftCount = countFrom;
    }
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        int i = this.leftCount + 1;
        if (i == 0) {
            this.stop = true;
            return;
        }
        if (i != 0) {
            d.setColor(Color.YELLOW);
            d.drawText((d.getWidth() / 2) - 20, (d.getHeight() / 2) + 120, "" + this.leftCount, 100);
        }
        if (((int) this.numOfSeconds / this.countFrom) != 0) {
            sleeper.sleepFor(((int) this.numOfSeconds / this.countFrom));
        }
        this.leftCount--;
    }
    /**
     * The method is in charge of stopping condition.
     * @return if the animation should stop or not
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
