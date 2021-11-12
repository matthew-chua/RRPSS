package Boundary;

import Helpers.*;
import Entity.ReservationEntity;
import Entity.Table;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * Is the boundary that is involved with flows on the Reservation screens.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class ReservationBoundary extends Boundary {
    public ReservationBoundary() {
    }

    /* =========== Manage Reservations Title =========== */
    private String manageReservationsTitle = separators + " Manage Reservations " + separators + "\n";
    private String findReservationTitle = separators + " Find Reservation " + separators + "\n";
    private String removeReservationTitle = separators + " Remove Reservation " + separators + "\n";
    private String createReservationTitle = separators + " Create Reservation " + separators + "\n";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    /**
     * Get User's Choice for which manager they would like
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserReservationChoice(ChoiceObserver callback) {
        /* =========== User's Reservation choices =========== */
        String choice1String = "1. Find / Remove reservation booking\n";
        String choice2String = "2. Check table availability for reservations\n";
        String choice3String = "3. Make a reservation\n";
        String choice4String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = manageReservationsTitle + choice1String + choice2String + choice3String + choice4String;

        // Get the user's choice
        int numberOfChoices = 3;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    /* =========== Find Reservation =========== */
    /**
     * Get the name of the customer that booked the reservation
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserReservationName(StringObserver callback) {

        String findReservationTitle = "=============== Find Reservations ===============\n";
        String getReservationName = "Enter the name of reserver:\n";
        String stringToPrint = findReservationTitle + getReservationName;

        getStringInput(callback, false, stringToPrint);
    }

    /**
     * Transforms an ArrayList of ReservationEntity into a formatted string of all reservations
     * @param tempList      is an ArrayList of ReservationEntity representing all reservations belonging to a name
     * @param name          the name which reserved these reservations
     * @return  a formatted string that represents all reservations under a given name
     */
    String getReservationString (ArrayList<ReservationEntity> tempList, String name){
        String stringToPrint = "";
        if (tempList.size() != 0) {
            stringToPrint += "Our records show that " + name + " has reservation(s) on: \n";
            // System.out.printf("Our records show that %s has reservation(s) on: \n", name);
            int index = 1;
            for (ReservationEntity j : tempList) {
                // System.out.printf("%d. %s at %s\n", index, dateFormat.format(j.getDate()), timeFormat.format(j.getTime()));
                stringToPrint += index + ". " + dateFormat.format(j.getDate()) + " at " + timeFormat.format(j.getDate()) + " for " + String.valueOf(j.getPax()) + " people" + "\n";
                index++;
            }
            // stringToPrint = " ";
        }else{
            stringToPrint = "No reservations found.";
        }
        return stringToPrint + "\n";
    }

    /**
     * Display results for a list of reservations under a given name
     * @param tempList      is an ArrayList of ReservationEntity representing all reservations belonging to a name
     * @param name          is a String representing the name which reserved these reservations
     */
    public void printReservations(ArrayList<ReservationEntity> tempList, String name) {
        String stringToPrint = getReservationString(tempList, name);
        resetUI();
        displayResults(findReservationTitle + stringToPrint);
    }

    /* =========== Remove Reservation =========== */
    /**
     * Get the user's choice on whether they would like to remove a reservation
     * @param tempList      is an ArrayList of ReservationEntity representing the list of current reservations under a name
     * @param name          is a String representing the name which reserved these reservations
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserRemoveChoice(ArrayList<ReservationEntity> tempList, String name, ChoiceObserver callback) {
        /* =========== User's Cancellation choices =========== */

        // String option = "Reservation found. What would you like to do?\n";
        String reservationString = getReservationString(tempList, name);
        String option = "What would you like to do?\n";  
        String choice1String = "1. Remove reservation\n";
        // String choice2String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = findReservationTitle + reservationString + option + choice1String;

        // Get the user's choice
        int numberOfChoices = 1;
        boolean isRecurring = false;
        getUserChoices(numberOfChoices, callback, isRecurring, stringToPrint);
    }

    /**
     * Gets the index of the reservation that the user wishes to select / remove
     * @param tempList      is an ArrayList of ReservationEntity representing the list of current reservations under a name
     * @param name          is a String representing the name which reserved these reservations
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserReservationIndex(ArrayList<ReservationEntity> tempList, String name, ChoiceObserver callback) {
        
        String reservationString = getReservationString(tempList, name);
        String stringToPrint = "Which reservation index is to be removed?\n";
        int numberOfChoices = tempList.size();
        boolean isRecurring = false;
        getUserChoices(numberOfChoices, callback, isRecurring, removeReservationTitle + reservationString + stringToPrint);
    }

    /**
     * Display that reservation has been deleted
     */
    public void reservationCancellationStatus() {
        resetUI();
        displayResults(removeReservationTitle + "Reservation successfully removed");
    }

    /** A String of available tables */
    private String availableTablesString = "";

    /**
     * Get the user's choice on whether to create a reservation
     * @param date              is a Date object representing the chosen date to create the reservation on
     * @param availableTables   is an ArrayList of Table entity representing the available tables to reserved for the date
     * @param callback          is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserCreateChoice(Date date, ArrayList<Table> availableTables, ChoiceObserver callback) {
        /* =========== User's Create choices =========== */
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String info = "Available Tables for " + dateTimeFormat.format(date) + ": \n\n";



        availableTables.forEach(table -> {
            availableTablesString += "Table " + table.getNumber() + ": " + table.getCapacity() + " pax\n";
        });


        String choice1String = "\n1. Create reservation\n";
        // String choice2String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = createReservationTitle + info + availableTablesString + choice1String;

        availableTablesString = "";

        // Get the user's choice
        int numberOfChoices = 1;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, stringToPrint);
    }


    /**
     * Get's the user's input for the time to reserve a table on
     * @return returns a Date object with the time the user wishes to book a reservation for
     */
    public Date getUserReservationTime() {
        System.out.println("Enter time (HH:MM 24hr):");
        while(true){
            String input = getStringInput();
            try{
                Date inputTime = timeFormat.parse(input);
                return inputTime;
            }
            catch(ParseException e){
                continue;
            }
        }
    }

    /**
     * Get's the name to save the reservation under
     * @return  the String representing the name of reserver
     */
    public String getUserReservationName(){
        System.out.println("Enter name of reserver: ");
        return getStringInput();
    }

    /**
     * Get's the number of peopel to reserve the table for
     * @return  an int representing number of people to reserve the table for
     */
    public int getUserReservationPax() {
        System.out.println("Enter number of people (2 - 10): ");
        return getPositiveInt();
    }

    /**
     * Gets the reserver's contact number
     * @return  a String representing the contact number of the user without error checking
     */
    public String getUserReservationContact() {
        System.out.println("Enter contact number: ");
        return getStringInput();
    }
}
