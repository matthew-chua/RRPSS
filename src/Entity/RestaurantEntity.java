package Entity;

import Helpers.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Date;
import java.util.List;

/**
 * This is a single restaurant object
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class RestaurantEntity extends PersistenceManager {

    /**
     * Checks that only one instance of Restaurant is created
     */
    private static RestaurantEntity shared = null;

    /**
     * Number of tables available in the restaurant
     */
    static int NUMBER_OF_TABLES = 10;
    /**
     * ArrayList of all table available in the restaurant
     */
    private ArrayList<Table> tables;
    /**
     * ArrayList of all staff available in the restaurant
     */
    private ArrayList<StaffEntity> staff;
    /**
     * ArrayList of all existing orders in the restaurant
     */
    private ArrayList<OrderEntity> orders;
    /**
     * ArrayList of all existing reservations in the restaurant
     */
    private ArrayList<ReservationEntity> reservations;
    /**
     *  This restaurant's menu
     */
    private MenuEntity menu;
    /**
     * ArrayList of all invoices in the restaurant
     */
    private ArrayList<InvoiceEntity> invoices;
    /**
     * This restaurant's current active stadd
     */
    private StaffEntity currentStaff;


    /**
     * Creates a new restaurant by loading all existing saved data
     */
    public RestaurantEntity() {
        
        instantiateData();
        loadAllData();
    }

    /**
     * Constant strings
     */
    private static String reservationsFile = "Reservations.txt";
    private static String tablesFile = "Tables.txt";
    private static String staffFile = "Staff.txt";
    private static String ordersFile = "Orders.txt";
    private static String invoiceFile = "Invoice.txt";

    /**
     * Check if there are any existing instances of restaurant
     * @return either a new restaurant or the existing restaurant
     */
    public static RestaurantEntity getInstance() {
        if (shared == null) {
            shared = new RestaurantEntity();
        }
        return shared;
    }


    /**
     * Creates table capacity for new restaurant
     */
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
    }

    /**
     * clears current restaurant's data
     */
    private void instantiateData() {
        this.tables = new ArrayList<Table>();
        this.staff = new ArrayList<StaffEntity>();
        this.orders = new ArrayList<OrderEntity>();
        this.reservations = new ArrayList<ReservationEntity>();
        this.menu = new MenuEntity();
        this.invoices = new ArrayList<InvoiceEntity>();
    }

    /**
     * Create founders of restaurant
     */
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

    /**
     * Gets restaurant's current active staff
     * @return this restaurant's current active staff
     */
    public StaffEntity getCurrentStaff(){
        return this.currentStaff;
    }

    /**
     * prints the current existing reservation in record
     */
    public void printReservations() {
        this.reservations.forEach(item -> System.out.println(item.getName()));
    }

    /**
     * Gets the existing reservation in record
     * @return an ArrayList of existing reservation
     */
    public ArrayList<ReservationEntity> getReservations() {
        return reservations;
    }

    /**
     * Gets the tables available in the restaurant
     * @return an ArrayList of existing tables
     */
    public ArrayList<Table> getTables() {
        return tables;
    }

    /**
     * Gets ArrayList of tables available for current customer based on number of people
     * @param pax number of people of current customer
     * @return an ArrayList of tables large enough for current customer
     */
    public ArrayList<Table> getTablesForPax(int pax){
        Stream<Table> filteredTableStream = this.tables.stream().filter(table -> table.getCapacity() >= pax);
        List<Table> filteredTableList = filteredTableStream.collect(Collectors.toList());
        ArrayList<Table> filteredTables = new ArrayList<Table>(filteredTableList);
        return filteredTables;
    }

    /**
     * Gets ArrayList of tables available for current customer based on number of people and date
     * @param pax number of people of current customer
     * @param date date of reservation requested by current customer
     * @return an ArrayList of tables available based on number of people and date
     */
    public ArrayList<Table> getAvailableTables(int pax, Date date){
        Stream<Table> filteredTableStreamByPax = this.tables.stream().filter(table -> table.getCapacity() >= pax);

        Stream<ReservationEntity> filteredReservations = this.reservations.stream().filter(reservations -> {
            return reservations.isOccupiedDuring(date);
        });

        Stream<Integer> occupiedTableIds = filteredReservations.map(res -> {
            return res.getTable();
        });

        ArrayList<Integer> occupiedTableIdsList = new ArrayList<Integer>(occupiedTableIds.collect(Collectors.toList()));

        Stream<Table> availableTableStream = filteredTableStreamByPax.filter(table -> {
            return (!occupiedTableIdsList.contains(table.getNumber()));
        });
        
        List<Table> availableTableList = availableTableStream.collect(Collectors.toList());
        ArrayList<Table> availableTables = new ArrayList<Table>(availableTableList);
        return availableTables;
    }

    /**
     * Gets the ArrayList of orders
     * @return an ArrayList of orders
     */
    public ArrayList<OrderEntity> getOrders() {
        return orders;
    }

    /**
     * Gets the ArrayList of staff
     * @return an ArrayList of staff
     */
    public ArrayList<StaffEntity> getStaff() {
        return staff;
    }

    /**
     * Gets the ArrayList of invoices
     * @return an ArayList of invoices
     */
    public ArrayList<InvoiceEntity> getInvoices(){
        return invoices;
    }

    /**
     * Gets the current menu of the restaurant
     * @return this restaurant's menu
     */
    public MenuEntity getMenu() {
        return this.menu;
    }

    /**
     * Changes the ArrayList of reservations in the restaurant and save it to an external file
     * @param reservations new reservation ArrayList
     */
    public void setReservations(ArrayList<ReservationEntity> reservations) {
        this.reservations = reservations;
        saveData(reservationsFile, this.reservations);
    }

    /**
     * Changes the ArrayList of tables in the restaurant and save it to an external file
     * @param tables new table ArrayList
     */
    public void setTables(ArrayList<Table> tables){
        this.tables = tables;
        saveData(tablesFile, this.tables);
    }

    /**
     * Changes the ArrayList of invoice in the restaurant and save it to an external file
     * @param i new invoice ArrayList
     */
    public void addInvoice(InvoiceEntity i){
        this.invoices.add(i);
        saveData(invoiceFile, this.invoices);
    }

    /**
     * Based on type of data and data input, save the data to an external file
     * @param <T> generic type
     * @param type type of data to save
     * @param data data to be saved
     */
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
        case RESERVATION:
            reservations.add((ReservationEntity) data);
            saveData(reservationsFile, reservations);
            break;
        case TABLE:
            tables.add((Table) data);
            saveData(tablesFile, tables);
            break;
        case INVOICE:
            // haven't done up yet
            break;
        // return;
        case MENU:
            System.out.println("Error, cannot add to menu list. Add to Alacarte items instead");
            break;
        }
    }

    /**
     * Changes current staff active for restaurant
     * @param staff this restaurant's new staff
     */
    public void setCurrentStaff(StaffEntity staff){
        currentStaff = staff;
    }

    /**
     * Removes existing data based on type from ArrayList and external file
     * @param type type of data to be removed
     * @param index index of data in ArrayList to be removed
     */
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
        case RESERVATION:
            reservations.remove(index);
            saveData(reservationsFile, reservations);
            break;
        case TABLE:
            tables.remove(index);
            saveData(tablesFile, tables);
            break;
        case INVOICE:
            // haven't done up yet
            break;
        case MENU:
            System.out.println("Error, cannot add to menu list. Add to Alacarte items instead");
            break;
        }
    }

    /**
     * extract data of restaurant from external file
     */
    private void loadAllData() {
        loadData(reservationsFile, reservations);
        loadData(tablesFile, tables);
        loadData(ordersFile, orders);
        loadData(staffFile, staff);
        loadData(invoiceFile, invoices);
    }

    /**
     * saves data of restaurant to external file
     */
    private void saveAllData() {
        saveData(ordersFile, orders);
        saveData(tablesFile, tables);
        saveData(staffFile, staff);
        saveData(reservationsFile, reservations);
        saveData(invoiceFile, invoices);
    }

}
