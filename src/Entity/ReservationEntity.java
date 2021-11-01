package Entity;

import java.io.Serializable;
import java.util.Date;

public class ReservationEntity implements Serializable {
    private Date date;
    private Date time;
    private int pax;
    private String name;
    private String contact;
    // private int table;

    public ReservationEntity(Date date, Date time, int pax, String name, String contact/* , int table */) {
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
        // this.table = table;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getPax() {
        return this.pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // public int getTable() {
    // return this.table;
    // }

    // public void setTable(int table) {
    // this.table = table;
    // }

    public void printReservation() {
        System.out.println("******* Reservation for " + name + " *******");
        System.out.println("Reservation Date: " + date);
        System.out.println("Reservation Time: " + time);
        // System.out.println("Table Number: " + table);
        System.out.println("Number of people: " + pax);
        System.out.println("Contact Number: " + contact);
    }
}
