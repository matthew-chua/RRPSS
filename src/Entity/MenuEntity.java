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

        resetData();

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
        for(PackageEntity pkg : this.packages){
            for(AlaCarteEntity item : pkg.getItems()){
                if(item == alacarteEntity)
                    removePackageEntity(pkg);
            }
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




}
