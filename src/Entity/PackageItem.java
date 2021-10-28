package Entity;
import java.util.ArrayList;

public class PackageItem {

    //instantiate attributes
    private int id;
    private String name;
    private String desc;
    private double price;
    // private MenuItem[] items;
    private ArrayList<MenuItem> items;
    private boolean isRemoved;
    

    public PackageItem(int package_id){
        id = package_id;
    }

    public int getPackageId(){
        return id;
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

    public ArrayList<MenuItem> getItems(){
        return items;
    }

    public boolean getRemovalStatus(){
        return isRemoved;
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

    public void setItems(ArrayList<MenuItem> inputItems){
        items = inputItems;
    }

    public void addItem(MenuItem menuItem){
        items.add(menuItem);
    }

    public void removeItem(MenuItem menuItem){
        items.remove(menuItem);
    }

    public void removePackage(){
        isRemoved = true;
    }

    public void printPackageContents(){
        for(MenuItem item: this.items){
            if(!item.getRemovalStatus()){
                System.out.printf("\n%d. %s (%s) -- $%d",
                item.getItemId(),item.getName(),item.getType(),item.getPrice());
            }
        }
    }
}
