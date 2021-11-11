package Boundary;

import Helpers.*;

/**
 * 
 * Is the boundary that is involved with flows on the Home screens.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class HomeBoundary extends Boundary {
    public HomeBoundary() {

    }    
    
    /**
     * Get User's Choice for which manager they would like
     * @param name  is a string which represents the name of staff
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserManagerChoice(String name, ChoiceObserver callback) {
        // resetUI();
        String getUserManagerChoiceString = "Hi " + name + ", what would you like to do today?\n1. Manage Menu\n2. Manage Orders\n3. Manage Reservations\n4. Manage Sales\n";

        // Print the instructions

        // Get the user's choice
        int numberOfChoices = 4;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, getUserManagerChoiceString);
        
        // Get User Choices is defined in parent class, Boundary.
        
    }

}
