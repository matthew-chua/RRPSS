package Entity;

import java.io.Serializable;

// this is a single alacarte object
public class AlaCarteEntity implements Serializable {
    public enum Type{
        APPETISER(1),
        MAINCOURSE(2),
        DRINK(3),
        DESSERT(4);

        int num;
        Type(int n) {
            num = n;
        }
    }
    //instantiate attributes
    private String name;
    private String desc;
    private double price;
    private Type type;

    // constructor
    public AlaCarteEntity(String name, String desc, double price, Type type){
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public String getDesc(){
        return this.desc;
    }

    public double getPrice(){
        return this.price;
    }

    public Type getType(){
        return this.type;
    }

    public void setType(Type inputType){
        this.type = inputType;
    }

    public void setName(String inputName){
        this.name = inputName;
    }

    public void setDesc(String inputDesc){
        this.desc = inputDesc;
    }

    public void setPrice(double inputPrice){
        this.price = inputPrice;
    }
}

