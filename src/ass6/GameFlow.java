package ass6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
/**
 * A GameFlow class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 27/5/19
 */
public class GameFlow implements Task<Void> {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Sleeper sleeper;
    private Counter currentScore;
    private boolean isWin;
    private String file;
    private HighScoresTable table;
    public static final int START_NUM_OF_LIFE = 7;
    // Constructor-
    /**
     * Construct a GameFlow given animationRunner, keyboardSensor, gui, sleeper, highScoresTable and file.
     * @param ar - the animationRunner
     * @param ks - the keyboardSensor
     * @param gui - the gui
     * @param sleeper - the sleeper
     * @param highScoresTable - the highScoresTable
     * @param file - the file
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Sleeper sleeper,
                    HighScoresTable highScoresTable, String file) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.sleeper = sleeper;
        this.currentScore = new Counter();
        this.isWin = true;
        this.table = highScoresTable;
        this.file = file;
    }
    /**
     * The method run the levels one by one.
     * @param levels - a list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter numOfLives = new Counter();
        numOfLives.increase(START_NUM_OF_LIFE);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                                            this.gui, this.currentScore, numOfLives, this.sleeper);
            level.setPaddleSpeed(levelInfo.paddleSpeed());
            int[] hits = new int[levelInfo.numberOfBlocksToRemove()];
            int i = 0;
            for (Block b : levelInfo.blocks()) {
                hits[i] = b.getHitPoints();
                i++;
            }
            level.initialize();
            //level has more blocks and player has more lives
            while (level.getNumOfBlocks() != 0 && level.getNumOfLives() != 0) {
                level.playOneTurn();
            }
            i = 0;
            for (Block b : levelInfo.blocks()) {
                b.setHit(hits[i]);
                i++;
            }
            ///no more lives
            if (level.getNumOfLives() == 0) {
                this.isWin = false;
                break;
            }
        }
        this.addToTable();
        EndScreen end = new EndScreen(this.currentScore, this.isWin);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, end));
        HighScoresAnimation scoreAnimation = new HighScoresAnimation(this.table);
        this.animationRunner.run(new KeyPressStoppableAnimation(
                                this.keyboardSensor, KeyboardSensor.SPACE_KEY, scoreAnimation));
    }
    /**
     * The method add the table score to the game.
     */
    public void addToTable() {
        int score = this.currentScore.getValue();
        if (this.table.getRank(score) <= this.table.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Enter Name", "What is your name?", "Anonymous");
            this.table.add(new ScoreInfo(name, score));
            File highScoresFile = new File("highscores.txt");
            try {
                this.table.save(highScoresFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public Void run() {
        List<LevelInformation> level;
        LevelSpecificationReader l = new LevelSpecificationReader();
        FileReader reader = null;
        try {
            reader = new FileReader("resources/" + this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        level = l.fromReader(reader);
        this.runLevels(level);
        return null;
    }
}
