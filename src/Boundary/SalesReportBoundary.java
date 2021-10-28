package Boundary;
import Helpers.*;

public class SalesReportBoundary extends Boundary {

    public SalesReportBoundary() {}

    // Strings
    private String salesReportChoiceString = separators + " Print Sales Revenue Report by Period " + separators + "\n" + "Please select a period:\n1. Print sale revenue report by day\n2. Print sale revenue report by month\n" + "Otherwise, enter 0 to return back to the main menu";

    // Get User's Choice for which manager they would like
    public void getUserManagerChoice(ChoiceObserver callback){
        // resetUI();
        // Print the instructions
        // System.out.println(getUserManagerChoiceString);

        // Get the user's choice
        int numberOfChoices = 2;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, salesReportChoiceString);
        // Get User Choices is defined in parent class, Boundary.
    }

}
