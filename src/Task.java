package ass6;
/**
 * A Task interface.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 13/6/19
 * @param <T> - T
 */
public interface Task<T> {
    /**
     * The method run the task.
     * @return the T
     */
    T run();
}
