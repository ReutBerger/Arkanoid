package ass6;

import biuoop.DrawSurface;
/**
 * An Animation interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 24/5/19
 */
public interface Animation {
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    void doOneFrame(DrawSurface d);
    /**
     * The method is in charge of stopping condition.
     * @return if the animation should stop or not
     */
    boolean shouldStop();
}
