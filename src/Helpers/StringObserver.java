package Helpers;

/**
 * 
 * Anonymous Callback function class for string inputs
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public interface StringObserver {

    /**
     * Callback function to be called on valid input of String
     * @param input    the valid string that user has inputted
     */
    void userDidEnterString(String input);
}
