package Boundary;

import java.util.*;
import Helpers.ChoiceObserver;
import Helpers.StringObserver;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * Is the parent boundary class that has predefined methods to get inputs from the user.
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class Boundary {
    Scanner sc = new Scanner(System.in);
    
    /** Static constant used to print titles and dividers **/
    public static String separators = "============";

    /** Resets the console **/
    public void resetUI() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Gets a string input from the user
     * @param callback        is a function that takes in an int which corresponds
     *                        to the user's choice
     * @param isRecurring     is a bool that toggles whether to end the loop on
     *                        first successful retrieval of user's choice
     * 
     * @param stringToPrint   is a String that is being printed before user inputs
     *                        their choice
     * 
     */
    public void getStringInput(StringObserver callback, boolean isRecurring, String stringToPrint) {
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
                // do nothing
            } catch(NullPointerException e) {
                // do nothing
            }

            callback.userDidEnterString(userInput);
        } while (isRecurring);
    }

    /** Gets a string input from user.
     * 
     * @return string input from user
     */
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
     * 
     * @param stringToPrint   is a String that is being printed before user inputs
     *                        their choice
     * 
     */

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

    /**
     * Gets a positive double from user
     * @return positive double from user if success. Otherwise, returns 0.0
     */
    public double getPositiveDouble() {
        // check for error
        double positiveDouble = 0.0;
        sc.nextLine();
        while (!sc.hasNextDouble()){
            System.out.println("Oops, that's not a valid input. Please try again!");
            sc.nextLine();
        };
        positiveDouble = sc.nextDouble();
        return positiveDouble;
    }

    /**
     * Gets a positive integer
     * @return positive integer
     */
    public int getPositiveInt() {
        // ?? check for error
        int choice = 0;
        try{
            choice = Integer.parseInt(sc.next());
        }
        catch (NumberFormatException e) {
            System.out.println("Oops. That's not a valid number");
        }
        return choice;
    }

    /**
     * Gets date from user in dd/MM/yyyy format
     * @return Date object
     */
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

    /**
     * Gets month from user in MM/yyyy format
     * @return Date object
     */
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

    /**
     * Method that prints results and forces the user to press enter to move on.
     * 
     * @param stringToPrint is the string that is printed.
     */
    public void displayResults(String stringToPrint){
        System.out.println(stringToPrint);
        System.out.println("Press enter to continue");
        sc.nextLine();
        sc.nextLine();
    }

}
