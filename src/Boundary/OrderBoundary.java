package Boundary;
import java.util.ArrayList;

import javax.swing.MenuElement;

import Controller.OrderController;
import Entity.AlaCarteEntity;
import Entity.MenuEntity;
import Entity.OrderEntity;
import Entity.PackageEntity;
import Helpers.*;

public class OrderBoundary extends Boundary{
    
    //constructor
    public OrderBoundary(){}

    // Strings
    private String orderManagerTitle = separators + " Manage Orders " + separators + "\n";

    // Serving Tables
    private String tableToServeString = "Please enter a table to serve: (1 - 10)\n" + "Otherwise, enter 0 to return back to the main menu";

    private float total = 0;


    public void createOrderForTable(int tableNumber, ChoiceObserver callback){
        // resetUI();
        
        int numberOfChoices = 1;
        boolean isRecurring = false;

        String createOrderString = "Table number " + String.valueOf(tableNumber) + " does not have an existing order.\nPress 1 to create order";

        getUserChoices(numberOfChoices, callback, isRecurring, orderManagerTitle + createOrderString);
    }

    public void showTableCreated(){
        displayResults("Table Created!");
    }

    public void updateOrderForTable(int tableNumber, OrderEntity order, ChoiceObserver callback){
        int numberOfChoices = 3;
        boolean isRecurring = false;

        // print existing 
        String createOrderString = "Order for table number " + String.valueOf(tableNumber) + ":\n\n";
        // createOrderString += 
        createOrderString += getOrderString(order);

        createOrderString += "1. Add to Order\n";
        createOrderString += "2. Remove from Order\n";
        createOrderString += "3. Print Invoice\n";

        getUserChoices(numberOfChoices, callback, isRecurring, orderManagerTitle + createOrderString);
    }

    public void getTableChoice(ChoiceObserver callback){
        String stringToPrint = orderManagerTitle + tableToServeString;
        getUserChoices(10, callback, false, stringToPrint);
    }

    private String getOrderString(OrderEntity order){
        String orderString = "ALCARTE ITEMS:\n";
        for (int i=0; i<order.getMenuItems().size(); i++){
            orderString += order.getMenuItems().get(i).getName()+ "\t\t\t" + String.valueOf(order.getMenuItems().get(i).getPrice()) + "\n";
        }

        orderString += "\nPACKAGE ITEMS:\n";
        for (int i=0; i<order.getSpecials().size(); i++){
            orderString += order.getSpecials().get(i).getName()+ "\t\t\t" + String.valueOf(order.getSpecials().get(i).getPrice()) + "\n";
        }

        order.getSpecials().forEach(o -> total += o.getPrice());
        order.getMenuItems().forEach(o -> total += o.getPrice());

        
        orderString += "\nTOTAL W/O TAX: " + String.valueOf(total) + "\n\n";

        total = 0;

        return orderString;
    }

    public void getMenuItemType(ChoiceObserver callback){

        String stringToPrint = orderManagerTitle;
        String option1 = "1. Add / Remove Ala Carte Item\n";
        String option2 = "2. Add / Remove Package Item";
        getUserChoices(2, callback, true, stringToPrint + option1 + option2);
    }

    public void getAlaCarteItemIndex(ArrayList<AlaCarteEntity> items, ChoiceObserver callback){
        
        // String menuString = menu.toString();
        // String 
        String stringToPrint = orderManagerTitle;
        for (int i=0; i<items.size(); i++){
            stringToPrint += String.valueOf(i+1) + ". " + items.get(i).getName() + "\n";
        }

        getUserChoices(items.size(), callback, false, stringToPrint);

    }

    public void getPackageIndex(ArrayList<PackageEntity> items, ChoiceObserver callback){
        
        // String menuString = menu.toString();
        // String 
        String stringToPrint = orderManagerTitle;
        for (int i=0; i<items.size(); i++){
            stringToPrint += String.valueOf(i+1) + " " + items.get(i).getName();
        }
        getUserChoices(items.size(), callback, false, stringToPrint);

    }
    
    public void getUserMembership(ChoiceObserver callback){
        String stringToPrint = orderManagerTitle;
        stringToPrint += "Is there a valid membership?\n";
        stringToPrint += "1. Yes\n";
        stringToPrint += "2. No\n";
        getUserChoices(2, callback, false, stringToPrint);
    }

    public void printInvoiceFormat(){
        System.out.println(separators + separators + separators);
        System.out.println("Kiki's Italian Cocktail Bar");
        System.out.println(separators + separators + separators);
    }

}
