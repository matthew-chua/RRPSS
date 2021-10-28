package Controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import Boundary.ReservationBoundary;
import Entity.ReservationEntity;
import Helpers.*;

public class ReservationController {

    private ReservationBoundary view;
    private ReservationEntity reservation;
    private static ArrayList<ReservationEntity> reservationList;
    private int tableCount = 10;
    Scanner sc = new Scanner(System.in);

    public ReservationController() {
        this.view = new ReservationBoundary();

        this.reservationList = new ArrayList<ReservationEntity>();

        this.start();
    }

    private void start() {
        

        /* Selection Prompt */
        // int choice;
        
        // choice = sc.nextInt();
        view.getUserReservationChoice(choice -> {
            switch (choice) {
                case 1: /* remove reservation */
                    findReservation();
                    break;
                case 2: /* show list of available tables */
                    checkTableAvailability(0);
                    break;
                case 3: /* create reservation */
                    checkTableAvailability(1);
                    break;
                case 0: /* Back to main menu */
                    break;
                }
        });

        // findReservation();
    }

    /* Find then remove reservation */
    public void findReservation() {
        // System.out.println("=============== Find Reservations ===============");
        // System.out.println("Enter the name of reserver:");
        // String name = sc.nextLine();
        view.getUserReservationName(reservationName -> {
            /* Get list of reservations */
            getReservationsByName(reservationName);

            System.out.println("1. Cancel reservation");
            System.out.println("0. Return");

            int choice;
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
            case 1:
                /* remove reservation of current customer */
                removeReservation(reservationName);
                break;
            case 0:
                break;
            default:
                break;
            }
        });
    }



    /* Print reservations made by specific customer */
    public void getReservationsByName(String name) {
        ArrayList<ReservationEntity> tempList = new ArrayList<ReservationEntity>();
        for (ReservationEntity i : reservationList) {
            if (i.getName() == name) {
                tempList.add(i);
            }
        }

        view.printReservations(tempList, name);
    }

    /* Remove one reservation by specific customer */
    public void removeReservation(String name) {

        String dateTime = view.getUserDateTime("Remove Reservation");
        // not optimal but ok
        String[] dateTimeList = dateTime.split(" ");
        
        for (ReservationEntity i : reservationList) {
            if (i.getName() == name && i.getDate() == dateTimeList[0] && i.getTime() == dateTimeList[1]) {
                i.printReservation();
                reservationList.remove(i);
                System.out.println("Reservation succesfully removed");
                return;
            }
        }
        System.out.println("Reservation does not exist");
    }

    /* Takes in input based on whether customer wants to simply check availability or directly make reservation */
    public void checkTableAvailability(int makeReservation) {
        
        String dateTime = view.getUserDateTime("Remove Reservation");
        String[] dateTimeList = dateTime.split(" ");
        String date = dateTimeList[0];
        String time = dateTimeList[1];
        // System.out.println("Enter the date (DD/MM/YYYY):");
        // String date = sc.nextLine();

        // System.out.println();
        // System.out.println("Enter the time (HHMM 24hr):");
        // String time = sc.nextLine();

        // System.out.println();
        System.out.println("Enter the number of people:");
        System.out.println("Max: 10, Min: 2");
        int pax = sc.nextInt();

        /* Check if for that date and time, whether all tables are used up */
        int reservationCount = 0;
        for (ReservationEntity i : reservationList) {
            if (i.getDate() == date && i.getTime() == time && i.getPax() == pax) {
                reservationCount++;
            }
        }

        if (tableCount - reservationCount == 0) {
            System.out.println("No available tables");
            return;
        } 
        else if (makeReservation == 0) {
            System.out.println("1. Reserve table");
            System.out.println("2. Change date");
            System.out.println("0. Return");
            int input = sc.nextInt();
            switch (input) {
            case 1:
                makeReservation = 1;
                break;
            case 2:
                /* Allow customer to enter another date and time, not sure if this recursion will break the system */
                checkTableAvailability(0);
                makeReservation = 0;
                break;
            case 0:
                break;
            default:
                break;
            }
        }

        /* Customer may not want to make reservation */
        if (makeReservation == 1) {
            createReservation(date, time, pax);
        }
    }

    /* On confirmation of making reservation */
    public void createReservation(String date, String time, int pax) {
        System.out.println();
        System.out.println("Enter the name of reserver:");
        String name = sc.nextLine();

        System.out.println();
        System.out.println("Enter the contact of reserver:");
        String contact = sc.nextLine();

        ReservationEntity reservation = new ReservationEntity(date, time, pax, name, contact);
        reservationList.add(reservation);
        reservation.printReservation();
    }
}