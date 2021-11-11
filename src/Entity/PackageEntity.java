package Entity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a single package object that contains multiple alacarte
 * objects in an arraylist
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class PackageEntity implements Serializable {

    /**
     * The name of this Package
     */
    private String name;
    /**
     * The description of this Package
     */
    private String desc;
    /**
     * The price of this Package
     */
    private double price;
    /**
     * The items available in this Package
     */
    private ArrayList<AlaCarteEntity> items;
    
    /**
     * Creates a new Package with the name, description and price
     * @param name This Package's name
     * @param desc This Package's description
     * @param price This Packages's price
     */
    public PackageEntity(String name, String desc, double price){
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.items = new ArrayList<AlaCarteEntity>();
    }

    /**
     * Gets the name of this Package
     * @return this Package's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the description of this Package
     * @return this Package's description
     */
    public String getDesc(){
        return this.desc;
    }

    /**
     * Gets the price of this Package
     * @return this Package's price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Gets the AlaCarte items available in this Package
     * @return this Package's ArrayList of AlaCarte items available
     */
    public ArrayList<AlaCarteEntity> getItems(){
        return this.items;
    }

    /**
     * Changes the name of this Package
     * @param inputName This Package's new name
     */
    public void setName(String inputName){
        this.name = inputName;
    }

    /**
     * Changes the description of this Package
     * @param inputDesc This Package's new description
     */
    public void setDesc(String inputDesc){
        this.desc = inputDesc;
    }

    /**
     * Changes the price of this Package
     * @param inputPrice This Package's new price
     */
    public void setPrice(double inputPrice){
        this.price = inputPrice;
    }

    /**
     * Changes the AlaCarte items available in this Package
     * @param inputItems This Package's new AlaCarte items
     */
    public void setItems(ArrayList<AlaCarteEntity> inputItems){
        this.items = inputItems;
    }

    /**
     * Adds AlaCarte items to this Package
     * @param alacarteItem This Package's additional AlaCarte item
     */
    public void addItem(AlaCarteEntity alacarteItem){
        this.items.add(alacarteItem);
    }

    /**
     * Removes AlaCarte items from this Package
     * @param alacarteItem This Package's AlaCarte item to be removed
     */
    public void removeItem(AlaCarteEntity alacarteItem){
        this.items.remove(alacarteItem);
    }

}
