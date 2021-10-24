package Boundary;
import Helpers.*;

public class OrderBoundary extends Boundary{
    
    //constructor
    public OrderBoundary(){}

    // Strings
    private String orderManagerTitle = separators + " Manage Orders " + separators + "\n";
    private String tableToServeString = "Please enter a table to serve:\n" + "Otherwise, enter 0 to return back to the main menu";

    public void getManagerChoice(Callback callback){
        resetUI();
        
        int numberOfChoices = 5;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, callback, isRecurring, orderManagerTitle + tableToServeString);
        //need to print all the tables here

        //scan for input

    }



    
}
