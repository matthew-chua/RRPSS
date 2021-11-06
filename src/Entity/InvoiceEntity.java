package Entity;

import java.io.Serializable;
// import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
// import java.sql.Date;

import Boundary.Boundary;


public class InvoiceEntity implements Serializable{

    private String StaffEntityName; // need on receipt?
    // private LocalDateTime timestamp; // or the order time?
    private Date timestamp; // or the order time?
    private int tableNumber; // get from Table class?
    private ArrayList<AlaCarteEntity> menuItems; // get from Order class?
    private ArrayList<PackageEntity> packageItems;
    private double paymentDue; // do in order or invoice
    private Boolean membership; // checking here?
    

    // constructor
    public InvoiceEntity(String staffName, int tableNumber, ArrayList<AlaCarteEntity> menuItems, ArrayList<PackageEntity> packageItems, Boolean membership) {
        this.StaffEntityName = staffName;
        // this.timestamp = LocalDateTime.now();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long millis=System.currentTimeMillis(); 
        this.timestamp = new Date(millis);
        this.membership = membership;
        this.menuItems = menuItems;
        this.packageItems = packageItems;
        this.tableNumber = tableNumber;
    }

    public Date getTimeStamp(){
        return this.timestamp;
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

    public String toString(){
        String invoiceString = "INVOICE FOR TABLE NO: " + String.valueOf(this.tableNumber) + "\n\n";
        invoiceString += "ALA CARTE ITEMS\n";
        invoiceString += Boundary.separators + Boundary.separators + Boundary.separators + "\n";

        for (int i=0; i<this.menuItems.size(); i++){
            invoiceString += this.menuItems.get(i).getName()+"\t\t\t"+this.menuItems.get(i).getPrice() + "\n";
        }
        invoiceString += "\nPACKAGE ITEMS:\n";
        for (int i=0; i<this.packageItems.size(); i++){
            invoiceString += this.packageItems.get(i).getName()+ "\t\t\t" + String.valueOf(this.packageItems.get(i).getPrice()) + "\n";
        }

        float total = calculateTotal();
        invoiceString += "\nTOTAL W/O TAX: " + String.valueOf(total) + "\n";
        invoiceString += "TOTAL  TAX: " + String.valueOf(total*1.17) + "\n";
        if (this.membership){
            invoiceString += "TOTAL AFTER MEMBERSHIP DISCOUNT: " + String.valueOf(total*1.17*0.9) + "\n";
        }

        return invoiceString;
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
