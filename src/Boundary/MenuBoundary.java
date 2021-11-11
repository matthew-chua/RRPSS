package Boundary;
import Helpers.*;
import Entity.AlaCarteEntity;
import Entity.AlaCarteEntity.Type;
import Entity.PackageEntity;
import java.util.ArrayList;
/**
 * 
 * Is the boundary that is involved with all Menu flows.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 */
public class MenuBoundary extends Boundary {
    public MenuBoundary(){}

    /* =========== Manage Menu Title =========== */
    private String manageMenuTitle = separators + " Manage Menu " + separators + "\n";
    private String addItemTitle = separators + " Add New Menu Item " + separators + "\n";
    private String updateItemTitle = separators + " Update Menu Item " + separators + "\n";
    private String removeItemTitle = separators + " Remove Menu Item " + separators + "\n";
    private String addPackageTitle = separators + " Add New Package " + separators + "\n";
    private String updatePackageTitle = separators + " Update Package " + separators + "\n";
    private String removePackageTitle = separators + " Remove Package " + separators + "\n";


    /**
     * Transforms an ArrayList of AlaCarteEntity and ArrayList of PackageEntity into a formatted string representing the menu
     * @param items is an ArrayList of AlaCarteEntity representing the menu's ala carte items
     * @param packages is an ArrayList of PackageEntity representing the menu's packages
     * @return  a string representing the menu.
     */
    public String printMenu(ArrayList<AlaCarteEntity> items, ArrayList<PackageEntity> packages){
        // print menu
        if (items.size() == 0 && packages.size() == 0) {
            // System.out.printf("No items in menu. Add a menu item to start");
            return "No items in menu. Add a menu item to start \n";
        }

        String toPrint = manageMenuTitle + "Menu: \n";
        toPrint += separators+separators+separators;
        toPrint += "\n" + "Chef KuKu's Italian Cock with Tails\n";
        toPrint += separators+separators+separators + "\n";
        toPrint += " - Appetisers:\n";

        for(AlaCarteEntity item : items){
            if (item.getType() == Type.APPETISER){
                toPrint += formatItem(item);
            }
        }

        
        // print main courses
        toPrint += " - Main Courses:\n";
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.MAINCOURSE){
                // System.out.printf("\n    - %s          $%.2f", item.getName(), item.getPrice());
                // toPrint += " - " + item.getName() + "\t" + String.format("%.2f", item.getPrice()) + "\n";
                toPrint += formatItem(item);
            }
        }

        // // print drinks
        toPrint += " - Drinks:\n";
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.DRINK){
                // System.out.printf("\n    - %s          $%.2f", item.getName(), item.getPrice());
                toPrint += formatItem(item);
            }
        }

        // // print desserts
        // System.out.println("\n- Desserts: ");
        toPrint += " - Desserts:\n";
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.DESSERT){
                // System.out.printf("\n    - %s          $%.2f", item.getName(), item.getPrice());
                toPrint += formatItem(item);
            }
        }

        // // print packages
        // System.out.println("\nPackages: ");
        toPrint += " - Packages:\n";
        for(PackageEntity pkg : packages){
            // System.out.printf("\n- %s          $%.2f", pkg.getName(), pkg.getPrice());
            toPrint += "   " + " - " + pkg.getName() + "\t\t\t" + 
            String.format("%.2f", pkg.getPrice()) + "\n"
            + "     -----------------" + "\n";
            
            ArrayList<AlaCarteEntity> packageItems = pkg.getItems();
            for(AlaCarteEntity pkgItem : packageItems){
                // System.out.printf("\n    - %s          $%.2f", pkgItem.getName(), pkgItem.getPrice());
                toPrint += formatItem(pkgItem);
            }
        }
        toPrint += "\n";
        return toPrint;
    }


    /**
     * Transforms an Ala Carte Entity into a formatted String
     * @param item
     * @return
     */
    private String formatItem(AlaCarteEntity item){
        return "    " + " - " + item.getName() + "\t\t\t" 
        + String.format("%.2f", item.getPrice()) + "\n"
        + "\t" + item.getDesc() + "\r\n\n";
    }


    /**
     * Get User's Choice for what menu component they would like to manage
     * @param items is an ArrayList of AlaCarteEntity representing the menu's ala carte items
     * @param packages is an ArrayList of PackageEntity representing the menu's packages
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getUserMenuChoice(ArrayList<AlaCarteEntity> items, ArrayList<PackageEntity> packages, ChoiceObserver callback){
        
        
        /* =========== User's Menu choices =========== */
        String choice1String = "1. Add a new menu item\n";
        String choice2String = "2. Update a menu item\n";
        String choice3String = "3. Remove a menu item\n";
        String choice4String = "4. Add a new package\n";
        String choice5String = "5. Update a package\n";
        String choice6String = "6. Remove a package\n";

        // String to print
        String stringToPrint = printMenu(items, packages) + choice1String + choice2String +
        choice3String + choice4String + choice5String + choice6String;

        // Get the user's choice
        int numberOfChoices = 6;
        boolean isRecurring = false;
        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    //////////////// Print banners for each manage menu choice ///////////////////////
    /**
     * Print banners for each manage menu choice
     * @param choice is an int representing the user's previous choice to which the title will be appropriately printed out
     */
    public void printTitle(int choice){
        switch(choice){
            case 1:
                System.out.println(addItemTitle);
                break;
            
            case 2:
                System.out.println(updateItemTitle);
                break;
            
            case 3:
                System.out.println(removeItemTitle);
                break;
            
            case 4:
                System.out.println(addPackageTitle);
                break;
            
            case 5:
                System.out.println(updatePackageTitle);
                break;
            
            case 6:
                System.out.println(removePackageTitle);
                break; 
        }
    }

    /**
     * Get the appropriate title for the current UI flow
     * @param choice is an int representing the user's previous choice to which the title will be appropriately printed out
     * @return the string representing the title of the UI that the user is currently viewing
     */
    private String getTitle(int choice){
        String stringToPrint = "";
        switch(choice){
            case 1:
                stringToPrint += addItemTitle;
                break;
            
            case 2:
                stringToPrint += updateItemTitle;
                break;
            
            case 3:
                stringToPrint += removeItemTitle;
                break;
            
            case 4:
                stringToPrint += addPackageTitle;
                break;
            
            case 5:
                stringToPrint += updatePackageTitle;
                break;
            
            case 6:
                stringToPrint += removePackageTitle;
                break; 
        }
        return stringToPrint;
    }

    /////////////////// Adding/Updating Ala Carte Item //////////////////////
    /**
     * Get User's Choice for what an item's type is
     * @param choice is an int representing the user's previous choice.
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getAlaCarteTypeChoice(int choice, ChoiceObserver callback){

        String choice1String = "1. Appetiser\n";
        String choice2String = "2. Main Course\n";
        String choice3String = "3. Drink\n";
        String choice4String = "4. Dessert\n";
        // String choice5String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = getTitle(choice);
        stringToPrint += "Enter menu item type: \n";
        stringToPrint += choice1String + choice2String + choice3String + choice4String;

        // Get the user's choice
        int numberOfChoices = 4;
        boolean isRecurring = false;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    /**
     * Gets the name of the menu item or package
     * @param isPackage  is a bool that represents whether an item is a package or ala carte item.
     * @return a string representing the name of the package or ala carte item
     */
    public String getName(boolean isPackage){
        if(isPackage)
            System.out.println("\nEnter package name: ");
        else
            System.out.println("\nEnter menu item name: ");
        return getStringInput();
    }

    /**
     * Get the description
     * @param isPackage  is a bool that represents whether an item is a package or ala carte item.
     * @return a string representing the description of the package or ala carte item
     */
    public String getDesc(boolean isPackage){
        if(isPackage)
            System.out.println("\nEnter package description: ");
        else
            System.out.println("\nEnter menu item description: ");
        return getStringInput();
    }

    /**
     * Get the price of either a package or ala carte item.
     * @param isPackage  is a bool that represents whether an item is a package or ala carte item.
     * @return a double corresponding to the price of package or ala carte item.
     */
    public double getPrice(boolean isPackage){
        if(isPackage)
            System.out.println("\nEnter package price: ");
        else
            System.out.println("\nEnter menu item price: ");
        return getPositiveDouble();
    }


    /////////////////// Updating/Removing Ala Carte Item //////////////////////

    /**
     * Get the user's choice to update an ala carte item
     * @param choice
     * @param items
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getAlaCarteItemChoice(int choice, ArrayList<AlaCarteEntity> items, ChoiceObserver callback){ 
        
        String stringToPrint = "";

        stringToPrint += getTitle(choice);
        
        if(choice==2){
            stringToPrint += "\nChoose menu item to update: \n";
        }
        if(choice==3){
            stringToPrint += "\nChoose menu item to remove: \n";
        }

        int counter = 1;
        for (AlaCarteEntity item : items) {
            String itemString = Integer.toString(counter) + ". " + item.getName() + " - " + Double.toString(item.getPrice()) + "\n";
            // eg. "1. fries with ketchup - $69.00 \n"
            stringToPrint = stringToPrint + itemString;
            counter++;
        }
        // Get the user's choice
        int numberOfChoices = items.size();
        boolean isRecurring = false;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    /**
     * Choose the field to update for ala carte menu items
     * @param choice is an int that represents the user's previous choice
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getAlaCarteFieldChoice(int choice, ChoiceObserver callback){
        // System.out.println("\nChoose field to update: ");
        String stringToPrint = "";

        stringToPrint += getTitle(choice);
        
        stringToPrint += "\nChoose field to update: \n";
        
        String choice1String = "1. Name\n";
        String choice2String = "2. Description\n";
        String choice3String = "3. Price\n";
        String choice4String = "4. Type\n";
        String choice5String = "0. Back to main menu\n";

        // String to print
        String itemString = choice1String + choice2String + choice3String + choice4String + choice5String;
        stringToPrint = stringToPrint + itemString;
        // Get the user's choice
        int numberOfChoices = 4;
        boolean isRecurring = true;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }


    ////////////////////// Adding ala carte item to package /////////////////////////
    /**
     * Add ala carte item to package.
     * @param choice is an int that represents the user's previous choice
     * @param items is an ArrayList of AlaCarteEntity showing the available items to be added to package
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getItemToPackageChoice(int choice, ArrayList<AlaCarteEntity> items, ChoiceObserver callback){ 
        String stringToPrint = getTitle(choice);;

        stringToPrint += "\nChoose Ala Carte item to add to/remove from package\n";
        stringToPrint += "\n0. Back to Main Menu \n";
        int counter = 1;
        for (AlaCarteEntity item : items) {
            String itemString = Integer.toString(counter) + ". " + item.getName() + " - " + Double.toString(item.getPrice()) + "\n";
            // eg. "1. kukubird with ketchup - $69.00 \n"
            stringToPrint = stringToPrint + itemString;
            counter++;
        }

        // Get the user's choice
        int numberOfChoices = items.size();
        boolean isRecurring = true;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    /**
     * Update package
     * @param choice is the user's previous choice to update package
     * @param callback is the anonymous callback function to run when user inputs a valid integer
     */
    public void getPackageFieldChoice(int choice, ChoiceObserver callback){
        // System.out.println("\nChoose field to update: ");
        String stringToPrint = getTitle(choice);;
        stringToPrint += "\nChoose field to update: \n";

        String choice1String = "1. Name\n";
        String choice2String = "2. Description\n";
        String choice3String = "3. Price\n";
        String choice4String = "4. Add Item\n";
        String choice5String = "5. Remove Item\n";
        String choice6String = "0. Back to main menu\n";

        // String to print
        String itemString = choice1String + choice2String + choice3String + choice4String + choice5String + choice6String;
        stringToPrint += itemString;
        // Get the user's choice
        int numberOfChoices = 5;
        boolean isRecurring = true;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

     /**
      * Updating/Removing whole package entity
      * @param choice is the user's previous decision to either update or remove the package
      * @param packages is an ArrayList of Package Entity stored in menu
      * @param callback is the anonymous callback function to run when user inputs a valid integer
      */
    public void getPackageChoice(int choice,ArrayList<PackageEntity> packages, ChoiceObserver callback){ 
        // System.out.println("\nChoose Package: ");
        String stringToPrint = getTitle(choice);

        if(choice == 5){
            stringToPrint += "\nChoose package to update: \n";
        }
        if(choice == 6){
            stringToPrint += "\nChoose package to remove: \n";
        }

        int counter = 1;
        for (PackageEntity pkg : packages) {
            String pkgString = Integer.toString(counter) + ". " + pkg.getName() + " - " + Double.toString(pkg.getPrice()) + "\n";
            // eg. "1. kukubird with ketchup - $69.00 \n"
            stringToPrint = stringToPrint + pkgString;
            counter++;
        }

        // Get the user's choice
        int numberOfChoices = packages.size();
        boolean isRecurring = false;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
        // Get User Choices is defined in parent class, Boundary.
    }
}
