package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class OrderEntity implements Serializable{

    // private ArrayList<AlaCarteEntity> menuItems;
    // private ArrayList<PackageEntity> specials;

    private Map<AlaCarteEntity, Integer> menuItems;
    private Map<PackageEntity, Integer> specials;

    private StaffEntity servingStaffEntity;
    private Table table;

    // constructor
    public OrderEntity(StaffEntity servingStaffEntity, Table table) {
        this.servingStaffEntity = servingStaffEntity;
        this.table = table;
        // this.menuItems = new ArrayList<AlaCarteEntity>();
        // this.specials = new ArrayList<PackageEntity>();

        this.menuItems = new HashMap<AlaCarteEntity, Integer>();
        this.specials = new HashMap<PackageEntity, Integer>();
    }

    // getters
    // public ArrayList<AlaCarteEntity> getMenuItems() {
    //     return this.menuItems;
    // }

    // public ArrayList<PackageEntity> getSpecials() {
    //     return this.specials;
    // }

    public Map<AlaCarteEntity, Integer> getMenuItems() {
        return this.menuItems;
    }

    public Map<PackageEntity, Integer> getSpecials() {
        return this.specials;
    }

    public StaffEntity getStaffEntity() {
        return this.servingStaffEntity;
    }

    public Table getTable() {
        return this.table;
    }

    // setters
    public void setTable(Table table) {
        this.table = table;
    }

    public void addMenuItem(AlaCarteEntity item, int qty) {
        // this.menuItems.add(item);
        // this.menuItems.put(item, menuItems.get(item)==null ? 1 : menuItems.get(item)+1);
        this.menuItems.put(item, (menuItems.getOrDefault(item, 0) + qty) );
    }

    public void addSpecial(PackageEntity specialItem, int qty) {
        // this.specials.add(specialItem);
        // this.specials.put(specialItem, specials.get(specialItem)==null ? 1 : specials.get(specialItem)+1);
        this.specials.put(specialItem, (specials.getOrDefault(specialItem, 0) + qty ));
    }

    public void removeMenuItem(AlaCarteEntity item, int qty) {
        // this.menuItems.remove(index);
        int currentQty = menuItems.getOrDefault(item, 0);
        if (qty >= currentQty){
            this.menuItems.remove(item);
        }else{
            this.menuItems.put(item, (currentQty - qty) );
        }
    }

    public void removeSpecial(PackageEntity item, int qty) {
        // this.specials.remove(index);

        int currentQty = specials.getOrDefault(item, 0);
        if (qty >= currentQty){
            this.specials.remove(item);
        }else{
            this.specials.put(item, (currentQty - qty) );
        }

    }

    // public void removeMenuItem(int index) {
    //     this.menuItems.remove(index);
    // }

    // public void removeSpecial(int index) {
    //     this.specials.remove(index);
    // }

    // public float calculateTotal(){

    //     float total = 0;

    //     for (int i=0; i<this.specials.size(); i++){
    //         total += packageItems.get(i).getPrice();
    //     }
    //     for (int i=0; i<this.menuItems.size(); i++){
    //         total += menuItems.get(i).getPrice();
    //     }
    //     return total;
    // }
}

