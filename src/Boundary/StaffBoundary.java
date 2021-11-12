package Boundary;

import java.util.ArrayList;

import Entity.StaffEntity;
import Helpers.ChoiceObserver;

/**
 * 
 * Is the boundary that is involved with flows on the Sales Report screens.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class StaffBoundary extends Boundary{
    
    public StaffBoundary() {}

    /**
     * Get's the user's choice of which staff is using the system
     * @param staffList     is an ArrayList of StaffEntity representing all saved staff in the system
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getStaffChoice(ArrayList<StaffEntity> staffList, ChoiceObserver callback){
        
        String getStaffChoiceString = "Choose your staff\n";

        for (int i=0;i<staffList.size(); i++){
            getStaffChoiceString += String.valueOf(i+1) + ". " + staffList.get(i).getName() + " - " + staffList.get(i).getJobTitle() + "\n";
        }
        
        // Get the user's choice
        int numberOfChoices = staffList.size();
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, getStaffChoiceString);
    }

}
