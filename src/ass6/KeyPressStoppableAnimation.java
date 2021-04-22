package ass6;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * A KeyPressStoppableAnimation class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 28/5/19
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    // Constructor-
    /**
     * Construct a KeyPressStoppableAnimation given KeyboardSensor, key and animation.
     * @param sensor - the KeyboardSensor
     * @param key - the key
     * @param animation - the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * The method is in charge of the logic.
     * @param d - the DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        // If the key is not pressed, set isAlreadyPressed=false
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        // If the key was pressed before the animation started, we ignore the key press.
        if (!this.isAlreadyPressed && this.keyboard.isPressed(this.key)) {
            this.stop = true;
        }
    }
    /**
     * The method is in charge of stopping condition.
     * @return if the animation should stop or not
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
