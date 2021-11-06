package Boundary;

import Helpers.*;

public class HomeBoundary extends Boundary {
    public HomeBoundary() {

    }

    

    // Get User's Choice for which manager they would like
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
