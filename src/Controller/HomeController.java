package Controller;
import Boundary.*;
import Controller.MenuController;
import Entity.RestaurantEntity;

/**
 * 
 * Is the controller that updates the boundary and entities for all Home related scenes.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class HomeController {

    // UI
    /** The HomeBoundary that updates the UI for the user */
    private HomeBoundary view;

    /** Constructor */
    public HomeController() {
        // Instantiate view
        this.view = new HomeBoundary();

        // Run start function
        this.start();
    }

    /** Runs the sequenece of events on initialisation */
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