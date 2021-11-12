package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import Boundary.Boundary;

/**
 * 
 * Is a single Invoice object
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class InvoiceEntity implements Serializable{
    /**
     * The time that the invoice is created
     */
    private Date timestamp;
    /**
     * The table assigned to the order
     */
    private int tableNumber;
    /**
     * The menu items ordered
     */
    private Map<AlaCarteEntity, Integer> menuItems;
    /**
     * The package items ordered
     */
    private Map<PackageEntity, Integer> packageItems;
    /**
     * The membership status of the customer
     */
    private Boolean membership;

    private String staffEntityName;
    

    /**
     * Creates a new Invoice with name
     * @param staffName This Invoice's staff assigned
     * @param tableNumber This Invoice's table number
     * @param menuItems This Invoice's menu items ordered
     * @param packageItems This Invoice's package items ordered
     * @param membership This Invoice's customer's membership status
     */
    public InvoiceEntity(String staffName, int tableNumber, Map<AlaCarteEntity, Integer> menuItems, Map<PackageEntity, Integer> packageItems, Boolean membership) {
        this.staffEntityName = staffName;
        long millis=System.currentTimeMillis(); 
        this.timestamp = new Date(millis);
        this.tableNumber = tableNumber;
        this.menuItems = menuItems;
        this.packageItems = packageItems;
        this.membership = membership;
    }

    /**
     * Gets the time stamp of this Invoice
     * @return this Invoice's time stamp
     */
    public Date getTimeStamp(){
        return this.timestamp;
    }

    /**
     * Gets the menu items of this Invoice
     * @return this Invoice's menu items
     */
    public Map<AlaCarteEntity, Integer> getAlaCarteItems(){
        return this.menuItems;
    }

    /**
     * Gets the package items of this Invoice
     * @return this Invoice's package items
     */
    public Map<PackageEntity, Integer> getPackageItems(){
        return this.packageItems;
    }

    /**
     * Collates receipt details into a single string to display
     * @return string to display when concluding order
     */
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
            String price = String.format("%.2f", alcItem.getPrice());
            String alignedPrice = String.format("%" + String.valueOf(40 -  updatedOrderName.length())+ "s", price);
            invoiceString += updatedOrderName + alignedPrice + "\n";
        });

        invoiceString += "\nPACKAGE ITEMS:\n\n";
        
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
        total = 0;

        return toReturn;
    }

    /**
     * Calculate the price per Invoice
     * @return this Invoice's total price
     */
    double totalRevenue = 0;
    public double calculateTotal(){
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
}
