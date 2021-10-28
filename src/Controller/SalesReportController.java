package Controller;
import Boundary.*;

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

    public void printByDay() {

    }

    public void printByMonth() {
        
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