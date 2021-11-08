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
import Entity.Table;
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
            
            // view.printReservations(tmpList, reservationName);
            if (tmpList.size() != 0) {
                view.getUserRemoveChoice(tmpList, reservationName, choice -> {
                    switch (choice) {
                    case 1:
                        removeReservation(tmpList, reservationName);
                        break;
                    case 0:
                        break;
                    }
                });
            }else{
                view.displayResults("No reservations found under " + reservationName);
            }
            return;
        });
    }

    /* Remove reservation using indexing */
    public void removeReservation(ArrayList<ReservationEntity> tmpList, String name) {
        // view.printReservations(tmpList, name);
        view.getUserReservationIndex(tmpList, name, index -> {
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
        Date date = view.getUserDate();
        if (date == null) return;
        Date time = view.getUserReservationTime();

        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(time);

        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));
        calendarDate.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
        calendarDate.set(Calendar.SECOND, calendarTime.get(Calendar.SECOND));
        // view.displayResults(date.toString() + "\nTime:" + time.toString() );
        // date = calendarDate.getTime();
        
        // check that there isnt a reservation two hours before
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY) - 2);
        Date twoHoursBefore = calendarDate.getTime();
        // check that there isnt a reservation two hours before
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY) + 2);
        Date twoHoursAfter = calendarDate.getTime();

        // set back the time
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));

        /* filter date */
        ArrayList<ReservationEntity> dateList = new ArrayList<ReservationEntity>(
                existingReservations.stream().filter(res -> {
                    // view.displayResults("saved date: " + res.getDate().toString() + "\ntwo hours before: " + twoHoursBefore.toString());
                    boolean isOccupied2HoursBefore = (res.getDate().compareTo(twoHoursBefore) > 0 && res.getDate().compareTo(calendarDate.getTime()) <= 0);
                    boolean isOccupied2HoursAfter = (res.getDate().compareTo(calendarDate.getTime()) > 0 && res.getDate().compareTo(twoHoursAfter) <= 0);
                    // view.displayResults(String.valueOf(isOccupied2HoursBefore || isOccupied2HoursAfter));
                    // JUST LEFT THIS PART
                    return (isOccupied2HoursAfter || isOccupied2HoursBefore);
                    }
                ).collect(Collectors.toList()));
        /* filter time */
        // ArrayList<ReservationEntity> tmpList = new ArrayList<ReservationEntity>(
                // dateList.stream().filter(res -> res.getTime().equals(time)).collect(Collectors.toList()));

        // view.checkTableAvailabilityStatus(dateList.size());

        if (dateList.size() < restaurant.getTables().size()) {
            if (!create){
                view.getUserCreateChoice(calendarDate.getTime(), restaurant.getAvailableTables(0, calendarDate.getTime()), choice -> {
                    switch (choice) {
                    case 1:
                        createReservation(calendarDate.getTime(), time);
                        break;
                    case 0:
                        break;
                    }
                });
            }
            else{
                createReservation(calendarDate.getTime(), time);
            }
        }else{
            view.displayResults("Oops, we're fully booked for this timing.");
        }
    }

    /* Making reservation */
    public void createReservation(Date date, Date time) {
        
        int reservationPax = view.getUserReservationPax();

        // ArrayList<Table> tables = restaurant.getTablesForPax(reservationPax);

        // check that there are enough tables
        ArrayList<Table> availableTables = restaurant.getAvailableTables(reservationPax, date);
        if (availableTables.size() == 0){
            view.displayResults("Oops, we dont have anymore tables for " + reservationPax + " people.");
            return;
        }

        // get more inputs from user
        String reservationName = view.getUserReservationName();
        String reservationContact = view.getUserReservationContact();

        view.displayResults("Reservation created!");
        // peg a table into reservation
        ReservationEntity reservation = new ReservationEntity(date, time, reservationPax, reservationName, reservationContact, availableTables.get(0).getNumber());

        existingReservations.add(reservation);
        restaurant.setReservations(existingReservations);
    }

    public void updateReservations() {
        Calendar cal = Calendar.getInstance();
        Date dateNow = cal.getTime();
        existingReservations.removeIf(reservation -> reservation.getDate().before(dateNow));
        restaurant.setReservations(existingReservations);
        return;
    }
}