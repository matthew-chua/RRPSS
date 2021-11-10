package Entity;
import Entity.AlaCarteEntity.Type;
import Helpers.PersistenceManager;

import java.util.ArrayList;

// this is the parent object for all alacarte and package entities
// contains array list of all alacarte items
// and array list of all packages
public class MenuEntity extends PersistenceManager{

    // attributes
    private ArrayList<AlaCarteEntity> alaCarteItems;
    private ArrayList<PackageEntity> packages;

    private static String alaCarteFile = "AlaCarte.txt";
    private static String packageFile = "MenuPackages.txt";

    // Constructor
    public MenuEntity(){
        this.alaCarteItems = new ArrayList<AlaCarteEntity>();
        this.packages = new ArrayList<PackageEntity>();

        // resetData();

        loadData(alaCarteFile, this.alaCarteItems);
        loadData(packageFile, this.packages);
    }

    private void resetData(){
        saveData(alaCarteFile, alaCarteItems);
        saveData(packageFile, packages);
    }

    // temp function
    private void printList(){
        this.packages.forEach(pkg -> {System.out.println(pkg);});
    }


    public ArrayList<AlaCarteEntity> getAlaCarteItems(){
        return this.alaCarteItems;
    }

    public ArrayList<PackageEntity> getPackages(){
        return this.packages;
    }

    public void populateData(){
        // create alacarte entities
        // create package
    }

    ////////////////////////////// CRUD ALACARTE ///////////////////////////////////////
    // Add Ala Carte Entity
    public void addAlaCarteEntity(AlaCarteEntity alacarteEntity){
        this.alaCarteItems.add(alacarteEntity);
        saveData(alaCarteFile, alaCarteItems);
    }

    // Update Ala Carte Entity
    public void updateAlaCarteEntityName(AlaCarteEntity alacarteEntity, String name){
        alacarteEntity.setName(name);
        saveData(alaCarteFile, alaCarteItems);
    }
    public void updateAlaCarteEntityDesc(AlaCarteEntity alacarteEntity, String desc){
        alacarteEntity.setDesc(desc);
        saveData(alaCarteFile, alaCarteItems);
    }
    public void updateAlaCarteEntityPrice(AlaCarteEntity alacarteEntity, double price){
        alacarteEntity.setPrice(price);
        saveData(alaCarteFile, alaCarteItems);
    }
    public void updateAlaCarteEntityType(AlaCarteEntity alacarteEntity, Type type){
        alacarteEntity.setType(type);
        saveData(alaCarteFile, alaCarteItems);
    }

    // Remove Ala Carte Entity
    public void removeAlaCarteEntity(AlaCarteEntity alacarteEntity){
        this.alaCarteItems.remove(alacarteEntity);
        // also remove all package entities containing removed alacarte entity
        ArrayList<PackageEntity> packagesToRemove = new ArrayList<PackageEntity>();
        for(PackageEntity pkg : this.packages){
            if(pkg.getItems().contains(alacarteEntity)){
                packagesToRemove.add(pkg);
            }
        }
        for(PackageEntity i : packagesToRemove){
            removePackageEntity(i);
        }
        saveData(alaCarteFile, alaCarteItems);
    }

    ////////////////////////////// CRUD PACKAGE ///////////////////////////////////////
    // Add Package
    public void addPackageEntity(PackageEntity packageEntity){
        this.packages.add(packageEntity);
        saveData(packageFile, packages);
    }

    // Update Package
    public void updatePackageEntityName(PackageEntity packageEntity, String name){
        packageEntity.setName(name);
        saveData(packageFile, packages);
    }
    public void updatePackageEntityDesc(PackageEntity packageEntity, String desc){
        packageEntity.setDesc(desc);
        saveData(packageFile, packages);
    }
    public void updatePackageEntityPrice(PackageEntity packageEntity, double price){
        packageEntity.setPrice(price);
        saveData(packageFile, packages);
    }
    public void addPackageEntityItem(PackageEntity packageEntity, AlaCarteEntity alaCarteToAdd){
        packageEntity.addItem(alaCarteToAdd);
        saveData(packageFile, packages);
    }
    public void removePackageEntityItem(PackageEntity packageEntity,  AlaCarteEntity alaCarteToRemove){
        packageEntity.removeItem(alaCarteToRemove);
        saveData(packageFile, packages);
    }
    
    // Remove whole package
    public void removePackageEntity(PackageEntity packageEntity){
        this.packages.remove(packageEntity);
        saveData(packageFile, packages);
    }

    public String toString(){
        // print menu

        ArrayList<AlaCarteEntity> items = this.alaCarteItems;
        ArrayList<PackageEntity> packages = this.packages;
        String separators = "=============";
        if (items.size() == 0 && packages.size() == 0) {
            // System.out.printf("No items in menu. Add a menu item to start");
            return "No items in menu. Add a menu item to start \n";
        }

        String toPrint = "Menu: \n";
        toPrint += separators+separators+separators;
        toPrint += "\n" + "Chef KuKu's Italian Cock with Tails\n";
        toPrint += separators+separators+separators + "\n";
        toPrint += " - Appetisers:\n";

        for(AlaCarteEntity item : items){
            if (item.getType() == Type.APPETISER){
                // System.out.printf("\n    - %s\t$ %.2f", item.getName(), item.getPrice());
                // toPrint += " - " + item.getName() + "\t" + String.format("%.2f", item.getPrice()) + "\n";
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
            toPrint += "   " + " - " + pkg.getName() + "\t\t\t" + String.format("%.2f", pkg.getPrice()) + "\n";
            
            ArrayList<AlaCarteEntity> packageItems = pkg.getItems();
            for(AlaCarteEntity pkgItem : packageItems){
                // System.out.printf("\n    - %s          $%.2f", pkgItem.getName(), pkgItem.getPrice());
                toPrint += formatItem(pkgItem);
            }
        }
        toPrint += "\n";
        return toPrint;
    }

    private String formatItem(AlaCarteEntity item){
        return "    " + " - " + item.getName() + "\t\t\t" + String.format("%.2f", item.getPrice()) + "\n";
    }

}
