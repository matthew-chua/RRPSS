package Controller;
import Boundary.*;
import Controller.MenuController;
import Entity.RestaurantEntity;

public class HomeController {

    // UI
    private HomeBoundary view;

    // Constructor
    public HomeController() {
        // Instantiate view
        this.view = new HomeBoundary();

        // Run start function
        this.start();
    }

    // Load up controller
    private void start(){

        // RestaurantEntity res = RestaurantEntity.shared.tables;

        // pass in the anonymous callback function
        view.getUserManagerChoice(RestaurantEntity.getInstance().getCurrentStaff().getName(), choice -> {
            switch (choice){
                case 0:
                System.out.println("quitted");
                return;

                case 1:
                System.out.println("Hello");
                MenuController menuController = new MenuController();
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
                SalesReportController salesReportController = new SalesReportController();
                break;

                // case 5:
                // System.out.println("Manage StaffEntity");
                // break;

                default:
                break;
                }
            }
        );

    }
}