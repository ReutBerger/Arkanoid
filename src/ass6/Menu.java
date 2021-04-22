package ass6;

/**
 * A Menu interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 13/6/19
 * @param <T> - T
 */
public interface Menu<T> extends Animation {
    /**
     * The method add selection.
     * @param key - the key to press
     * @param message - the massage on the board game
     * @param returnVal - the screen to show
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * Return what is the next board.
     * @return the next board
     */
    T getStatus();
    /**
     * The method add Sub Menu.
     * @param key - the key to press
     * @param message - the massage on the board game
     * @param subMenu - the screen to show
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
