package ass6;

import java.awt.Color;
import biuoop.DrawSurface;
/**
 * A HighScoresAnimation class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 12/6/19
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable h;
    private boolean stop;
    // Constructor-
    /**
     * Construct a HighScoresAnimation given the table(list) of scores.
     * @param scores - a list of the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.h = scores;
        this.stop = false;
    }
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        d.drawText(50, 50, "High Scores", 35);
        d.setColor(Color.green);
        d.drawText(100, 150, "Player Name", 30);
        d.drawText(450, 150, "Score", 30);
        d.drawText(100, 160, "________________________________________", 30);
        d.setColor(Color.orange);
        for (int i = 0; i < this.h.getHighScores().size(); i++) {
            d.drawText(100, 200 + 50 * i, this.h.getHighScores().get(i).getName(), 32);
            d.drawText(450, 200 + 50 * i, "" + this.h.getHighScores().get(i).getScore(), 32);
        }
        d.setColor(Color.cyan);
        d.drawText(200, d.getHeight() / 2 + 200, "Press space to continue", 32);
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
