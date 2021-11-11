package Entity;

import Entity.AlaCarteEntity.Type;
import Helpers.PersistenceManager;

import java.util.ArrayList;

/**
 * this is the parent object for all alacarte and package entities contains
 * array list of all alacarte items and array list of all packages
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class MenuEntity extends PersistenceManager {

    /**
     * The AlaCarte items available on the menu
     */
    private ArrayList<AlaCarteEntity> alaCarteItems;
    /**
     * The package items available on the menu
     */
    private ArrayList<PackageEntity> packages;

    private static String alaCarteFile = "AlaCarte.txt";
    private static String packageFile = "MenuPackages.txt";

    /**
     * Creates a new menu with the existing AlaCarte and Package items Loads data
     * from external file
     */
    public MenuEntity() {
        this.alaCarteItems = new ArrayList<AlaCarteEntity>();
        this.packages = new ArrayList<PackageEntity>();

        // resetData();

        loadData(alaCarteFile, this.alaCarteItems);
        loadData(packageFile, this.packages);
    }

    /**
     * Gets the ArrayList of AlaCarte items of this Menu
     * 
     * @return this Menu's AlaCarte items
     */
    public ArrayList<AlaCarteEntity> getAlaCarteItems() {
        return this.alaCarteItems;
    }

    /**
     * Gets the ArrayList of Package items of this Menu
     * 
     * @return this Menu's Package items
     */
    public ArrayList<PackageEntity> getPackages() {
        return this.packages;
    }

    ////////////////////////////// CRUD ALACARTE
    ////////////////////////////// ///////////////////////////////////////
    /**
     * Adds a new AlaCarte item to this Menu
     * 
     * @param alacarteEntity This Menu's new AlaCarte item
     */
    public void addAlaCarteEntity(AlaCarteEntity alacarteEntity) {
        this.alaCarteItems.add(alacarteEntity);
        saveData(alaCarteFile, alaCarteItems);
    }

    /**
     * Changes the name of an AlaCarte item on this Menu
     * 
     * @param alacarteEntity This Menu's existing AlaCarte item
     * @param name           This Menu's existing AlaCarte item's new name
     */
    public void updateAlaCarteEntityName(AlaCarteEntity alacarteEntity, String name) {
        for (PackageEntity pkg : this.packages) {
            for (AlaCarteEntity pkgItem : pkg.getItems()) {
                if (pkgItem.getName() == alacarteEntity.getName()) {
                    pkgItem.setName(name);
                }
            }
        }
        alacarteEntity.setName(name);
        saveData(packageFile, packages);
        saveData(alaCarteFile, alaCarteItems);
    }

    /**
     * Changes the description of an AlaCarte item on this Menu
     * 
     * @param alacarteEntity This Menu's existing AlaCarte item
     * @param desc           This Menu's existing AlaCarte item's new description
     */
    public void updateAlaCarteEntityDesc(AlaCarteEntity alacarteEntity, String desc) {
        for (PackageEntity pkg : this.packages) {
            for (AlaCarteEntity pkgItem : pkg.getItems()) {
                if (pkgItem.getName() == alacarteEntity.getName()) {
                    pkgItem.setDesc(desc);
                }
            }
        }
        alacarteEntity.setDesc(desc);
        saveData(packageFile, packages);
        saveData(alaCarteFile, alaCarteItems);
    }

    /**
     * Changes the price of an AlaCarte item on this Menu
     * 
     * @param alacarteEntity This Menu's existing AlaCarte item
     * @param price          This Menu's existing AlaCarte item's new price
     */
    public void updateAlaCarteEntityPrice(AlaCarteEntity alacarteEntity, double price) {
        for (PackageEntity pkg : this.packages) {
            for (AlaCarteEntity pkgItem : pkg.getItems()) {
                if (pkgItem.getName() == alacarteEntity.getName()) {
                    pkgItem.setPrice(price);
                }
            }
        }
        alacarteEntity.setPrice(price);
        saveData(packageFile, packages);
        saveData(alaCarteFile, alaCarteItems);
    }

    /**
     * Changes the type of an AlaCarte item on this Menu
     * 
     * @param alacarteEntity This Menu's existing AlaCarte item
     * @param type           This Menu's existing AlaCarte item's new type
     */
    public void updateAlaCarteEntityType(AlaCarteEntity alacarteEntity, Type type) {
        for (PackageEntity pkg : this.packages) {
            for (AlaCarteEntity pkgItem : pkg.getItems()) {
                if (pkgItem.getName() == alacarteEntity.getName()) {
                    pkgItem.setType(type);
                }
            }
        }
        alacarteEntity.setType(type);
        saveData(packageFile, packages);
        saveData(alaCarteFile, alaCarteItems);
    }

    /**
     * Removes an existing AlaCarte item from this Menu
     * 
     * @param alacarteEntity This Menu's AlaCarte item to be removed
     */
    public void removeAlaCarteEntity(AlaCarteEntity alacarteEntity) {
        this.alaCarteItems.remove(alacarteEntity);
        // also remove all package entities containing removed alacarte entity
        ArrayList<PackageEntity> packagesToRemove = new ArrayList<PackageEntity>();
        for (PackageEntity pkg : this.packages) {
            if (pkg.getItems().contains(alacarteEntity)) {
                packagesToRemove.add(pkg);
            }
        }
        for (PackageEntity i : packagesToRemove) {
            removePackageEntity(i);
        }
        saveData(alaCarteFile, alaCarteItems);
    }

    ////////////////////////////// CRUD PACKAGE
    ////////////////////////////// ///////////////////////////////////////
    /**
     * Adds a new package to this Menu
     * 
     * @param packageEntity This Menu's new package
     */
    public void addPackageEntity(PackageEntity packageEntity) {
        this.packages.add(packageEntity);
        saveData(packageFile, packages);
    }

    /**
     * Changes the name of a package on this Menu
     * 
     * @param packageEntity This Menu's existing package
     * @param name          This Menu's existing package's new name
     */
    public void updatePackageEntityName(PackageEntity packageEntity, String name) {
        packageEntity.setName(name);
        saveData(packageFile, packages);
    }

    /**
     * Changes the description of a package on this Menu
     * 
     * @param packageEntity This Menu's existing package
     * @param desc          This Menu's existing package's new description
     */
    public void updatePackageEntityDesc(PackageEntity packageEntity, String desc) {
        packageEntity.setDesc(desc);
        saveData(packageFile, packages);
    }

    /**
     * Changes the price of a package on this Menu
     * 
     * @param packageEntity This Menu's existing package
     * @param price         This Menu's existing package's new price
     */
    public void updatePackageEntityPrice(PackageEntity packageEntity, double price) {
        packageEntity.setPrice(price);
        saveData(packageFile, packages);
    }

    /**
     * Adds an AlaCarte item to an existing package in this Menu
     * 
     * @param packageEntity This Menu's existing package
     * @param alaCarteToAdd This Menu's existing package's new AlaCarte item
     */
    public void addPackageEntityItem(PackageEntity packageEntity, AlaCarteEntity alaCarteToAdd) {
        packageEntity.addItem(alaCarteToAdd);
        saveData(packageFile, packages);
    }

    /**
     * Remove an AlaCarte item from an existing package in this Menu
     * 
     * @param packageEntity This Menu's existing package
     * @param alaCarteToAdd This Menu's existing package's existing AlaCarte item to
     *                      be remove
     */
    public void removePackageEntityItem(PackageEntity packageEntity, AlaCarteEntity alaCarteToRemove) {
        packageEntity.removeItem(alaCarteToRemove);
        saveData(packageFile, packages);
    }

    /**
     * Remove an existing package in this Menu
     * 
     * @param packageEntity This Menu's package to be removed
     */
    public void removePackageEntity(PackageEntity packageEntity) {
        this.packages.remove(packageEntity);
        saveData(packageFile, packages);
    }

    /**
     * Collates this Menu into a single string to display
     * 
     * @return string to display when viewing Menu
     */
    public String toString() {
        ArrayList<AlaCarteEntity> items = this.alaCarteItems;
        ArrayList<PackageEntity> packages = this.packages;
        String separators = "=============";
        if (items.size() == 0 && packages.size() == 0) {
            return "No items in menu. Add a menu item to start \n";
        }

        String toPrint = "Menu: \n";
        toPrint += separators + separators + separators;
        toPrint += "\n" + "Chef KuKu's Italian Cock with Tails\n";
        toPrint += separators + separators + separators + "\n";
        toPrint += " - Appetisers:\n";

        for (AlaCarteEntity item : items) {
            if (item.getType() == Type.APPETISER) {
                toPrint += formatItem(item);
            }
        }

        toPrint += " - Main Courses:\n";
        for (AlaCarteEntity item : items) {
            if (item.getType() == Type.MAINCOURSE) {
                toPrint += formatItem(item);
            }
        }

        toPrint += " - Drinks:\n";
        for (AlaCarteEntity item : items) {
            if (item.getType() == Type.DRINK) {
                toPrint += formatItem(item);
            }
        }

        toPrint += " - Desserts:\n";
        for (AlaCarteEntity item : items) {
            if (item.getType() == Type.DESSERT) {
                toPrint += formatItem(item);
            }
        }

        toPrint += " - Packages:\n";
        for (PackageEntity pkg : packages) {
            toPrint += "   " + " - " + pkg.getName() + "\t\t\t" + String.format("%.2f", pkg.getPrice()) + "\n";

            ArrayList<AlaCarteEntity> packageItems = pkg.getItems();
            for (AlaCarteEntity pkgItem : packageItems) {
                toPrint += formatItem(pkgItem);
            }
        }
        toPrint += "\n";
        return toPrint;
    }

    /**
     * Formats AlaCarte item for display
     * 
     * @param item This Menu's AlaCarte item
     * @return this AlaCarte items details
     */
    private String formatItem(AlaCarteEntity item) {
        return "    " + " - " + item.getName() + "\t\t\t" + String.format("%.2f", item.getPrice()) + "\n";
    }

}
