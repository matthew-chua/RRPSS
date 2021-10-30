package Entity;
import java.util.ArrayList;

// this is a single package object that contains multiple alacarte objects in an arraylist
public class PackageEntity {

    //instantiate attributes
    private String name;
    private String desc;
    private double price;
    private ArrayList<AlaCarteEntity> items;
    
    public PackageEntity(String name, String desc, double price){
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        return desc;
    }

    public double getPrice(){
        return price;
    }

    public ArrayList<AlaCarteEntity> getItems(){
        return items;
    }

    public void setName(String inputName){
        name = inputName;
    }

    public void setDesc(String inputDesc){
        desc = inputDesc;
    }

    public void setPrice(double inputPrice){
        price = inputPrice;
    }

    // straight awway pass in array of alacarte items to make package
    public void setItems(ArrayList<AlaCarteEntity> inputItems){
        items = inputItems;
    }

    public void addItem(AlaCarteEntity alacarteItem){
        items.add(alacarteItem);
    }

    public void removeItem(AlaCarteEntity alacarteItem){
        items.remove(alacarteItem);
    }

    // public void printPackageContents(){
    //     for(AlaCarteEntity item: this.items){
    //         if(!item.getRemovalStatus()){
    //             System.out.printf("\n%d. %s (%s) -- $%d",
    //             item.getItemId(),item.getName(),item.getType(),item.getPrice());
    //         }
    //     }
    // }
}
