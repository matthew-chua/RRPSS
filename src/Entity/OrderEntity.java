package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderEntity implements Serializable{

    private ArrayList<AlaCarteEntity> menuItems;
    private ArrayList<PackageEntity> specials;
    private StaffEntity servingStaffEntity;
    private Table table;

    // constructor
    public OrderEntity(StaffEntity servingStaffEntity) {
        this.servingStaffEntity = servingStaffEntity;
    }

    // getters
    public ArrayList<AlaCarteEntity> getMenuItems() {
        return this.menuItems;
    }

    public ArrayList<PackageEntity> getSpecials() {
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

    public void addMenuItem(AlaCarteEntity item) {
        this.menuItems.add(item);
    }

    public void addSpecial(PackageEntity specialItem) {
        this.specials.add(specialItem);
    }

    public void removeMenuItem(int index) {
        this.menuItems.remove(index);
    }

    public void removeSpecial(int index) {
        this.specials.remove(index);
    }

}
