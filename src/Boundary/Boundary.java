package Boundary;
import java.util.*;

import Helpers.Callback;

public class Boundary {
    Scanner sc = new Scanner(System.in);

    /**  Resets the console **/
    public void resetUI(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /** Gets a string input from user **/
    public String getStringInput(){
        // catch error later
        return sc.next();
    }

    /** 
     * Gets a choice input from user in the form of an int 
     * @param numberOfChoices is the maximum value of the int to look for
     * @param callback is a function that takes in an int which corresponds to the user's choice
     * @param isRecurring is a bool that toggles whether to end the loop on first successful retrieval of user's choice
    **/
    public void getUserChoices(int numberOfChoices, Callback callback, boolean isRecurring){
        int choice = -1;
        while(choice != 0)
        {
            try {
                System.out.print("Enter the number of student: ");
                choice = Integer.parseInt(sc.next());
                if (choice > 0 && choice <= numberOfChoices){ 
                    callback.userDidSelectChoice(choice);
                    if (!isRecurring){ break; }
                }else{
                    System.out.println("Hey, that's not a valid choice... Please try again!");
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Hey, that's not a number... Please try again!");
            }
        }
    }



}
