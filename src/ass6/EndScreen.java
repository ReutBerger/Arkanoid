package ass6;

import java.awt.Color;

import biuoop.DrawSurface;
/**
 * A PauseScreen class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 28/5/19
 */
public class EndScreen implements Animation {
    private boolean stop;
    private Counter score;
    private boolean isWin;
    // Constructor-
    /**
     * Construct an EndScreen given the final score and status of winning.
     * @param score - the final score
     * @param isWin - if the user win or lose
     */
    public EndScreen(Counter score, boolean isWin) {
       this.stop = false;
       this.score = score;
       this.isWin = isWin;
    }
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(205, 150, 205));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        if (!this.isWin) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        }
        d.drawText(10, d.getHeight() / 2 + 50, "-- press space to continue", 32);
    }
    /**
     * The method is in charge of stopping condition.
     * @return if the animation should stop or not
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
