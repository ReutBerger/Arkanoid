package ass6;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;


import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
/**
 * A SubMenuAnimation class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 19/6/19
 */
public class SubMenuAnimation implements Task<Void> {
    private String subMenuTitle;
    private KeyboardSensor keyboard;
    private AnimationRunner ar;
    private GUI gui;
    private Sleeper sleep;
    private HighScoresTable highScoresTable;
    private String setLevels;
    /**
     * Construct a SubMenuAnimation given the next param.
     * @param title - the title
     * @param ar - the animation runner
     * @param key - the keyboard
     * @param gui - the gui
     * @param sleep - the sleeper
     * @param highScoresTable - the highScoresTable
     * @param setLevels - the setLevels
     */
    public SubMenuAnimation(String title, AnimationRunner ar, KeyboardSensor key, GUI gui, Sleeper sleep,
                            HighScoresTable highScoresTable, String setLevels) {
        this.keyboard = key;
        this.subMenuTitle = title;
        this.ar = ar;
        this.gui = gui;
        this.sleep = sleep;
        this.highScoresTable = highScoresTable;
        this.setLevels = setLevels;
    }
    @Override
    public Void run() {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(this.subMenuTitle, this.keyboard);
        String line;
        Reader reader = null;
        if (this.setLevels == null) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("levels_set.txt");
            reader = new InputStreamReader(is);
        } else {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.setLevels);
            reader = new InputStreamReader(is);
        }
        LineNumberReader bufferedReader = new LineNumberReader(reader);
        try {
            String[] s = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (bufferedReader.getLineNumber() % 2 == 1) {
                    s = line.split(":");
                } else {
                    menu.addSelection(s[0], s[1], new GameFlow(this.ar, this.keyboard, this.gui,
                                                               this.sleep, this.highScoresTable, line));
                }
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
        while (true) {
        this.ar.run(menu);
        Task<Void> status = menu.getStatus();
        status.run();
        return null;
        }
    }
}
