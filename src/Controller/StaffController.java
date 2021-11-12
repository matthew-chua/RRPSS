package Controller;
import Boundary.*;
import Controller.MenuController;
import Entity.RestaurantEntity;
import Entity.StaffEntity;
import java.util.ArrayList;


/**
 * 
 * Is the controller that updates the boundary and entities for all Staff related scenes.
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
public class StaffController {

    /** The Boundary responsible for displaying the Staff UI */
    private StaffBoundary view;
    /** A reference to the shared instance of restaurant entity */
    private RestaurantEntity res;
    /** Reference to the staff available */
    private ArrayList<StaffEntity> staffList;

    // Constructor
    public StaffController() {
        // Instantiate view
        this.view = new StaffBoundary();
        res = RestaurantEntity.getInstance();
        staffList = res.getStaff();

        // Run start function
        this.start();
    }

    /** Runs the sequenece of events for selecting Staff */
    private void start(){

        // pass in the anonymous callback function
        view.getStaffChoice(staffList, choice -> {
           res.setCurrentStaff(staffList.get(choice-1) );
           HomeController controller = new HomeController();
        });
    }   
}