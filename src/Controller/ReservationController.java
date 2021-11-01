package Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import Boundary.ReservationBoundary;
import Entity.ReservationEntity;
import Entity.RestaurantEntity;
import Helpers.*;

public class ReservationController {

    private ReservationBoundary view;
    private ReservationEntity reservation;
    private RestaurantEntity restaurant;
    private ArrayList<ReservationEntity> workingList;
    private ArrayList<ReservationEntity> existingReservations;
    Scanner sc = new Scanner(System.in);

    boolean status = false;

    private RestaurantDataType type = RestaurantDataType.RESERVATION;

    public ReservationController() {
        this.view = new ReservationBoundary();
        this.restaurant = RestaurantEntity.getInstance();
        this.existingReservations = restaurant.getReservations();
        this.workingList = new ArrayList<ReservationEntity>();
        this.start();
    }

    private void start() {
        // System.out.println("existing reservation");
        // System.out.println(existingReservations);
        // updateReservations();
        view.getUserReservationChoice(choice -> {
            switch (choice) {
            case 1: /* remove reservation */
                findReservation();
                break;
            case 2: /* show number of available tables */
                checkTableAvailability();
                break;
            case 3: /* create reservation */
                createReservation();
                break;
            case 0: /* Back to main menu */
                break;
            }
        });
    }

    /* Find then remove reservation */
    public void findReservation() {
        view.getUserReservationName(reservationName -> {
            /* Get list of reservations */

            // workrestaurant.getList(RestaurantDataType.RESERVATION);

            // for (ReservationEntity i : restaurant.getReservations()) {
            //     if (i.getName() == reservationName) {
            //         workingList.add(i);
            //     }
            // }

            // ArrayList<ReservationEntity> tmpList = existingReservations.stream().filter(res -> res.getName() == reservationName)

            // int index = existingReservations.indexOf(o)

            view.printReservations(workingList, reservationName);

            if (workingList.size() == 0) {
                System.out.println("No reservations found. Press enter to continue");
                sc.nextLine();
                return;
            } else {
                view.getUserRemoveChoice(choice -> {
                    switch (choice) {
                    case 1:
                        removeReservation(workingList, reservationName);
                        break;
                    case 0:
                        break;
                    }
                });
            }
        });
    }

    /* Remove one reservation by specific customer */
    public void removeReservation(ArrayList<ReservationEntity> workingList, String name) {

        view.printReservations(workingList, name);
        view.getUserReservationIndex(workingList.size(), index -> {
            for (int i = 0; i < existingReservations.size(); i++) {
                if (existingReservations.get(i) == workingList.get(index)) {
                    restaurant.removeDataFromList(RestaurantDataType.RESERVATION, i);
                    workingList.clear();
                    existingReservations = restaurant.getReservations();
                    status = true;
                    break;
                }
            }
        });
        view.reservationCancellationStatus(status);
    }

    /* */
    public void checkTableAvailability() {

        String dateTime = view.getUserDateTime("Check Table Availability");
        String[] dateTimeList = dateTime.split(" ");
        String date = dateTimeList[0];
        String time = dateTimeList[1];

        System.out.println("Enter the number of people:");
        System.out.println("Max: 10, Min: 2");
        int pax = sc.nextInt();

        /* Check if for that date and time, whether all tables are used up */
        int reservationCount = 0;
        // for (ReservationEntity i :
        // restaurant.getList(RestaurantDataType.RESERVATION)) {
        // if (i.getDate() == date && i.getTime() == time /* && i.getPax() == pax */) {
        // // should check 30mins before specified time too
        // reservationCount++;
        // }
        // }
        int availableTables = existingReservations.size() - reservationCount;
        view.checkTableAvailabilityStatus(availableTables);
    }

    /* Making reservation */
    public void createReservation() {
        int pax = 5;

        String dateTime = view.getUserDateTime("Check Table Availability");
        String[] dateTimeList = dateTime.split(" ");
        String date = dateTimeList[0];
        String time = dateTimeList[1];

        System.out.println();
        System.out.println("Enter the name of reserver:");
        String name = sc.nextLine();

        System.out.println();
        System.out.println("Enter the contact of reserver:");
        String contact = sc.nextLine();

        ReservationEntity reservation = new ReservationEntity(date, time, pax, name, contact);
        restaurant.addDataToList(RestaurantDataType.RESERVATION, reservation);
        reservation.printReservation();
    }

    // public void updateReservations() {
    // for (int i = 0; i < existingReservations.size(); i++) {

    // // if (restaurant.getList(RestaurantDataType.RESERVATION).get(i).getTime() <
    // // Calendar.getInstance().getTime()){
    // // restaurant.removeDataFromList(RestaurantDataType.RESERVATION, i);
    // // i--;
    // // }
    // }
    // }
}