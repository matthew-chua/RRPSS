package Entity;

import Helpers.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// public enum RestaurantDataType{
//     TABLE, STAFF, ORDER, INVOICE, RESERVATION
// }

public class RestaurantEntity extends PersistenceManager {

    // Attributes
    private static RestaurantEntity shared = null;

    private ArrayList<Table> tables;
    private ArrayList<StaffEntity> staff;
    private ArrayList<OrderEntity> orders;
    private ArrayList<ReservationEntity> reservations;
    private MenuEntity menu;
    // private InvoiceEntity[] invoices;

    // Constructor
    public RestaurantEntity() {

        // init data
        instantiateData();

        // this.reservations.add(newRes);
        // this.reservations.add(newRes2);
        // saveReservationData();
    }

    // Constants
    private static String reservationsFile = "Reservations.txt";
    private static String tablesFile = "Tables.txt";
    private static String staffFile = "Staff.txt";
    private static String ordersFile = "Orders.txt";

    public static RestaurantEntity getInstance() {
        if (shared == null) {
            shared = new RestaurantEntity();
        }
        return shared;
    }

    private void instantiateData() {
        this.tables = new ArrayList<Table>();
        this.staff = new ArrayList<StaffEntity>();
        this.orders = new ArrayList<OrderEntity>();
        this.reservations = new ArrayList<ReservationEntity>();
        this.menu = new MenuEntity();
        // this.invoices = new ArrayList<InvoiceEntity>();
    }

    public void printReservations() {
        this.reservations.forEach(item -> System.out.println(item.getName()));
    }

    // public <T> ArrayList<T> getList(RestaurantDataType type) {
    // switch (type) {
    // case ORDER:
    // return orders;
    // case STAFF:
    // return staff;
    // case RESERVATION:
    // return reservations;
    // case TABLE:
    // return tables;
    // case INVOICE:
    // // haven't done up yet
    // // return;
    // case MENU:
    // // break;
    // // return;
    // // return menu;
    // default:
    // return new ArrayList<>();
    // }
    // }

    public ArrayList<ReservationEntity> getReservations() {
        return reservations;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<OrderEntity> getOrders() {
        return orders;
    }

    public ArrayList<StaffEntity> getStaff() {
        return staff;
    }

    public MenuEntity getMenu() {
        return this.menu;
    }

    public void setReservations(ArrayList<ReservationEntity> reservations) {
        this.reservations = reservations;
        saveData(reservationsFile, this.reservations);
    }

    public <T> void addDataToList(RestaurantDataType type, T data) {
        switch (type) {
        case ORDER:
            orders.add((OrderEntity) data);
            saveData(ordersFile, orders);
            break;
        case STAFF:
            staff.add((StaffEntity) data);
            saveData(staffFile, staff);
            break;
        // return staff;
        case RESERVATION:
            reservations.add((ReservationEntity) data);
            saveData(reservationsFile, reservations);
            // return reservations;
            break;
        case TABLE:
            tables.add((Table) data);
            saveData(tablesFile, tables);
            break;
        // return tables;
        case INVOICE:
            // haven't done up yet
            break;
        // return;
        case MENU:
            // can't add
            System.out.println("Error, cannot add to menu list. Add to Alacarte items instead");
            break;
        }
    }

    public void removeDataFromList(RestaurantDataType type, int index) {
        switch (type) {
        case ORDER:
            orders.remove(index);
            saveData(ordersFile, orders);
            break;
        case STAFF:
            staff.remove(index);
            saveData(staffFile, staff);
            break;
        // return staff;
        case RESERVATION:
            reservations.remove(index);
            saveData(reservationsFile, reservations);
            // return reservations;
            break;
        case TABLE:
            tables.remove(index);
            saveData(tablesFile, tables);
            break;
        // return tables;
        case INVOICE:
            // haven't done up yet
            break;
        // return;
        case MENU:
            // can't add
            System.out.println("Error, cannot add to menu list. Add to Alacarte items instead");
            break;
        }
    }

    private void loadAllData() {
        loadData(reservationsFile, reservations);
        loadData(tablesFile, tables);
        loadData(ordersFile, orders);
        loadData(staffFile, staff);
    }

    private void saveAllData() {
        saveData(ordersFile, orders);
        saveData(tablesFile, tables);
        saveData(staffFile, staff);
        saveData(reservationsFile, reservations);
    }

}
