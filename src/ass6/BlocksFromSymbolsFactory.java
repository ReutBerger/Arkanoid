package ass6;

import java.util.Map;

/**
 * A BlocksFromSymbolsFactory class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 17/6/19
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    /**
     * Construct a BlocksFromSymbolsFactory given two maps.
     * @param spacerWidths - the spacerWidths map
     * @param blockCreators - the blockCreators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }
    /**
     * Returns true if 's' is a valid space symbol.
     * @param s - symbol
     * @return true if 's' is a valid space symbol
     */
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.containsKey(s)) {
             return true;
        }
        return false;
    }
    /**
     * Returns true if 's' is a valid block symbol.
     * @param s - symbol
     * @return true if 's' is a valid block symbol
     */
    public boolean isBlockSymbol(String s) {
        if (blockCreators.containsKey(s)) {
             return true;
        }
        return false;
    }
    /**
     * Return a block according to the definitions associated with symbol s.
     * The block will be located at position (xpos, ypos).
     * @param s - symbol
     * @param xpos - x position
     * @param ypos - y position
     * @return a block according to the definitions associated with symbol s
     */
    public Block getBlock(String s, int xpos, int ypos) {
        Block b = blockCreators.get(s).create(xpos, ypos);
        return b;
    }
    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s - the symbol
     * @return the width in pixels associated with the given spacer-symbol
     */
    public int getSpaceWidth(String s) {
       return spacerWidths.get(s);
    }
}
