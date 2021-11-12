package Controller;
import Boundary.*;
import Entity.*;

import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * 
 * Is the controller that updates the boundary and entities for all Sales related scenes.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 * 
 */
public class SalesReportController {
    
    /** The Boundary responsible for displaying the Sales Report UI */
    private SalesReportBoundary view;
    /** A reference to the shared instance of restaurant entity */
    private RestaurantEntity res;
    /** Reference to the existing invoices */
    private ArrayList<InvoiceEntity> invoiceList;

    /** Reference to the alacarte menu items */
    private Map<AlaCarteEntity, Integer> menuItems;
    /** Reference to the package menu items */
    private Map<PackageEntity, Integer> packageItems;

    // Constructor
    public SalesReportController(){
        // Instantiate view
        this.view = new SalesReportBoundary();
        this.res = RestaurantEntity.getInstance();

        this.invoiceList = res.getInvoices();
        
        setupMenuItems();
        setupPackageItems();

        // Run start function
        this.start();
    }

    /** Instantiate menu items */
    private void setupMenuItems(){
        this.menuItems = new HashMap<AlaCarteEntity, Integer>();
    }

    /** Instantiate package items */
    private void setupPackageItems(){
        this.packageItems = new HashMap<PackageEntity, Integer>();
    }
    
    // String to print
    String title = Boundary.separators + " Print Sales Revenue Report by Period " + Boundary.separators + "\n";
    String printSalesString = title + "Showing sales for ";

    /** Prints the sales report by day */
    public void printByDay(){
        resetLists();
        Date userDate = view.getUserDate();
        if (userDate == null) return;
    
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        printSalesString += fmt.format(userDate) + "\n\n";

        List<InvoiceEntity> filteredList = this.invoiceList.stream().filter(invoice -> {
            // Compare dates without time
            return fmt.format(userDate).equals(fmt.format(invoice.getTimeStamp()));
        }).toList();
        if (filteredList.size() == 0){
            view.displayResults(printSalesString + "Oops, nothing found");
            printSalesString = title + "Showing sales for ";
            return;
        }


        // filter
        filteredList.forEach(invoice -> {
            invoice.getAlaCarteItems().forEach((item, qty) -> {
                int currentQty = this.menuItems.getOrDefault(item, 0);
                this.menuItems.put(item, currentQty+qty);
            });
            invoice.getPackageItems().forEach((item, qty) -> {
                int currentQty = this.packageItems.getOrDefault(item, 0);
                this.packageItems.put(item, currentQty+qty);
            });
        });

        // add all items for the period into string
        this.menuItems.forEach((item, qty) -> {
            printSalesString += qty + " x " + item.getName() + ": $" + item.getPrice()*qty + "\n";
        });

        this.packageItems.forEach((item, qty) -> {
            printSalesString += qty + " x " + item.getName() + ": $" + item.getPrice()*qty + "\n";
        });

        double sum = filteredList.stream().mapToDouble(invoice -> invoice.calculateTotal()).sum();


        view.resetUI();
        view.displayResults(printSalesString + "\nTotal Revenue: $" + String.format("%.2f", sum));
        printSalesString = title + "Showing sales for ";
    }

    /** prints the sales report by month */
    public void printByMonth() {
        resetLists();
        Date userDate = view.getUserMonth();

        if (userDate == null) return;
        
        SimpleDateFormat fmt = new SimpleDateFormat("MM/yyyy");

        printSalesString += fmt.format(userDate) + "\n\n";

        List<InvoiceEntity> filteredList = this.invoiceList.stream().filter(invoice -> {
            // Compare dates without time
            return fmt.format(userDate).equals(fmt.format(invoice.getTimeStamp()));
        }).toList();
        if (filteredList.size() == 0){
            view.displayResults(printSalesString + "Oops, nothing found");
            printSalesString = title + "Showing sales for ";
            return;
        }
        
        updateCurrentLists(filteredList);

        // add all items for the period into string
        currentListsToString();

        double sum = filteredList.stream().mapToDouble(invoice -> invoice.calculateTotal()).sum();
        
        view.resetUI();
        view.displayResults(printSalesString + "\nTotal Revenue: $" + String.valueOf(sum));
        printSalesString = title + "Showing sales for ";
    }

    /** Reset lists to empty state */
    private void resetLists(){
        setupMenuItems();
        setupPackageItems();
    }

    /** Transforms list of packages and menu items into a formatted string in printSalesString */
    private void currentListsToString(){
        this.menuItems.forEach((item, qty) -> {
            printSalesString += qty + " x " + item.getName() + ": $" + item.getPrice()*qty + "\n";
        });

        this.packageItems.forEach((item, qty) -> {
            printSalesString += qty + " x " + item.getName() + "\n";
        });
    }


    /**
     * Updates the current list of invoice entities
     * @param filteredList  a List of InvoiceEntity that is filtered by date
     */
    private void updateCurrentLists(List<InvoiceEntity> filteredList){
        filteredList.forEach(invoice -> {
            invoice.getAlaCarteItems().forEach((item, qty) -> {
                int currentQty = this.menuItems.getOrDefault(item, 0);
                this.menuItems.put(item, currentQty+qty);
            });
            invoice.getPackageItems().forEach((item, qty) -> {
                int currentQty = this.packageItems.getOrDefault(item, 0);
                this.packageItems.put(item, currentQty+qty);
            });
        });
    }

    /** Runs the sequenece of events for Sales Report flows */
    private void start(){

        // pass in the anonymous callback function
        view.getUserManagerChoice(choice -> {
            switch (choice){
                case 0:
                // System.out.println("quitted");
                return;

                case 1:
                System.out.println("Print sale revenue by day");
                // some function
                printByDay();
                break;

                case 2:
                System.out.println("Print sale revenue by month");
                // some function
                printByMonth();
                break;

                default:
                break;
            }
        });
    }
}