package Boundary;
import java.util.*;

public class Boundary {
    Scanner sc = new Scanner(System.in);

    // Resets the console
    public void resetUI(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Gets a string input from user
    public String getStringInput(){
        // catch error later
        return sc.next();
    }

    /** Gets a choice input from user in the form of an int **/
    
    public int getUserChoices(int numberOfChoices){
        int choice;
        choice = sc.nextInt();
        while (choice > numberOfChoices || choice < 0){
            System.out.println("Oops, please enter a valid number");
            choice = sc.nextInt();
        }
        return choice;
    }




}
