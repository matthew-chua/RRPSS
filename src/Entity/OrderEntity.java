package Entity;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 * Is a order per customer
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class OrderEntity implements Serializable {
    /**
     * The AlaCarte items ordered
     */
    private Map<AlaCarteEntity, Integer> menuItems;
    /**
     * The Package items ordered
     */
    private Map<PackageEntity, Integer> specials;

    /**
     * The Staff responsible for this order
     */
    private StaffEntity servingStaffEntity;
    /**
     * Table at which this order was placed
     */
    private Table table;

    /**
     * Creates a new Order with Staff responsible and table at which this order was
     * placed
     * 
     * @param servingStaffEntity This Order's Staff
     * @param table              This Order's table details
     */
    public OrderEntity(StaffEntity servingStaffEntity, Table table) {
        this.servingStaffEntity = servingStaffEntity;
        this.table = table;

        this.menuItems = new HashMap<AlaCarteEntity, Integer>();
        this.specials = new HashMap<PackageEntity, Integer>();
    }

    /**
     * Gets the AlaCarte items of this Order
     * 
     * @return this Order's AlaCarte items
     */
    public Map<AlaCarteEntity, Integer> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Gets the Package items of this Order
     * 
     * @return this Order's Package items
     */
    public Map<PackageEntity, Integer> getSpecials() {
        return this.specials;
    }

    /**
     * Gets the staff of this Order
     * 
     * @return this Order's Staff object
     */
    public StaffEntity getStaffEntity() {
        return this.servingStaffEntity;
    }

    /**
     * Gets the table of this Order
     * 
     * @return this Order's Table object
     */
    public Table getTable() {
        return this.table;
    }

    /**
     * Changes the Table object of this Order
     * 
     * @param table This Order's new Table object
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Adds an AlaCarte item to this Order
     * 
     * @param item This Order's AlaCarte item to be added
     * @param qty  This Order's number of AlaCarte item to be added
     */
    public void addMenuItem(AlaCarteEntity item, int qty) {
        this.menuItems.put(item, (menuItems.getOrDefault(item, 0) + qty));
    }

    /**
     * Adds a Package item to this Order
     * 
     * @param specialItem This Order's Package item to be added
     * @param qty         This Order's number of Package item to be added
     */
    public void addSpecial(PackageEntity specialItem, int qty) {
        this.specials.put(specialItem, (specials.getOrDefault(specialItem, 0) + qty));
    }

    /**
     * Removes an AlaCarte item from this Order
     * 
     * @param item This Order's AlaCarte item to be removed
     * @param qty  This Order's number of AlaCarte item to be removed
     */
    public void removeMenuItem(AlaCarteEntity item, int qty) {
        int currentQty = menuItems.getOrDefault(item, 0);
        if (qty >= currentQty) {
            this.menuItems.remove(item);
        } else {
            this.menuItems.put(item, (currentQty - qty));
        }
    }

    /**
     * Removes a Package item from this Order
     * 
     * @param item This Order's Package item to be removed
     * @param qty  This Order's number of Package item to be removed
     */
    public void removeSpecial(PackageEntity item, int qty) {
        int currentQty = specials.getOrDefault(item, 0);
        if (qty >= currentQty) {
            this.specials.remove(item);
        } else {
            this.specials.put(item, (currentQty - qty));
        }
    }
}
