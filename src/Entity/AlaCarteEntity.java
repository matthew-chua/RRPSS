package Entity;

import java.io.Serializable;

/**
 * 
 * Is a single AlaCarte object
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class AlaCarteEntity implements Serializable {
    /**
     * The different types of AlaCarte object
     */
    public enum Type {
        APPETISER(1), MAINCOURSE(2), DRINK(3), DESSERT(4);

        int num;

        Type(int n) {
            num = n;
        }
    }

    /**
     * The name of this AlaCarte item
     */
    private String name;
    /**
     * The description of this AlaCarte item
     */
    private String desc;
    /**
     * The price of this AlaCarte item
     */
    private double price;
    /**
     * The type of this AlaCarte item
     */
    private Type type;

    /**
     * Creates a new AlaCarte item with name
     * 
     * @param name  This item's name
     * @param desc  This item's description
     * @param price This item's price
     * @param type  This item's type
     */
    public AlaCarteEntity(String name, String desc, double price, Type type) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.type = type;
    }

    /**
     * Gets the name of this AlaCarte item
     * 
     * @return this AlaCarte item's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the description of this AlaCarte item
     * 
     * @return this AlaCarte item's description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Gets the price of this AlaCarte item
     * 
     * @return this AlaCarte item's price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the type of this AlaCarte item
     * 
     * @return this AlaCarte item's type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Changes the name of this AlaCarte item
     * 
     * @param inputName This AlaCarte item's new name
     */
    public void setName(String inputName) {
        this.name = inputName;
    }

    /**
     * Changes the description of this AlaCarte item
     * 
     * @param inputDesc This AlaCarte item's new description
     */
    public void setDesc(String inputDesc) {
        this.desc = inputDesc;
    }

    /**
     * Changes the price of this AlaCarte item
     * 
     * @param inputPrice This AlaCarte item's new price
     */
    public void setPrice(double inputPrice) {
        this.price = inputPrice;
    }

    /**
     * Changes the type of this AlaCarte item
     * 
     * @param inputType This AlaCarte item's new type
     */
    public void setType(Type inputType) {
        this.type = inputType;
    }
}
