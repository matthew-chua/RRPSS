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

    // Constructor
    public SalesReportController(){
        // Instantiate view
        this.view = new SalesReportBoundary();
        this.res = RestaurantEntity.getInstance();

        this.invoiceList = res.getInvoices();
        // Run start function
        this.start();
    }

    // public ArrayList<InvoiceEntity> getReportbyDay(Date date) {
    //     // some function to get all invoices from selected date
    //     return this.invoiceList;
    // }

    // public ArrayList<InvoiceEntity> getReportbyMonth(Date month) {
    //     // some function to get all invoices from selected month
    //     return this.invoiceList;
    // }
    
    public void printByDay(){
        Date userDate = view.getUserDate();
        
        SimpleDateFormat fmt = new SimpleDateFormat("ddMMyyyy");

        List<InvoiceEntity> filteredList = this.invoiceList.stream().filter(invoice -> {
            // Compare dates without time
            return fmt.format(userDate).equals(fmt.format(invoice.getTimeStamp()));
        }).toList();
        if (filteredList.size() == 0){
            view.displayResults("Oops, nothing found");
            return;
        }

        // float sum = filteredList.stream().filter(predicate)

        view.resetUI();
        // view.displayResults("Showing sales for" + fmt.format(userDate) + "\n" + String.valueOf(sum));
    }

    public void printByMonth() {

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