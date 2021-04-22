package ass6;
/**
 * A BlockCreator interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 17/6/19
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos - x position
     * @param ypos - y position
     * @return the new block
     */
    Block create(int xpos, int ypos);
}
