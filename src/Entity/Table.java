package Entity;

import java.io.Serializable;

/**
 * This is a single table object
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class Table implements Serializable {
    /**
     * The table number of this table
     */
    private int number;
    /**
     * The seating capacity of this table
     */
    private int capacity;
    /**
     * The availability of this table
     */
    private boolean availability;
    /**
     * The order associated with this table
     */
    private OrderEntity order;

    /**
     * Creates a new table
     * 
     * @param number       This table's number
     * @param capacity     This table's seating capacity
     * @param availability This table's availability
     */
    Table(int number, int capacity, Boolean availability) {
        this.number = number;
        this.capacity = capacity;
        this.availability = availability;
        this.order = null;
    }

    /**
     * Gets the table number of this table
     * 
     * @return this table's table number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the seating capacity of this table
     * 
     * @return this table's seating capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the availability of this table
     * 
     * @return this table's availability
     */
    public boolean getAvailability() {
        return availability;
    }

    /**
     * Changes the availability of this table
     * 
     * @param y this table's availability status
     */
    public void setAvailability(boolean y) {
        availability = y;
    }

    /**
     * Gets the orders placed at this table
     * 
     * @return this table's orders placed
     */
    public OrderEntity getOrder() {
        return order;
    }

    /**
     * Changes the orders placed at this table
     * 
     * @param o this table's orders placed
     */
    public void setOrder(OrderEntity o) {
        order = o;
    }

}
