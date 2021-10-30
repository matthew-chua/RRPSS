package Entity;
import Entity.AlaCarteEntity.Type;
import java.util.ArrayList;

// this is the parent object for all alacarte and package entities
// contains array list of all alacarte items
// and array list of all packages
public class MenuEntity {

    // attributes
    private ArrayList<AlaCarteEntity> alaCarteItems;
    private ArrayList<PackageEntity> packages;

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
    }

    // Update Ala Carte Entity
    public void updateAlaCarteEntityName(AlaCarteEntity alacarteEntity, String name){
        alacarteEntity.setName(name);
    }
    public void updateAlaCarteEntityDesc(AlaCarteEntity alacarteEntity, String desc){
        alacarteEntity.setDesc(desc);
    }
    public void updateAlaCarteEntityPrice(AlaCarteEntity alacarteEntity, double price){
        alacarteEntity.setPrice(price);
    }
    public void updateAlaCarteEntityType(AlaCarteEntity alacarteEntity, Type type){
        alacarteEntity.setType(type);
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
    }

    ////////////////////////////// CRUD PACKAGE ///////////////////////////////////////
    // Add Package
    public void addPackageEntity(PackageEntity packageEntity){
        this.packages.add(packageEntity);
    }

    // Update Package
    public void updatePackageEntityName(PackageEntity packageEntity, String name){
        packageEntity.setName(name);
    }
    public void updatePackageEntityDesc(PackageEntity packageEntity, String desc){
        packageEntity.setDesc(desc);
    }
    public void updatePackageEntityPrice(PackageEntity packageEntity, double price){
        packageEntity.setPrice(price);
    }
    public void addPackageEntityItem(PackageEntity packageEntity, AlaCarteEntity alaCarteToAdd){
        packageEntity.addItem(alaCarteToAdd);
    }
    public void removePackageEntityItem(PackageEntity packageEntity,  AlaCarteEntity alaCarteToRemove){
        packageEntity.removeItem(alaCarteToRemove);
    }
    
    // Remove whole package
    public void removePackageEntity(PackageEntity packageEntity){
        this.packages.remove(packageEntity);
    }

}
