package Boundary;

import java.util.*;
import Helpers.ChoiceObserver;
import Helpers.StringObserver;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Boundary {
    Scanner sc = new Scanner(System.in);
    
    public static String separators = "============";

    /** Resets the console **/
    public void resetUI() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /** Gets a string input from user **/
    public void getStringInput(StringObserver callback, boolean isRecurring, String stringToPrint) {
        // catch error later
        String userInput = "";
        do {

            resetUI();
            System.out.println(stringToPrint);
            System.out.println("Press 0 to go back\n");

            userInput = sc.next();

            try{
                int input = Integer.parseInt(userInput);
                if (input == 0) return;
            }catch(NumberFormatException e) { 
                 
            } catch(NullPointerException e) {

            }

            // if ( userInput == "0" )
            //     return;

            callback.userDidEnterString(userInput);
        } while (isRecurring);
    }

    public String getStringInput() {
        sc.useDelimiter("\n");
        return sc.next();
    }

    /**
     * Gets a choice input from user in the form of an int
     * 
     * @param numberOfChoices is the maximum value of the int to look for ... don't
     *                        include 0
     * @param callback        is a function that takes in an int which corresponds
     *                        to the user's choice
     * @param isRecurring     is a bool that toggles whether to end the loop on
     *                        first successful retrieval of user's choice
     **/

    public void getUserChoices(int numberOfChoices, ChoiceObserver callback, boolean isRecurring,
            String stringToPrint) {
        int choice = -1;
        boolean invalidChoice = false;
        boolean nonNumber = false;
        while (choice != 0) {
            try {
                resetUI();
                System.out.println(stringToPrint);
                if (invalidChoice) {
                    System.out.println("Hey, that's not a valid choice... Please try again!");
                    invalidChoice = false;
                }
                if (nonNumber) {
                    System.out.println("Hey, that's not a number... Please try again!");
                    nonNumber = false;
                }

                System.out.println("Press 0 to go back\n");

                choice = Integer.parseInt(sc.next());
                if (choice == 0)
                    break;
                if (choice > 0 && choice <= numberOfChoices) {
                    callback.userDidSelectChoice(choice);
                    if (!isRecurring) {
                        break;
                    }
                } else {
                    invalidChoice = true;
                }
            } catch (NumberFormatException e) {
                nonNumber = true;
            }
        }

    }

    public double getPositiveDouble() {

        // check for error
        return sc.nextDouble();
    }

    public int getPositiveInt() {
        // ?? check for error
        return sc.nextInt();
    }

    public Date getUserDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter date (DD/MM/YYYY): \n");
        System.out.println("Press 0 to go back");
        while(true){
            String input = getStringInput();
            
            try{
                int inputInt = Integer.parseInt(input);
                if (inputInt == 0) return null;
            }catch(NumberFormatException e) { 
                 
            } catch(NullPointerException e) {

            }

            try{
                Date inputDate = dateFormat.parse(input);
                return inputDate;
            }
            catch(ParseException e){
                System.out.println("Oops, that's not a valid date. Please Try again!");
                continue;
            }
        }
    }

    public Date getUserMonth(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        System.out.println("Enter month and year (MM/YYYY):\n");

        System.out.println("Press 0 to go back");
        while(true){
            String input = getStringInput();
            try{
                int inputInt = Integer.parseInt(input);
                if (inputInt == 0) return null;
            }catch(NumberFormatException e) { 
                 
            } catch(NullPointerException e) {

            }

            try{
                Date inputDate = dateFormat.parse(input);
                return inputDate;
            }
            catch(ParseException e){
                System.out.println("Oops, that's not a valid input. Please try again!");
                continue;
            }
        }
    }



    public void displayResults(String stringToPrint){
        System.out.println(stringToPrint);
        System.out.println("Press enter to continue");
        sc.nextLine();
        sc.nextLine();
    }

}
