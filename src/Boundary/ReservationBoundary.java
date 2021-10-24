package Boundary;

import Helpers.*;

public class ReservationBoundary extends Boundary {
    public ReservationBoundary() {}

    /* =========== Manage Reservations Title =========== */
    private String manageReservationsTitle = separators + " Manage Reservations " + separators + "\n";

    
    // Get User's Choice for which manager they would like
    public void getUserReservationChoice(Callback callback){
        /* =========== User's Reservation choices =========== */
        String choice1String = "1. Find / Remove reservation booking\n";
        String choice2String = "2. Check table availability for reservations\n";
        String choice3String = "3. Create a reservation\n";
        String choice4String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = manageReservationsTitle + choice1String + choice2String + choice3String + choice4String;

        // Get the user's choice
        int numberOfChoices = 3;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    /* =========== Find Reservation =========== */
    public void getUserReservationName(Callback callback){

        String findReservationTitle = "=============== Find Reservations ===============\n";
        String getReservationName = "Enter the name of reserver:\n";
        String stringToPrint = findReservationTitle;

        getStringInput(callback, true, stringToPrint);

    }


}
