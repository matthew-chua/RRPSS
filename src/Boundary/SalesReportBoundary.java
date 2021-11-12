package Boundary;
import Helpers.*;

/**
 * 
 * Is the boundary that is involved with flows on the Sales Report screens.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class SalesReportBoundary extends Boundary {

    public SalesReportBoundary() {}

    // Strings
    /** The UI to display to the user to choose their action */
    private String salesReportChoiceString = separators + " Print Sales Revenue Report by Period " + separators + "\n" + "Please select a period:\n1. Print sale revenue report by day\n2. Print sale revenue report by month\n";

    /**
     * Get User's Choice for which manager they would like
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserManagerChoice(ChoiceObserver callback){
        
        // Get the user's choice
        int numberOfChoices = 2;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, salesReportChoiceString);
    }

}
