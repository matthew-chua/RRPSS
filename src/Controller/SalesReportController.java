package Controller;
import Boundary.*;
import Entity.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class SalesReportController {
    
    // UI
    private SalesReportBoundary view;

    // Constructor
    public SalesReportController(){
        // Instantiate view
        this.view = new SalesReportBoundary();
        
        // Run start function
        this.start();
    }

    public ArrayList<InvoiceEntity> getReportbyDay(LocalDateTime date) {
        // some function to get all invoices from selected date
        
    }

    public ArrayList<InvoiceEntity> getReportbyMonth(LocalDateTime month) {
        // some function to get all invoices from selected month
    }
    
    public void printByDay() {

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