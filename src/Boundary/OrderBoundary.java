package Boundary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.swing.MenuElement;

import Controller.OrderController;
import Entity.AlaCarteEntity;
import Entity.MenuEntity;
import Entity.OrderEntity;
import Entity.PackageEntity;
import Helpers.*;




/**
 * 
 * Is the boundary that is involved with all Order flows.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class OrderBoundary extends Boundary{
    
    /** Constructor for order boundary */
    public OrderBoundary(){}

    // Strings
    /** Manage Order Title String */
    private String orderManagerTitle = separators + " Manage Orders " + separators + "\n";

    /** Table to serve description String */
    private String tableToServeString = "Please enter a table to serve: (1 - 10)\n" + "Otherwise, enter 0 to return back to the main menu";

    /** Total price */
    private float total = 0;

    /**
     * Get user's choice on whether to create an order for the given table.
     * @param tableNumber is an int representing the table number that was choice
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void createOrderForTable(int tableNumber, ChoiceObserver callback){

        int numberOfChoices = 1;
        boolean isRecurring = false;

        String createOrderString = "Table number " + String.valueOf(tableNumber) + " does not have an existing order.\nPress 1 to create order";

        getUserChoices(numberOfChoices, callback, isRecurring, orderManagerTitle + createOrderString);
    }

    /** Display results that the table was created */
    public void showTableCreated(){
        displayResults("Table Created!");
    }

    /**
     * Get the user's choice to update the order
     * @param tableNumber       is an int representing the current table number
     * @param order             is an OrderEntity representing the current order
     * @param callback          is the anonymous callback function to run when user inputs a valid integer
     */
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

    /**
     * Get the choice of table to edit
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getTableChoice(ChoiceObserver callback){
        String stringToPrint = orderManagerTitle + tableToServeString;
        getUserChoices(10, callback, false, stringToPrint);
    }

    /** String representing all ala carte orders */
    private String orderString = "ALA CARTE ITEMS:\n";

    /**
     * Transforms an OrderEntity into a formatted string representing the order.
     * @param order is an OrderEntity representing the current order.
     * @return  Formatted String representing the order.
     */
    private String getOrderString(OrderEntity order){
        
        order.getMenuItems().forEach((alcItem, qty) -> {
            String qtyString = String.valueOf(qty);
            orderString += qtyString + " x ";
            String orderName = alcItem.getName();
            String updatedOrderName = ((orderName.length() > 25) ? (orderName.substring(0, 24) + "...") : orderName);
            // String price = String.valueOf(order.getMenuItems().get(i).getPrice());
            String price = String.format("%.2f", alcItem.getPrice());
            String alignedPrice = String.format("%" + String.valueOf(40 -  updatedOrderName.length())+ "s", price);
            orderString += updatedOrderName + alignedPrice + "\n";
        });

        orderString += "\nPACKAGE ITEMS:\n";
        order.getSpecials().forEach((pkgItem, qty) -> {
            String qtyString = String.valueOf(qty);
            orderString += qtyString + " x ";
            String orderName = pkgItem.getName();
            String updatedOrderName = ((orderName.length() > 25) ? (orderName.substring(0, 24) + "...") : orderName);
            // String price = String.valueOf(order.getMenuItems().get(i).getPrice());
            String price = String.format("%.2f", pkgItem.getPrice());
            String alignedPrice = String.format("%" + String.valueOf(40 -  updatedOrderName.length())+ "s", price);
            orderString += updatedOrderName + alignedPrice + "\n";
        });

        order.getSpecials().forEach((k, v) -> total += (k.getPrice() * v ) );
        order.getMenuItems().forEach((k, v) -> total += (k.getPrice() * v ));

        String taxString = "TOTAL W/O TAX: ";
        String price = String.format("%.2f", total);
        String alignedPrice = String.format("%" + String.valueOf(40 -  taxString.length())+ "s", price);
        orderString += taxString + alignedPrice + "\n";

        orderString += separators + separators + separators + "\n\n";
        total = 0;
        String toReturn = orderString;
        orderString = "ALA CARTE ITEMS:\n";

        return toReturn;
    }

    /**
     * Get the user's choice on whether they wish to add / remove ala carte items or packages.
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getMenuItemType(ChoiceObserver callback){

        String stringToPrint = orderManagerTitle;
        String option1 = "1. Add / Remove Ala Carte Item\n";
        String option2 = "2. Add / Remove Package Item";
        getUserChoices(2, callback, true, stringToPrint + option1 + option2);
    }

    /**
     * Get the index of the ala carte item the user wishes to select
     * @param items         is an ArrayList of AlaCarteEntity from the menu
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getAlaCarteItemIndex(ArrayList<AlaCarteEntity> items, ChoiceObserver callback){
        
        // String menuString = menu.toString();
        // String 
        String stringToPrint = orderManagerTitle;

        for (int i=0; i<items.size(); i++){
            stringToPrint += String.valueOf(i+1) + ". " + items.get(i).getName() + "\n";
        }

        getUserChoices(items.size(), callback, false, stringToPrint);

    }

    /**
     * Get the index of the package item the user wishes to select
     * @param items         is an ArrayList of PackageEntity from the menu
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getPackageIndex(ArrayList<PackageEntity> items, ChoiceObserver callback){
        
        String stringToPrint = orderManagerTitle;
        for (int i=0; i<items.size(); i++){
            stringToPrint += String.valueOf(i+1) + " " + items.get(i).getName() + "\n";
        }
        getUserChoices(items.size(), callback, false, stringToPrint);

    }

    /**
     * Get the quantity of items the user wishes to add or remove
     * @param max          the maximum qty of items a user can add at a time
     * @param callback     is the anonymous callback function to run when user inputs a valid integer
     */
    public void getQty(int max, ChoiceObserver callback){
        getUserChoices(max, callback, false, orderManagerTitle + "How many would you like to add / remove?\n");
    }
    
    /**
     * Gets whether the user is a member or not.
     * @param callback      is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserMembership(ChoiceObserver callback){
        String stringToPrint = orderManagerTitle;
        stringToPrint += "Is there a valid membership?\n";
        stringToPrint += "1. Yes\n";
        stringToPrint += "2. No\n";
        getUserChoices(2, callback, false, stringToPrint);
    }

    /** Prints the header of the invoice */
    public void printInvoiceFormat(){
        System.out.println(separators + separators + separators);
        System.out.println("Shui Qi Eating House Pte Ltd");
        System.out.println(separators + separators + separators);
    }

}
