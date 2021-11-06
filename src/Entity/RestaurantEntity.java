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

    static int NUMBER_OF_TABLES = 10;
    private ArrayList<Table> tables;
    private ArrayList<StaffEntity> staff;
    private ArrayList<OrderEntity> orders;
    private ArrayList<ReservationEntity> reservations;
    private MenuEntity menu;
    private ArrayList<InvoiceEntity> invoices;
    private StaffEntity currentStaff;


    // Constructor
    public RestaurantEntity() {

        // init data
        instantiateData();

        // this.reservations.add(newRes);
        // this.reservations.add(newRes2);
        // saveReservationData();
        
        // saveAllData();

        // setupStaff();
        // this.orders = new ArrayList<OrderEntity>();
        // resetTables();
        // saveAllData();

        // saveData(ordersFile, orders);
        loadAllData();
    }

    // Constants
    private static String reservationsFile = "Reservations.txt";
    private static String tablesFile = "Tables.txt";
    private static String staffFile = "Staff.txt";
    private static String ordersFile = "Orders.txt";
    private static String invoiceFile = "Invoice.txt";

    public static RestaurantEntity getInstance() {
        if (shared == null) {
            shared = new RestaurantEntity();
        }
        return shared;
    }


    private void resetTables(){
        for (int i=1; i<=NUMBER_OF_TABLES; i++){
            int cap=0;
            if (i<3){
                cap=2;
            }else if(i<5){
                cap=4;
            }else if(i<7){
                cap=6;
            }else if (i<9){
                cap=8;
            }
            else{
                cap=10;
            }
            tables.add(new Table(i, cap, true));
        }
        saveAllData();
    }

    private void instantiateData() {
        this.tables = new ArrayList<Table>();
        this.staff = new ArrayList<StaffEntity>();
        this.orders = new ArrayList<OrderEntity>();
        this.reservations = new ArrayList<ReservationEntity>();
        this.menu = new MenuEntity();
        this.invoices = new ArrayList<InvoiceEntity>();
    }

    private void setupStaff(){

        StaffEntity s1 = new StaffEntity("Xue Zhe", "Male" , 1, "Head Chef");
        this.staff.add(s1);

        StaffEntity s2 = new StaffEntity("Wei Bin", "Male" , 2, "Waiter");
        this.staff.add(s2);

        StaffEntity s3 = new StaffEntity("Grace", "Female" , 3, "Cashier");
        this.staff.add(s3);

        StaffEntity s4 = new StaffEntity("Matthew", "Male" , 4, "Manager");
        this.staff.add(s4);

        StaffEntity s5 = new StaffEntity("Ivan", "Male" , 5, "Temperature Taker");
        this.staff.add(s5);

    }

    public StaffEntity getCurrentStaff(){
        return this.currentStaff;
    }

    public void printReservations() {
        this.reservations.forEach(item -> System.out.println(item.getName()));
    }

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

    public ArrayList<InvoiceEntity> getInvoices(){
        return invoices;
    }

    public MenuEntity getMenu() {
        return this.menu;
    }

    public void setReservations(ArrayList<ReservationEntity> reservations) {
        this.reservations = reservations;
        saveData(reservationsFile, this.reservations);
    }

    public void setTables(ArrayList<Table> tables){
        this.tables = tables;
        saveData(tablesFile, this.tables);
    }

    public void addInvoice(InvoiceEntity i){
        this.invoices.add(i);
        saveData(invoiceFile, this.invoices);
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

    public void setCurrentStaff(StaffEntity staff){
        currentStaff = staff;
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
        loadData(invoiceFile, invoices);
    }

    private void saveAllData() {
        saveData(ordersFile, orders);
        saveData(tablesFile, tables);
        saveData(staffFile, staff);
        saveData(reservationsFile, reservations);
        saveData(invoiceFile, invoices);
    }

}
