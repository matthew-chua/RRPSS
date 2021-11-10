package Controller;
import java.util.ArrayList;
import java.util.Scanner;
import Boundary.MenuBoundary;
import Entity.AlaCarteEntity;
import Entity.PackageEntity;
import Entity.RestaurantEntity;
import Entity.StaffEntity;
import Entity.Table;
import Entity.MenuEntity;
import Entity.OrderEntity;
import Entity.AlaCarteEntity.Type;
import Helpers.*;

public class MenuController {
    
    private MenuBoundary view;
    private MenuEntity menu;
    Scanner sc = new Scanner(System.in);
    private RestaurantEntity res;
    // private boolean shouldEnd = false;

    public MenuController() {
        this.view = new MenuBoundary();

        this.res = RestaurantEntity.getInstance();
        this.menu = res.getMenu();


        // this.res.addDataToList(RestaurantDataType.STAFF , new StaffEntity());

        this.start();
    }

    // public void setShouldEnd(){
    //     shouldEnd = true;
    // }

    private void start() {
        /* Selection Prompt */
        // int choice;
        // choice = sc.nextInt();
        // view.printMenu(menu.getAlaCarteItems(), menu.getPackages());


        

        view.getUserMenuChoice(menu.getAlaCarteItems(), menu.getPackages(), choice -> {
            // view.printTitle(choice);
            switch (choice) {
                case 1: /* add new alacarte item */
                    addAlaCarteFlow();
                    start();
                    break;

                case 2: /* update alacarte item */
                    updateAlaCarteFlow();
                    start();
                    break;

                case 3: /* remove alacarte item */
                    removeAlaCarteFlow();
                    start();
                    break;
                
                case 4: /* add new package */
                    addPackageFlow();
                    start();
                    break;
                
                case 5: /* update package */
                    updatePackageFlow();
                    start();
                    break;
                
                case 6: /* remove package */
                    removePackageFlow();
                    start();
                    break;

                case 0: /* Back to main menu */
                    // System.out.println("help");
                    // shouldEnd = true;
                    // setShouldEnd();
                    break;
                }
        });

        // start();
    }

    // create alacarte object according to type
    public void addAlaCarteFlow(){
        boolean isPackage = false;
        view.resetUI();
        view.printTitle(1);
        String name = view.getName(isPackage);
        view.resetUI();

        view.printTitle(1);
        String desc = view.getDesc(isPackage);
        view.resetUI();

        view.printTitle(1);
        double price = view.getPrice(isPackage);
        view.resetUI();


        view.getAlaCarteTypeChoice(1, type -> {
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
        view.displayResults("\n" + "Ala Carte item added");
    }

    public void createAlaCarteObject(String name, String desc, double price, Type type){
        // create new alacarte entity
        AlaCarteEntity newAlaCarteItem = new AlaCarteEntity(name, desc, price, type);

        // add to menu entity
        menu.addAlaCarteEntity(newAlaCarteItem);
    }

    public void updateAlaCarteFlow(){
        view.resetUI();
        view.getAlaCarteItemChoice(2, menu.getAlaCarteItems(), itemChoice -> {
            
            // get ala carte object as toUpdate
            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
            AlaCarteEntity toUpdate = alaCarteItems.get(itemChoice-1);
            view.resetUI();
            view.getAlaCarteFieldChoice(2,fieldChoice -> {
                boolean isPackage = false;
                switch(fieldChoice){
                    
                    case 1: // name
                        view.resetUI();
                        view.printTitle(2);
                        String newName = view.getName(isPackage);
                        menu.updateAlaCarteEntityName(toUpdate, newName);
                        view.displayResults("\n" + "Ala Carte item name updated");
                        break;
                    
                    case 2: // desc
                        view.resetUI();
                        view.printTitle(2);
                        String newDesc = view.getDesc(isPackage);
                        menu.updateAlaCarteEntityDesc(toUpdate, newDesc);
                        view.displayResults("\n" + "Ala Carte item description updated");
                        break;

                    case 3: // price
                        view.resetUI();
                        view.printTitle(2);
                        double newPrice = view.getPrice(isPackage);
                        menu.updateAlaCarteEntityPrice(toUpdate, newPrice);
                        view.displayResults("\n" + "Ala Carte item price updated");
                        break;

                    case 4: // type
                        view.resetUI();
                        view.printTitle(2);
                        view.getAlaCarteTypeChoice(2, type -> {
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
                            view.displayResults("\n" + "Ala Carte item type updated");

                        });
                        break;
                }
            });

        });
    }

    
    public void removeAlaCarteFlow(){
        view.getAlaCarteItemChoice(3, menu.getAlaCarteItems(), itemChoice -> {
           
            // get ala carte object as toRemove
            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
            AlaCarteEntity toRemove = alaCarteItems.get(itemChoice-1);
            menu.removeAlaCarteEntity(toRemove);
            view.displayResults("\n" + "Ala Carte item and packages containing this item are removed");
        });
    }

    public void addPackageFlow(){
        boolean isPackage = true;
        view.resetUI();
        view.printTitle(4);
        String name = view.getName(isPackage);
        view.resetUI();
        view.printTitle(4);
        String desc = view.getDesc(isPackage);
        view.resetUI();
        view.printTitle(4);
        double price = view.getPrice(isPackage);

        PackageEntity newPackage = new PackageEntity(name, desc, price);
        menu.addPackageEntity(newPackage);

        view.getItemToPackageChoice(4, menu.getAlaCarteItems(), itemChoice -> {
            // get ala carte object as toRemove
            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
            AlaCarteEntity toAdd = alaCarteItems.get(itemChoice-1);
            if (newPackage.getItems().contains(toAdd)){
                view.displayResults("\n" + toAdd.getName() + " is already in package.");
            }
            else{
                menu.addPackageEntityItem(newPackage, toAdd);
                view.displayResults("\n" + toAdd.getName() + " added.");
            }
            
        });
    }

    public void updatePackageFlow(){
        view.getPackageChoice(5, menu.getPackages(), item -> {
            // use item as an int
            ArrayList<PackageEntity> packages = menu.getPackages();
            PackageEntity packageToUpdate = packages.get(item - 1);
            
            view.getPackageFieldChoice(5, fieldChoice -> {
                boolean isPackage = true;
                switch(fieldChoice) {
                    case 1: // name
                        view.resetUI();
                        view.printTitle(5);
                        String newName = view.getName(isPackage);
                        menu.updatePackageEntityName(packageToUpdate, newName);
                        view.displayResults("\n" + "Package name updated");
                        break;
                    
                    case 2: // desc
                        view.resetUI();
                        view.printTitle(5);
                        String newDesc = view.getDesc(isPackage);
                        menu.updatePackageEntityDesc(packageToUpdate, newDesc);
                        view.displayResults("\n" + "Package description updated");
                        break;

                    case 3: // price
                        view.resetUI();
                        view.printTitle(5);
                        double newPrice = view.getPrice(isPackage);
                        menu.updatePackageEntityPrice(packageToUpdate, newPrice);
                        view.displayResults("\n" + "Package price updated");
                        break;

                    case 4: // add item
                        view.getItemToPackageChoice(5, menu.getAlaCarteItems(), itemChoice -> {
                            // get ala carte object as toRemove
                            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
                            AlaCarteEntity toAdd = alaCarteItems.get(itemChoice-1);
                            if (packageToUpdate.getItems().contains(toAdd)){
                                view.displayResults("\n" + toAdd.getName() + " is already in package");
                            }
                            else{
                                menu.addPackageEntityItem(packageToUpdate, toAdd);
                                view.displayResults("\n" +  toAdd.getName() + " added");
                            }
                            
                        });
                        break;
                    
                    case 5: // remove item
                        view.getItemToPackageChoice(5, menu.getAlaCarteItems(), itemChoice -> {
                            // get ala carte object as toRemove
                            ArrayList<AlaCarteEntity> alaCarteItems = menu.getAlaCarteItems();
                            AlaCarteEntity toRemove = alaCarteItems.get(itemChoice-1);
                            if(!packageToUpdate.getItems().contains(toRemove)){
                                view.displayResults("\n" + toRemove.getName() + " has already been ermoved from package.");
                            }
                            else{
                                menu.removePackageEntityItem(packageToUpdate, toRemove);
                                view.displayResults("\n" + toRemove.getName() + " removed");
                            }
                        });
                        break;
                }
            });
        });
    }

    public void removePackageFlow(){
        view.getPackageChoice(6, menu.getPackages(), item -> {
            // use item as an int
            ArrayList<PackageEntity> packages = menu.getPackages();
            PackageEntity toRemove = packages.get(item-1);
            menu.removePackageEntity(toRemove);
            view.displayResults("\n" + "Package removed");
        });
    }

}
