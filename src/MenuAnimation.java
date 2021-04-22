package ass6;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * A MenuAnimation<T> class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 13/6/19
 * @param <T> - T
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private KeyboardSensor keyboard;
    private List<String> messages;
    private List<String> keys;
    private List<T> returns;
    private boolean stop;
    private T status;
    /**
     * Construct a MenuAnimation given menu title and keyboard.
     * @param menuTitle - the menu title
     * @param key - the keyboard
     */
    public MenuAnimation(String menuTitle, KeyboardSensor key) {
        this.menuTitle = menuTitle;
        this.keyboard = key;
        this.stop = false;
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.returns = new ArrayList<T>();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        d.drawText(50, 50, this.menuTitle, 35);
        int j = 0;
        d.setColor(Color.blue);
        for (String optionString : messages) {
            d.drawText(150, 200 + j, optionString, 30);
            j += 60;
        }
        int m = 0, i = 0;
        for (String option1 : keys) {
            d.drawText(100, 200 + i, "(" + option1 + ")  ", 30);
            if (this.keyboard.isPressed(option1)) {
                this.status = this.returns.get(m);
                this.stop = true;
            }
            i += 60;
            m += 1;
        }
    }
    @Override
    public boolean shouldStop() {
        return stop;
    }
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returns.add(returnVal);
    }
    @Override
    public T getStatus() {
        this.stop = false;
        return status;
    }
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
    }
}
