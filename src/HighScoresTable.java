package ass6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A HighScoresTable class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 11/6/19
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreList;
    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     * @param size - the size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<ScoreInfo>(size);
    }
    /**
     * The method adds high score and sort the list.
     * @param score - the score
     */
    public void add(ScoreInfo score) {
        this.scoreList.add(score);
        this.scoreList = sort(this.scoreList);
        while (this.scoreList.size() > this.size) {
            this.scoreList.remove(this.scoreList.size() - 1);
        }
    }
    /**
     * Return the table size.
     * @return the size.
     */
    public int size() {
        return this.size;
    }
    /**
     * Return the current high scores.
     * The list is sorted such that the highest scores come first.
     * @return the current high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreList;
    }
    /**
     * Return the rank of the current score.
     * @param score - the score
     * @return the rank of the current score
     */
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.scoreList.size(); i++) {
            if (score > this.scoreList.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
    }
    /**
     * The method Clears the table.
     */
    public void clear() {
        this.scoreList.clear();
    }
    /**
     * The method load table data from file and the current table data is cleared.
     * @param filename - the file to read from
     * @throws IOException - IOException
     */
    public void load(File filename) throws IOException {
        FileReader fileR = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fileR);
        String line;
        while ((line = reader.readLine()) != null) {
            ScoreInfo sI;
            int index = line.indexOf(" ");
            String name = line.substring(0, index);
            String score = line.substring(index + 1, line.length());
            int sc = Integer.parseInt(score);
            sI = new ScoreInfo(name , sc);
            this.scoreList.add(sI);
        }
        reader.close();
    }
    /**
     * The method Save table data to the specified file.
     * @param filename - the file
     * @throws IOException - IOException
     */
    public void save(File filename) throws IOException {
        FileWriter fileW = new FileWriter(filename);
        for (ScoreInfo s : this.scoreList) {
            fileW.write(s.getName() + " " + s.getScore() + "\n");
        }
        fileW.close();
    }
    /**
     * The method read a table from file and return it.
     * @param filename - the file
     * @return the table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable h = new HighScoresTable(Ass7Game.SIZE);
        try {
            h.load(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return h;
    }
    /**
     * The method sort the list.
     * @param scoreList1 - the list to sort
     * @return the list after sort
     */
    public List<ScoreInfo> sort(List<ScoreInfo> scoreList1) {
        ScoreInfo temp;
        for (int i = 0; i < scoreList1.size(); i++) {
            for (int j = 1; j < (scoreList1.size() - i); j++) {
                if (scoreList1.get(j - 1).getScore() < scoreList1.get(j).getScore()) {
                    // swap elements
                    temp = scoreList1.get(j - 1);
                    scoreList1.set(j - 1, scoreList1.get(j));
                    scoreList1.set(j, temp);
                }
            }
        }
        return scoreList1;
    }
}
