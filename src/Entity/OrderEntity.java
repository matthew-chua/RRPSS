package Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderEntity {

    private ArrayList<MenuItem> menuItems;
    // private ArrayList<Special> specials;
    private StaffEntity servingStaffEntity;
    private Table table;

    // constructor
    public OrderEntity(StaffEntity servingStaffEntity) {
        this.servingStaffEntity = servingStaffEntity;
    }

    // getters
    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    // public ArrayList<Special> getSpecials() {
        // return this.specials;
    // }

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

    public void addMenuItem(MenuItem item) {
        this.menuItems.add(item);
    }

    // public void addSpecial(Special specialItem) {
    //     this.specials.add(specialItem);
    // }

    public void removeMenuItem(int index) {
        this.menuItems.remove(index);
    }

    // public void removeSpecial(int index) {
    //     this.specials.remove(index);
    // }

}
