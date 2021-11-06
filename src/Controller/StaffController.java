package Controller;
import Boundary.*;
import Controller.MenuController;
import Entity.RestaurantEntity;
import Entity.StaffEntity;
import java.util.ArrayList;

public class StaffController {

    // UI
    private StaffBoundary view;

    private RestaurantEntity res;

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

    // Load up controller
    private void start(){

        // pass in the anonymous callback function
        view.getStaffChoice(staffList, choice -> {
           res.setCurrentStaff(staffList.get(choice-1) );
           HomeController controller = new HomeController();
        });
    }


    
}