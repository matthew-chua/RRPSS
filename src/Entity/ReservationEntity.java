package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

/**
 * This is a single reservation object
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class ReservationEntity implements Serializable {
    /**
     * The date of this reservation
     */
    private Date date;
    /**
     * The time of this reservation
     */
    private Date time;
    /**
     * The number of people for this reservation
     */
    private int pax;
    /**
     * The name of the reserver for this reservation
     */
    private String name;
    /**
     * The contact number of the reserver for this reservation
     */
    private String contact;
    /**
     * The table number asssigned for this reservation
     */
    private int table;

    /**
     * Creates a new reservation
     * 
     * @param date    This reservation's date
     * @param time    This reservation's time
     * @param pax     This reservation's number of people
     * @param name    This reservation's reserver's name
     * @param contact This reservation's reserver's contact number
     * @param table   This reservation's table assigned
     */
    public ReservationEntity(Date date, Date time, int pax, String name, String contact, int table) {
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
        this.table = table;
    }

    /**
     * Gets the date of this reservation
     * 
     * @return this reservation's date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Changes the date of this reservation
     * 
     * @param date This reservation's new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the time of this reservation
     * 
     * @return this reservation's time
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * Changes the time of this reservation
     * 
     * @param time This reservation's new time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Gets the number of people for this reservation
     * 
     * @return this reservation's number of people
     */
    public int getPax() {
        return this.pax;
    }

    /**
     * Changes the number of people for this reservation
     * 
     * @param pax this reservations's new number of people
     */
    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * Gets the name of the reserver for this reservation
     * 
     * @return this reservation's reserver's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes the name of the reserver for this reservation
     * 
     * @param name this reservation's new reserver's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact number of the reserver for this reservation
     * 
     * @return this reservation's reserver contact number
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * Changes the contact number of the reserver for this reservation
     * 
     * @param contact this reservation's new reserver's contact number
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the table number assigned to this reservation
     * 
     * @return this reservation's table's number
     */
    public int getTable() {
        return this.table;
    }

    /**
     * Changes the table number assigned to this reservation
     * 
     * @param table this reservation's new table number
     */
    public void setTable(int table) {
        this.table = table;
    }

    /**
     * Prints out the details of this reservation
     */
    public void printReservation() {
        System.out.println("******* Reservation for " + name + " *******");
        System.out.println("Reservation Date: " + date);
        System.out.println("Reservation Time: " + time);
        // System.out.println("Table Number: " + table);
        System.out.println("Number of people: " + pax);
        System.out.println("Contact Number: " + contact);
    }

    /**
     * Check if the table is occupied within a specific time period
     * 
     * @param date the time period to check if a new reservation is made within 2
     *             hrs of an existing reservation
     * @return
     */
    public boolean isOccupiedDuring(Date date) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);

        calendarDate.set(Calendar.HOUR_OF_DAY, calendarDate.get(Calendar.HOUR_OF_DAY) - 2);
        Date twoHoursBefore = calendarDate.getTime();
        // check that there isnt a reservation two hours before
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarDate.get(Calendar.HOUR_OF_DAY) + 4);
        Date twoHoursAfter = calendarDate.getTime();

        boolean isOccupied2HoursBefore = (this.date.compareTo(twoHoursBefore) > 0
                && this.date.compareTo(calendarDate.getTime()) <= 0);
        boolean isOccupied2HoursAfter = (this.date.compareTo(calendarDate.getTime()) > 0
                && this.date.compareTo(twoHoursAfter) <= 0);
        return (isOccupied2HoursAfter || isOccupied2HoursBefore);
    }

}
