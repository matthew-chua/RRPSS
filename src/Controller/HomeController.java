package Controller;
import Boundary.*;

public class HomeController {
    
    // UI
    private HomeBoundary view;

    // Constructor
    public HomeController(){
        // Instantiate view
        this.view = new HomeBoundary();
        
        // Run start function
        this.start();
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
                System.out.println("Hello");
                break;

                case 2:
                System.out.println("Manage Orders");
                OrderController orderController = new OrderController();
                break;

                case 3: 
                System.out.println("Manage Reservation");
                ReservationController reservationController = new ReservationController();
                break;

                case 4:
                System.out.println("Manage Sales");
                // SalesController sale
                break;

                case 5:
                System.out.println("Manage Staff");
                break;

                default:
                break;
                }
            }
        );

    }
}