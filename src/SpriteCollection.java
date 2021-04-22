package ass6;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * A SpriteCollection class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Construct a SpriteCollection - create an empty list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * The method add the given sprite to the list.
     * @param s - a new sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * The method call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }
    /**
     * The method call drawOn(d) on all sprite - draw the all elements.
     * @param d - the drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
    /**
     * The method remove the sprite from the list of the sprites.
     * @param s - the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}

