package Controller;
import java.util.ArrayList;
import java.util.Scanner;
import Boundary.MenuBoundary;
import Entity.AlaCarteEntity;
import Entity.PackageEntity;
import Entity.MenuEntity;
import Entity.AlaCarteEntity.Type;
import Helpers.*;

public class MenuController {
    
    private MenuBoundary view;
    private MenuEntity menu;
    Scanner sc = new Scanner(System.in);

    public MenuController() {
        this.view = new MenuBoundary();
        this.menu = new MenuEntity();
        this.start();
    }

    private void start() {
        /* Selection Prompt */
        // int choice;
        // choice = sc.nextInt();
        view.printMenu(menu.getAlaCarteItems(), menu.getPackages());

        view.getUserMenuChoice(choice -> {
            view.printTitle(choice);
            switch (choice) {
                case 1: /* add new alacarte item */
                    addAlaCarteFlow();
                    break;

                case 2: /* update alacarte item */
                    updateAlaCarteFlow();
                    break;

                case 3: /* remove alacarte item */
                    removeAlaCarteFlow();
                    break;
                
                case 4: /* add new package */
                    addPackageFlow();
                    break;
                
                case 5: /* update package */
                    updatePackageFlow();
                    break;
                
                case 6: /* remove package */
                    removePackageFlow();
                    break;

                case 0: /* Back to main menu */
                    break;
                }
        });
    }

    // create alacarte object according to type
    public void addAlaCarteFlow(){
        boolean isPackage = false;
        String name = view.getName(isPackage);
        String desc = view.getDesc(isPackage);
        double price = view.getPrice(isPackage);

        view.getAlaCarteTypeChoice(type -> {
            switch (type) {
                
                // appetiser
                case 1: 
                    createAlaCarteObject(name, desc, price, Type.APPETISER);
                    break;
                
                // main course
                case 2:
                    createAlaCarteObject(name, desc, price, Type.MAINCOURSE);
                    break;

                // drink
                case 3:
                    createAlaCarteObject(name, desc, price, Type.DRINK);
                    break;

                // dessert
                case 4:
                    createAlaCarteObject(name, desc, price, Type.DESSERT);
                    break;
            }
            });
    }

    public void createAlaCarteObject(String name, String desc, double price, Type type){
        // create new alacarte entity
        AlaCarteEntity newAlaCarteItem = new AlaCarteEntity(name, desc, price, type);

        // add to menu entity
        menu.addAlaCarteEntity(newAlaCarteItem);
    }

    public void updateAlaCarteFlow(){
        view.getAlaCarteItemChoice(menu.getAlaCarteItems(), itemChoice -> {
            
            // get ala carte object as toUpdate
            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
            AlaCarteEntity toUpdate = alaCarteItems.get(itemChoice-1);

            view.getAlaCarteFieldChoice(fieldChoice -> {
                boolean isPackage = false;
                switch(fieldChoice){
                    
                    case 1: // name
                        String newName = view.getName(isPackage);
                        menu.updateAlaCarteEntityName(toUpdate, newName);
                        break;
                    
                    case 2: // desc
                        String newDesc = view.getDesc(isPackage);
                        menu.updateAlaCarteEntityDesc(toUpdate, newDesc);
                        break;

                    case 3: // price
                        double newPrice = view.getPrice(isPackage);
                        menu.updateAlaCarteEntityPrice(toUpdate, newPrice);
                        break;

                    case 4: // type
                        view.getAlaCarteTypeChoice(type -> {
                            switch (type) {
                                case 1: // appetiser
                                    menu.updateAlaCarteEntityType(toUpdate, Type.APPETISER);
                                    break;
                                
                                case 2: // main course
                                    menu.updateAlaCarteEntityType(toUpdate, Type.MAINCOURSE);
                                    break;
                
                                case 3: // drink
                                    menu.updateAlaCarteEntityType(toUpdate, Type.DRINK);
                                    break;
                
                                case 4: // dessert
                                    menu.updateAlaCarteEntityType(toUpdate, Type.DESSERT);
                                    break;
                            }
                        });
                        break;
                }
            });

        });
    }

    
    public void removeAlaCarteFlow(){
        view.getAlaCarteItemChoice(menu.getAlaCarteItems(), itemChoice -> {
           
            // get ala carte object as toRemove
            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
            AlaCarteEntity toRemove = alaCarteItems.get(itemChoice-1);
            menu.removeAlaCarteEntity(toRemove);
        });
    }

    public void addPackageFlow(){
        boolean isPackage = true;
        String name = view.getName(isPackage);
        String desc = view.getDesc(isPackage);
        double price = view.getPrice(isPackage);

        PackageEntity newPackage = new PackageEntity(name, desc, price);
        menu.addPackageEntity(newPackage);

        view.getItemToPackageChoice(menu.getAlaCarteItems(), itemChoice -> {
            // get ala carte object as toRemove
            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
            AlaCarteEntity toAdd = alaCarteItems.get(itemChoice-1);
            menu.addPackageEntityItem(newPackage, toAdd);
        });
    }

    public void updatePackageFlow(){
        view.getPackageChoice(menu.getPackages(), item -> {
            // use item as an int
            ArrayList<PackageEntity> packages = menu.getPackages();
            PackageEntity packageToUpdate = packages.get(item - 1);
            
            view.getPackageFieldChoice(fieldChoice -> {
                boolean isPackage = false;
                switch(fieldChoice) {
                    case 1: // name
                        String newName = view.getName(isPackage);
                        menu.updatePackageEntityName(packageToUpdate, newName);
                        break;
                    
                    case 2: // desc
                        String newDesc = view.getDesc(isPackage);
                        menu.updatePackageEntityDesc(packageToUpdate, newDesc);
                        break;

                    case 3: // price
                        double newPrice = view.getPrice(isPackage);
                        menu.updatePackageEntityPrice(packageToUpdate, newPrice);
                        break;
                    case 4: // add item
                        view.getItemToPackageChoice(menu.getAlaCarteItems(), itemChoice -> {
                            // get ala carte object as toRemove
                            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
                            AlaCarteEntity itemToAdd = alaCarteItems.get(itemChoice-1);
                            menu.addPackageEntityItem(packageToUpdate, itemToAdd);
                        });
                        break;
                    
                    case 5: // remove item
                        view.getItemToPackageChoice(menu.getAlaCarteItems(), itemChoice -> {
                            // get ala carte object as toRemove
                            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
                            AlaCarteEntity itemToRemove = alaCarteItems.get(itemChoice-1);
                            menu.removePackageEntityItem(packageToUpdate, itemToRemove);
                        });
                        break;
                }
            });
        });
    }

    public void removePackageFlow(){
        view.getPackageChoice(menu.getPackages(), item -> {
            // use item as an int
            ArrayList<PackageEntity> packages = menu.getPackages();
            PackageEntity toRemove = packages.get(item-1);
            menu.removePackageEntity(toRemove);
        });
    }

}
