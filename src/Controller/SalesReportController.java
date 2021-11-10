package Controller;
import Boundary.*;
import Entity.*;

import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;

public class SalesReportController {
    
    // UI
    private SalesReportBoundary view;
    private RestaurantEntity res;
    private ArrayList<InvoiceEntity> invoiceList;

    private Map<AlaCarteEntity, Integer> menuItems;
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

    // setup menu
    private void setupMenuItems(){
        this.menuItems = new HashMap<AlaCarteEntity, Integer>();
        // res.getMenu().getAlaCarteItems().forEach(item -> menuItems.put(item, 0));
    }

    // setup package
    private void setupPackageItems(){
        this.packageItems = new HashMap<PackageEntity, Integer>();
        // res.getMenu().getPackages().forEach(item -> packageItems.put(item, 0));
    }

    // public ArrayList<InvoiceEntity> getReportbyDay(Date date) {
    //     // some function to get all invoices from selected date
    //     return this.invoiceList;
    // }

    // public ArrayList<InvoiceEntity> getReportbyMonth(Date month) {
    //     // some function to get all invoices from selected month
    //     return this.invoiceList;
    // }
    
    // String to print
    String title = Boundary.separators + " Print Sales Revenue Report by Period " + Boundary.separators + "\n";
    String printSalesString = title + "Showing sales for ";

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

    private void resetLists(){
        setupMenuItems();
        setupPackageItems();
    }

    private void currentListsToString(){
        this.menuItems.forEach((item, qty) -> {
            printSalesString += qty + " x " + item.getName() + ": $" + item.getPrice()*qty + "\n";
        });

        this.packageItems.forEach((item, qty) -> {
            printSalesString += qty + " x " + item.getName() + "\n";
        });
    }

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

    public int sumOfRevenue(InvoiceEntity[] invoice) {
        return 1;
    }

    // Load up controller
    private void start(){

        // pass in the anonymous callback function
        view.getUserManagerChoice(choice -> {
            switch (choice){
                case 0:
                System.out.println("quitted");
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