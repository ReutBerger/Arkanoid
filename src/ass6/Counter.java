package ass6;

/**
 * A Counter class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 10/5/19
 */
public class Counter {
    private int num = 0;
    /**
     * The method add number to current count.
     * @param number - the new number
     */
    public void increase(int number) {
        this.num += number;
    }
    /**
     * The method subtract number from current count.
     * @param number - the new number
     */
    public void decrease(int number) {
        this.num -= number;
    }
    /**
     * Return the current count.
     * @return the current count
     */
    public int getValue() {
        return this.num;
    }
}
