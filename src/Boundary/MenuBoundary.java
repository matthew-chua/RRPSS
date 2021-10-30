package Boundary;
import Helpers.*;
import Entity.AlaCarteEntity;
import Entity.AlaCarteEntity.Type;
import Entity.PackageEntity;
import java.util.ArrayList;

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

    public void printMenu(ArrayList<AlaCarteEntity> items, ArrayList<PackageEntity> packages){
        // print menu

        if (items.size() == 0 && packages.size() == 0) {
            System.out.printf("No items in menu. Add a menu item to start");
            return;
        }

        System.out.printf(manageMenuTitle);
        System.out.printf("Menu: " + "\n");
        System.out.println("\n==============================");
        System.out.println("\nChef KuKu's Italian Cock with Tails");
        System.out.println("\n==============================");
        
        // print appetisers
        System.out.println("\n- Appetisers: ");
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.APPETISER){
                System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
            }
        }

        // print main courses
        System.out.println("\n- Main Courses: ");
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.MAINCOURSE){
                System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
            }
        }

        // print drinks
        System.out.println("\n- Drinks: ");
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.DRINK){
                System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
            }
        }

        // print desserts
        System.out.println("\n- Desserts: ");
        for(AlaCarteEntity item : items){
            if (item.getType() == Type.DESSERT){
                System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
            }
        }

        // print packages
        System.out.println("\nPackages: ");
        for(PackageEntity pkg : packages){
            System.out.printf("\n- %s          $%d", pkg.getName(), pkg.getPrice());
            
            ArrayList<AlaCarteEntity> packageItems = pkg.getItems();
            for(AlaCarteEntity pkgItem : packageItems){
                System.out.printf("\n    - %s          $%d", pkgItem.getName(), pkgItem.getPrice());
            }
        }
    }


    // Get User's Choice for what menu component they would like to manage
    public void getUserMenuChoice(ChoiceObserver callback){
        /* =========== User's Menu choices =========== */
        String choice1String = "1. Add a new menu item\n";
        String choice2String = "2. Update a menu item\n";
        String choice3String = "3. Remove a menu item\n";
        String choice4String = "4. Add a new package\n";
        String choice5String = "5. Update a package\n";
        String choice6String = "6. Remove a package\n";
        String choice7String = "0. Back to main menu\n";

        // -> PRINT MENU HERE?

        // String to print
        String stringToPrint = choice1String + choice2String +
        choice3String + choice4String + choice5String + choice6String + choice7String;

        // Get the user's choice
        int numberOfChoices = 6;
        boolean isRecurring = true;
        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    //////////////// Print banners for each manage menu choice ///////////////////////
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

    /////////////////// Adding/Updating Ala Carte Item //////////////////////
    // Get User's Choice for what an item's type is
    public void getAlaCarteTypeChoice(ChoiceObserver callback){
        System.out.println("\nEnter menu item type: ");
        String choice1String = "1. Appetiser\n";
        String choice2String = "2. Main Course\n";
        String choice3String = "3. Drink\n";
        String choice4String = "4. Dessert\n";
        String choice5String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = choice1String + choice2String + choice3String + choice4String + choice5String;

        // Get the user's choice
        int numberOfChoices = 4;
        boolean isRecurring = true;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }

    public String getName(boolean isPackage){
        if(isPackage)
            System.out.println("\nEnter package name: ");
        else
            System.out.println("\nEnter menu item name: ");
        return getStringInput();
    }

    public String getDesc(boolean isPackage){
        if(isPackage)
            System.out.println("\nEnter package description: ");
        else
            System.out.println("\nEnter menu item description: ");
        return getStringInput();
    }

    public double getPrice(boolean isPackage){
        if(isPackage)
            System.out.println("\nEnter package price: ");
        else
            System.out.println("\nEnter menu item price: ");
        return getPositiveDouble();
    }


    /////////////////// Updating/Removing Ala Carte Item //////////////////////
    public void getAlaCarteItemChoice(ArrayList<AlaCarteEntity> items, ChoiceObserver callback){ 
        System.out.println("\nChoose menu item: ");
        
        String stringToPrint = "";
        int counter = 1;
        for (AlaCarteEntity item : items) {
            String itemString = Integer.toString(counter) + ". " + item.getName() + " - " + Double.toString(item.getPrice()) + "\n";
            // eg. "1. kukubird with ketchup - $69.00 \n"
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

    public void getAlaCarteFieldChoice(ChoiceObserver callback){
        System.out.println("\nEnter field to update: ");
        String choice1String = "1. Name\n";
        String choice2String = "2. Description\n";
        String choice3String = "3. Price\n";
        String choice4String = "4. Type\n";
        String choice5String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = choice1String + choice2String + choice3String + choice4String + choice5String;

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
    // THIS IS THE SAME AS getAlaCarteItemChoice EXCEPT IT IS RECURRING -> keep querying for items to add to package, user exits at will
    public void getItemToPackageChoice(ArrayList<AlaCarteEntity> items, ChoiceObserver callback){ 
        System.out.println("\nChoose menu item to add to Package: ");
        
        String stringToPrint = "0. Back to Main Menu \n";
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

    ////////////////////// CHOOSE to change name/desc/price/Add/remove existing items to/from package entity/////////////////////////
    public void getPackageFieldChoice(ChoiceObserver callback){
        System.out.println("\nEnter field to update: ");
        String choice1String = "1. Name\n";
        String choice2String = "2. Description\n";
        String choice3String = "3. Price\n";
        String choice4String = "4. Add Item\n";
        String choice5String = "5. Remove Item\n";
        String choice6String = "0. Back to main menu\n";

        // String to print
        String stringToPrint = choice1String + choice2String + choice3String + choice4String + choice5String + choice6String;

        // Get the user's choice
        int numberOfChoices = 5;
        boolean isRecurring = true;

        getUserChoices(numberOfChoices, 
        callback, 
        isRecurring, 
        stringToPrint);
        // Get User Choices is defined in parent class, Boundary.
    }


    ////////////////////// Updating/Removing whole package entity/////////////////////////
    public void getPackageChoice(ArrayList<PackageEntity> packages, ChoiceObserver callback){ 
        System.out.println("\nChoose Package: ");
        
        String stringToPrint = "";
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
