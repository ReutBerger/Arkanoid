package ass6;
/**
 * A ScoreInfo class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 11/6/19
 */
public class ScoreInfo {
    private String name;
    private int score;
    // Constructor-
    /**
     * Construct a new ScoreInfo given name and score.
     * @param name - the name
     * @param score - the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * Return the name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Return the score.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }
}
