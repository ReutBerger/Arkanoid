package ass6;

import java.io.File;
import java.io.IOException;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
/**
 * An Ass6Game class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 31/3/19
 */
public class Ass7Game {
    public static final int SIZE = 5;
    /**
     * The main - creates and starts the game.
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        Sleeper sleep = new Sleeper();
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();
        String setLevels;
        //List<LevelInformation> levels = new ArrayList<LevelInformation>();
        File highScoreFile = new File("highscores.txt");
        try {
            highScoreFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        HighScoresTable highScoreTable = HighScoresTable.loadFromFile(highScoreFile);
        if (args.length > 0) {
            setLevels = args[0];
        } else {
            setLevels = null;
        }
        Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>("Arkanoid", gui.getKeyboardSensor());
        // To run the start game option
        Task<Void> startGame = new SubMenuAnimation("Level Sets", ar, ks, gui, sleep, highScoreTable, setLevels);
        mainMenu.addSelection("s", "Start Game", startGame);
        // To run the high score option
        HighScoresAnimation hiScore = new HighScoresAnimation(highScoreTable);
        mainMenu.addSelection("h", "High Score", new ShowHiScoresTask(ar, hiScore, ks));
        // To run the quit option
        Task<Void> quit = new Task<Void>() {
            public Void run() {
                gui.close();
                System.exit(0);
                return null;
            }
        };
        mainMenu.addSelection("q", "Quit", quit);
        // Run the menu
        while (true) {
            ar.run(mainMenu);
            // wait for user selection
            Task<Void> task = mainMenu.getStatus();
            task.run();
        }
    }
}
