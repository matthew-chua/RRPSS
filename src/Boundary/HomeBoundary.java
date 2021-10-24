package Boundary;
import Helpers.*;

public class HomeBoundary extends Boundary {
    public HomeBoundary(){

    }

    private static String getUserManagerChoiceString = "Hi bobberino tan ah gua, what would you like to do today?\n1. Manage Menu\n2. Manage Orders\n3. Manage Reservations\n4. Manage Sales\n5. Change Staff\n";

    // Get User's Choice for which manager they would like
    public void getUserManagerChoice(Callback callback){
        // resetUI();
        // Print the instructions
        // System.out.println(getUserManagerChoiceString);

        // Get the user's choice
        int numberOfChoices = 5;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, getUserManagerChoiceString);
        // Get User Choices is defined in parent class, Boundary.
    }



}
