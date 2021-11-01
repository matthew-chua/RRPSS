package Entity;

import java.io.Serializable;

public class Table implements Serializable{
    private int number;
    private int capacity;
    private boolean availability;
    private OrderEntity order;

    Table(int number, int capacity, Boolean availability){
        this.number = number;
        this.capacity = capacity;
        this.availability = availability;
        this.order = null;
    }

    public int getNumber(){
        return number;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean getAvailability(){
        return availability;
    }

    public void setAvailability(boolean y){
        availability = y;
    }

    public OrderEntity getOrder(){
        return order;
    }

    public void setOrder(OrderEntity o){
        order = o;
    }

}

