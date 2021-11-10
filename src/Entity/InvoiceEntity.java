package Entity;

import java.io.Serializable;
// import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
// import java.sql.Date;
import java.util.Dictionary;

import Boundary.Boundary;


public class InvoiceEntity implements Serializable{

    private String StaffEntityName; // need on receipt?
    // private LocalDateTime timestamp; // or the order time?
    private Date timestamp; // or the order time?
    private int tableNumber; // get from Table class?
    // private ArrayList<AlaCarteEntity> menuItems; // get from Order class?
    // private ArrayList<PackageEntity> packageItems;
    
    private Map<AlaCarteEntity, Integer> menuItems;
    private Map<PackageEntity, Integer> packageItems;

    private double paymentDue; // do in order or invoice
    private Boolean membership; // checking here?
    // private static String separators = "============";
    

    // constructor
    public InvoiceEntity(String staffName, int tableNumber, Map<AlaCarteEntity, Integer> menuItems, Map<PackageEntity, Integer> packageItems, Boolean membership) {
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

    public Map<AlaCarteEntity, Integer> getAlaCarteItems(){
        return this.menuItems;
    }

    public Map<PackageEntity, Integer> getPackageItems(){
        return this.packageItems;
    }

    // public void printInvoice(){
    //     System.out.println("INVOICE FOR TABLE NO: " + this.tableNumber);
    //     System.out.println("ALCARTE ITEMS:");
    //     for (int i=0; i<this.menuItems.size(); i++){
    //         System.out.println(this.menuItems.get(i).getName()+"-------------"+this.menuItems.get(i).getPrice());
    //     }

    //     System.out.println("PACKAGE ITEMS:");
    //     for (int i=0; i<this.packageItems.size(); i++){
    //         System.out.println(this.packageItems.get(i).getName()+"-------------"+this.packageItems.get(i).getPrice());
    //     }

    //     float total = calculateTotal();

    //     System.out.println("TOTAL W/O TAX: " + total);
    //     System.out.println("TOTAL  TAX: " + total*1.17);
    //     if (this.membership){
    //         System.out.println("TOTAL AFTER MEMBERSHIP DISCOUNT: " + total*1.17*0.9);
    //     }
    // }
    String invoiceString = "INVOICE FOR TABLE NO: ";
    float total;
    public String toString(){
        invoiceString += String.valueOf(this.tableNumber) + "\n\n";
        invoiceString += "ALA CARTE ITEMS\n\n";
        

        this.menuItems.forEach((alcItem, qty) -> {
            String qtyString = String.valueOf(qty);
            invoiceString += qtyString + " x ";
            String orderName = alcItem.getName();
            String updatedOrderName = ((orderName.length() > 25) ? (orderName.substring(0, 24) + "...") : orderName);
            // String price = String.valueOf(order.getMenuItems().get(i).getPrice());
            String price = String.format("%.2f", alcItem.getPrice());
            String alignedPrice = String.format("%" + String.valueOf(40 -  updatedOrderName.length())+ "s", price);
            invoiceString += updatedOrderName + alignedPrice + "\n";
        });

        // for (int i=0; i<this.menuItems.size(); i++){
        //     invoiceString += this.menuItems.get(i).getName()+"\t\t\t"+this.menuItems.get(i).getPrice() + "\n";
        // }
        invoiceString += "\nPACKAGE ITEMS:\n\n";
        // for (int i=0; i<this.packageItems.size(); i++){
        //     invoiceString += this.packageItems.get(i).getName()+ "\t\t\t" + String.valueOf(this.packageItems.get(i).getPrice()) + "\n";
        // }

        // float total = calculateTotal();
        // invoiceString += "\nTOTAL W/O TAX: " + String.valueOf(total) + "\n";
        // invoiceString += "TOTAL  TAX: " + String.valueOf(total*1.17) + "\n";
        // if (this.membership){
        //     invoiceString += "TOTAL AFTER MEMBERSHIP DISCOUNT: " + String.valueOf(total*1.17*0.9) + "\n";
        // }

        // copied
        total = 0;
        this.packageItems.forEach((k, v) -> total += (k.getPrice() * v ) );
        this.menuItems.forEach((k, v) -> total += (k.getPrice() * v ));

        String taxString = "TOTAL W/O TAX: ";
        String price = String.format("%.2f", total);
        String alignedPrice = String.format("%" + String.valueOf(40 -  taxString.length())+ "s", price);
        invoiceString += taxString + alignedPrice + "\n";

        invoiceString += Boundary.separators + Boundary.separators + Boundary.separators + "\n\n";
        
        String withTaxString = "TOTAL WITH TAX: ";
        price = String.format("%.2f", total*1.17);
        alignedPrice = String.format("%" + String.valueOf(40 -  withTaxString.length())+ "s", price);
        invoiceString += withTaxString + alignedPrice + "\n";

        if (this.membership){
            String membershipString = "TOTAL AFTER MEMBERSHIP DISCOUNT: ";
            price = String.format("%.2f", total*1.17*0.9);
            alignedPrice = String.format("%" + String.valueOf(40 -  membershipString.length())+ "s", price);
            invoiceString += membershipString + alignedPrice + "\n";
        }
        String toReturn = invoiceString;
        // orderString = "ALA CARTE ITEMS:\n";
        
        String invoiceString = "INVOICE FOR TABLE NO: ";
        total = 0;

        return toReturn;
    }

    double totalRevenue = 0;
    public double calculateTotal(){

        

        // for (int i=0; i<this.packageItems.size(); i++){
        //     total += packageItems.get(i).getPrice();
        // }
        // for (int i=0; i<this.menuItems.size(); i++){
        //     total += menuItems.get(i).getPrice();
        // }
        this.packageItems.forEach((item, qty) -> totalRevenue += (item.getPrice() * qty));
        this.menuItems.forEach((item, qty) -> totalRevenue += (item.getPrice() * qty));
        
        totalRevenue *= 1.17;
        if (membership){
            totalRevenue *= 0.9;
        }
        double toReturn = totalRevenue;
        totalRevenue = 0;
        return toReturn;
    }
    // getters;


    // setters

}
