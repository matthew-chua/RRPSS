package Entity;
public class MenuItem {
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
    private int id;
    private String name;
    private String desc;
    private double price;
    private Type type;
    private boolean isRemoved;

    public MenuItem(int item_id){
        id = item_id;
        isRemoved = false;
    }

    public int getItemId(){
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

    public Type getType(){
        return type;
    }

    public boolean getRemovalStatus(){
        return isRemoved;
    }

    public void setType(Type inputType){
        type = inputType;
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

    public void removeItem(){
        isRemoved = true;
    }

public class MenuItem {


    private int itemID;

    public MenuItem(int itemID){
        this.itemID = itemID;
    }
    
}
