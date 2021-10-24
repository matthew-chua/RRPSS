package Boundary;
import Helpers.*;
import Entity.ReservationEntity;
import java.util.ArrayList;

public class ReservationBoundary extends Boundary {
    public ReservationBoundary() {}

    /* =========== Manage Reservations Title =========== */
    private String manageReservationsTitle = separators + " Manage Reservations " + separators + "\n";

    
    // Get User's Choice for which manager they would like
    public void getUserReservationChoice(ChoiceObserver callback){
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
    public void getUserReservationName(StringObserver callback){

        String findReservationTitle = "=============== Find Reservations ===============\n";
        String getReservationName = "Enter the name of reserver:\n";
        String stringToPrint = findReservationTitle + getReservationName;

        getStringInput(callback, false, stringToPrint);
    }

    public void printReservations(ArrayList<ReservationEntity> tempList, String name){
        if (tempList.size() == 0) {
            System.out.printf("%s currently has no reservations\n", name);
        } else {
            System.out.printf("Our records show that %s has reservation(s) on\n", name);
            int index = 1;
            for (ReservationEntity j : tempList) {
                System.out.printf("%d. %s at %s", index, j.getDate(), j.getTime());
                index++;
            }
        }
    }

    public String getUserDateTime(String title){
        System.out.println(separators + " " + title + " " + separators);

        System.out.println("Enter date of reservation (DD/MM/YYYY):");
        // String date = sc.nextLine();
        String date = getStringInput();
        // chee bai how to validate like that

        System.out.println();
        System.out.println("Enter time of reservation (HHMM 24hr):");
        // String time = sc.nextLine();
        String time = getStringInput();

        // getStringInput(callback, isRecurring, stringToPrint);
        return date + " " + time;
    }
}

