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

    private String orderString = "ALA CARTE ITEMS:\n";
    

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

        // for (int i=0; i<order.getMenuItems().size(); i++){
        //     String orderName = order.getMenuItems().get(i).getName();
        //     String updatedOrderName = ((orderName.length() > 25) ? (orderName.substring(0, 24) + "...") : orderName);
        //     // String price = String.valueOf(order.getMenuItems().get(i).getPrice());
        //     String price = String.format("%.2f", order.getMenuItems().get(i).getPrice());
        //     String alignedPrice = String.format("%" + String.valueOf(40 -  updatedOrderName.length())+ "s", price);
        //     orderString += updatedOrderName + alignedPrice + "\n";
        // }

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

        // for (int i=0; i<order.getSpecials().size(); i++){
        //     String orderName = order.getSpecials().get(i).getName();
        //     String updatedOrderName = ((orderName.length() > 25) ? (orderName.substring(0, 24) + "...") : orderName);
        //     String price = String.format("%.2f", order.getSpecials().get(i).getPrice());
        //     String alignedPrice = String.format("%" + String.valueOf(40 -  updatedOrderName.length())+ "s", price);
        //     orderString +=  updatedOrderName + alignedPrice + "\n\n";
        // }

        // order.getSpecials().forEach(item -> total += item.getPrice());
        // order.getMenuItems().forEach(item -> total += item.getPrice());

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
            stringToPrint += String.valueOf(i+1) + " " + items.get(i).getName() + "\n";
        }
        getUserChoices(items.size(), callback, false, stringToPrint);

    }

    public void getQty(int max, ChoiceObserver callback){
        getUserChoices(max, callback, false, orderManagerTitle + "How many would you like to add / remove?\n");
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
        System.out.println("Shui Qi Eating House Pte Ltd");
        System.out.println(separators + separators + separators);
    }

}
