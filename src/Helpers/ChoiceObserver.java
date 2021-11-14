package Helpers;

/**
 * 
 * Anonymous Callback function class
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public interface ChoiceObserver {

    /**
     * Callback function to be called on valid input of int
     * @param choice    the valid integer that user has inputted
     */
    void userDidSelectChoice(int choice);
}
