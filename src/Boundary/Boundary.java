package Boundary;
import java.util.*;

import Helpers.Callback;

public class Boundary {
    Scanner sc = new Scanner(System.in);
    public static String separators = "============";


    /**  Resets the console **/
    public void resetUI(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /** Gets a string input from user **/
    public void getStringInput(Callback callback, boolean isRecurring, String stringToPrint){
        // catch error later
        String userInput = "";
        do{

            resetUI();
            System.out.println("ui should be reset");
            System.out.println(stringToPrint);
            userInput = sc.next();
            callback.userDidEnterString(userInput);
        }while (userInput != "0" && isRecurring);

    }

    /** 
     * Gets a choice input from user in the form of an int 
     * @param numberOfChoices is the maximum value of the int to look for
     * @param callback is a function that takes in an int which corresponds to the user's choice
     * @param isRecurring is a bool that toggles whether to end the loop on first successful retrieval of user's choice
    **/
    public void getUserChoices(int numberOfChoices, Callback callback, boolean isRecurring, String stringToPrint){
        int choice = -1;
        boolean invalidChoice = false;
        boolean nonNumber = false;
        while(choice != 0)
        {
            try {
                resetUI();
                System.out.println(stringToPrint);
                if (invalidChoice){
                    System.out.println("Hey, that's not a valid choice... Please try again!");
                    invalidChoice = false;
                }
                if (nonNumber){
                    System.out.println("Hey, that's not a number... Please try again!");
                    nonNumber = false;
                }

                choice = Integer.parseInt(sc.next());
                if (choice == 0) break;
                if (choice > 0 && choice <= numberOfChoices){ 
                    callback.userDidSelectChoice(choice);
                    if (!isRecurring){ break; }
                }else{
                    // System.out.println("Hey, that's not a valid choice... Please try again!");
                    invalidChoice = true;
                }
            }
            catch(NumberFormatException e) {
                // System.out.println("Hey, that's not a number... Please try again!");
                nonNumber = true;
            }
        }
    }



}
