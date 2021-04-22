package ass6;

import biuoop.KeyboardSensor;
/**
 * A ShowHiScoresTask class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 17/6/19
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;
    /**
     * Construct a ShowHiScoresTask.
     * @param runner - AnimationRunner
     * @param highScoresAnimation - Animation
     * @param ks - KeyboardSensor
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.ks = ks;
    }
    /**
     * The method run the high Scores Animation.
     * @return null - void
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}
