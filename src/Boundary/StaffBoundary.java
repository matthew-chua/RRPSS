package Boundary;

import java.util.ArrayList;

import Entity.StaffEntity;
import Helpers.ChoiceObserver;

public class StaffBoundary extends Boundary{
 
    public StaffBoundary() {}

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
