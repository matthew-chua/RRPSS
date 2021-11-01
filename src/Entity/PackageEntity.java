package Entity;
import java.io.Serializable;
import java.util.ArrayList;

// this is a single package object that contains multiple alacarte objects in an arraylist
public class PackageEntity implements Serializable {

    //instantiate attributes
    private String name;
    private String desc;
    private double price;
    private ArrayList<AlaCarteEntity> items;
    
    public PackageEntity(String name, String desc, double price){
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.items = new ArrayList<AlaCarteEntity>();
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

    public ArrayList<AlaCarteEntity> getItems(){
        return this.items;
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

    // straight awway pass in array of alacarte items to make package
    public void setItems(ArrayList<AlaCarteEntity> inputItems){
        this.items = inputItems;
    }

    public void addItem(AlaCarteEntity alacarteItem){
        this.items.add(alacarteItem);
    }

    public void removeItem(AlaCarteEntity alacarteItem){
        this.items.remove(alacarteItem);
    }

}
