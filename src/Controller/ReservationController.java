package Controller;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Collectors;

import Boundary.ReservationBoundary;
import Entity.ReservationEntity;
import Entity.RestaurantEntity;
import Helpers.*;

public class ReservationController {

    private ReservationBoundary view;
    private ReservationEntity reservation;
    private RestaurantEntity restaurant;
    private ArrayList<ReservationEntity> existingReservations;

    private boolean status = false;
    private RestaurantDataType type = RestaurantDataType.RESERVATION;

    public ReservationController() {
        this.view = new ReservationBoundary();
        this.restaurant = RestaurantEntity.getInstance();
        this.existingReservations = restaurant.getReservations();
        this.start();
    }

    private void start() {
        updateReservations();
        view.getUserReservationChoice(choice -> {
            switch (choice) {
            case 1: /* remove reservation */
                findReservation();
                break;
            case 2: /* show number of available tables */
                checkTableAvailability("Check Table Availability", false);
                break;
            case 3: /* create reservation */
                checkTableAvailability("Create Reservation", true);
                break;
            case 0: /* Back to main menu */
                break;
            }
        });
    }

    /* Find then remove reservation */
    public void findReservation() {
        updateReservations();
        view.getUserReservationName(reservationName -> {
            /* Get list of reservations */

            ArrayList<ReservationEntity> tmpList = new ArrayList<ReservationEntity>(existingReservations.stream()
                    .filter(res -> res.getName().equals(reservationName)).collect(Collectors.toList()));
            
            view.printReservations(tmpList, reservationName);
            if (tmpList.size() != 0) {
                view.getUserRemoveChoice(choice -> {
                    switch (choice) {
                    case 1:
                        removeReservation(tmpList, reservationName);
                        break;
                    case 0:
                        break;
                    }
                });
            }
            return;
        });
    }

    /* Remove reservation using indexing */
    public void removeReservation(ArrayList<ReservationEntity> tmpList, String name) {
        view.printReservations(tmpList, name);
        view.getUserReservationIndex(tmpList.size(), index -> {
            try {
                existingReservations.remove(tmpList.get(index - 1));
                status = true;
            }
            catch (NoSuchFieldError e) {}
        });
        view.reservationCancellationStatus(status);
        restaurant.setReservations(existingReservations);
    }

    /* Check table availability */
    public void checkTableAvailability(String stringToPrint, boolean create) {
        updateReservations();
        Date date = view.getUserReservationDate();
        Date time = view.getUserReservationTime();

        /* filter date */
        ArrayList<ReservationEntity> dateList = new ArrayList<ReservationEntity>(
                existingReservations.stream().filter(res -> res.getDate().equals(date)).collect(Collectors.toList()));
        /* filter time */
        ArrayList<ReservationEntity> tmpList = new ArrayList<ReservationEntity>(
                dateList.stream().filter(res -> res.getTime().equals(time)).collect(Collectors.toList()));

        view.checkTableAvailabilityStatus(tmpList.size());

        if (tmpList.size() == 0) {
            if (!create){
                view.getUserCreateChoice(choice -> {
                    switch (choice) {
                    case 1:
                        createReservation(date, time);
                        break;
                    case 0:
                        break;
                    }
                });
            }
            else{
                createReservation(date, time);
            }
        }
    }

    /* Making reservation */
    public void createReservation(Date date, Date time) {
        
        String reservationName = view.getUserReservationName();

        String reservationContact = view.getUserReservationContact();

        int reservationPax = view.getUserReservationPax();

        ReservationEntity reservation = new ReservationEntity(date, time, reservationPax, reservationName, reservationContact);

        existingReservations.add(reservation);
        restaurant.setReservations(existingReservations);
    }

    public void updateReservations() {
        return;
    }
}