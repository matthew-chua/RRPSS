package Boundary;
import Helpers.*;

public class OrderBoundary extends Boundary{
    
    //constructor
    public OrderBoundary(){

    }


    public void getManagerChoice(Callback callback){
        resetUI();
        System.out.println("==============================");
        System.out.println("Manage Orders");
        System.out.println("==============================");
        
        System.out.println("Please enter a table to serve:");
        System.out.println("otherwise, enter 0 to return back to the main menu");
        
        int numberOfChoices = 5;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring);
        //need to print all the tables here

        //scan for input

    }



    
}
