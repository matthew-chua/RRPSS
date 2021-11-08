package Boundary;

import Helpers.*;
import Entity.ReservationEntity;
import Entity.Table;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    // Get User's Choice for which manager they would like
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
    public void getUserReservationName(StringObserver callback) {

        String findReservationTitle = "=============== Find Reservations ===============\n";
        String getReservationName = "Enter the name of reserver:\n";
        String stringToPrint = findReservationTitle + getReservationName;

        getStringInput(callback, false, stringToPrint);
    }

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

    public void printReservations(ArrayList<ReservationEntity> tempList, String name) {
        // String stringToPrint = "";
        // if (tempList.size() != 0) {
        //     stringToPrint += "Our records show that " + name + " has reservation(s) on: \n";
        //     // System.out.printf("Our records show that %s has reservation(s) on: \n", name);
        //     int index = 1;
        //     for (ReservationEntity j : tempList) {
        //         // System.out.printf("%d. %s at %s\n", index, dateFormat.format(j.getDate()), timeFormat.format(j.getTime()));
        //         stringToPrint += index + ". " + dateFormat.format(j.getDate()) + " at " + timeFormat.format(j.getDate());
        //         index++;
        //     }
        //     // stringToPrint = " ";
        // }else{
        //     stringToPrint = "No reservations found.";
        // }
        String stringToPrint = getReservationString(tempList, name);
        resetUI();
        displayResults(findReservationTitle + stringToPrint);
    }

    /* =========== Remove Reservation =========== */
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

    public void getUserReservationIndex(ArrayList<ReservationEntity> tempList, String name, ChoiceObserver callback) {
        
        String reservationString = getReservationString(tempList, name);
        String stringToPrint = "Which reservation index is to be removed?\n";
        int numberOfChoices = tempList.size();
        boolean isRecurring = false;
        getUserChoices(numberOfChoices, callback, isRecurring, removeReservationTitle + reservationString + stringToPrint);
    }

    public void reservationCancellationStatus(boolean status) {
        // if (status) {
        //     System.out.print("Reservation successfully removed\n");
        // } else {
        //     System.out.print("Error, reservation not found\n");
        // }
        resetUI();
        displayResults(removeReservationTitle + "Reservation successfully removed");
    }

    public void checkTableAvailabilityStatus(int availableTables) {
        if (availableTables == 0) {
            System.out.print("There are no slots available.\n");
            return;
        } else {
            System.out.printf("There are %d available.\n", availableTables);
        }
    }

    private String availableTablesString = "";

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

    public String getUserReservationName(){
        System.out.println("Enter name of reserver: ");
        return getStringInput();
    }

    public int getUserReservationPax() {
        System.out.println("Enter number of people (2 - 10): ");
        return getPositiveInt();
    }

    public String getUserReservationContact() {
        System.out.println("Enter contact number: ");
        return getStringInput();
    }
}
