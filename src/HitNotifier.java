package ass6;

/**
 * A HitNotifier interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/5/19
 */
public interface HitNotifier {
    /**
     * The method add the hitListener as a listener to hit events.
     * @param hl - the hitListener
     */
    void addHitListener(HitListener hl);
    /**
     * The method remove the hitListener from the list of listeners to hit events.
     * @param hl - the hitListener
     */
    void removeHitListener(HitListener hl);
}
