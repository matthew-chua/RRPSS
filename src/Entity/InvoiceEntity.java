package Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class InvoiceEntity {

    private String StaffEntityName; // need on receipt?
    private LocalDateTime timestamp; // or the order time?
    private int tableNumber; // get from Table class?
    private ArrayList<AlaCarteEntity> menuItems; // get from Order class?
    private ArrayList<PackageEntity> packageItems;
    private double paymentDue; // do in order or invoice
    private Boolean membership; // checking here?
    

    // constructor
    public InvoiceEntity(String staffName, int tableNumber, ArrayList<AlaCarteEntity> menuItems, ArrayList<PackageEntity> packageItems, Boolean membership) {
        this.StaffEntityName = staffName;
        this.timestamp = LocalDateTime.now();
        this.membership = membership;
        this.menuItems = menuItems;
        this.packageItems = packageItems;
        this.tableNumber = tableNumber;
        
    }

    public void printInvoice(){
        System.out.println("INVOICE FOR TABLE NO: " + this.tableNumber);
        System.out.println("ALCARTE ITEMS:");
        for (int i=0; i<this.menuItems.size(); i++){
            System.out.println(this.menuItems.get(i).getName()+"-------------"+this.menuItems.get(i).getPrice());
        }

        System.out.println("PACKAGE ITEMS:");
        for (int i=0; i<this.packageItems.size(); i++){
            System.out.println(this.packageItems.get(i).getName()+"-------------"+this.packageItems.get(i).getPrice());
        }

        float total = calculateTotal();

        System.out.println("TOTAL W/O TAX: " + total);
        System.out.println("TOTAL  TAX: " + total*1.17);
        if (this.membership){
            System.out.println("TOTAL AFTER MEMBERSHIP DISCOUNT: " + total*1.17*0.9);
        }
        

    }


    public float calculateTotal(){

        float total = 0;

        for (int i=0; i<this.packageItems.size(); i++){
            total += packageItems.get(i).getPrice();
        }
        for (int i=0; i<this.menuItems.size(); i++){
            total += menuItems.get(i).getPrice();
        }
        return total;
    }
    // getters;

    // setters

}
