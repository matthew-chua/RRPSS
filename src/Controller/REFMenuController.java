// IGNORE THIS FILE IT WAS MY OLD MENU CONTROLLER JUST KEEPING IT HERE FOR REFERENCE

// package Entity;
// import Entity.AlaCarteEntity;
// import Entity.PackageEntity;
// import Entity.AlaCarteEntity.Type;
// import java.util.ArrayList;
// import java.util.Scanner;


// // change this document to become menu entity
// // menu controller will call the functions in this entity + print stuff/get input from boundary
// // controller only calls functions, does not define any function or get any input

// // import Boundary.*;

// public class MenuController {
    
//     // UI
//     // private MenuBoundary view;

//     // Attributes -> put in restaurant entity
//     private ArrayList<AlaCarteEntity> alaCarteItems;
//     private ArrayList<PackageEntity> packageItems;

//     // Constructor
//     //////////// FOR CONTROLLER
//     public MenuController(){
//         // this.view = new MenuBoundary();
//         this.start();
//     }

//     private void start(){
//     }

//     // use hashmap??
//     // public AlaCarteEntity getAlaCarteEntityByID(int menuItemID){
//     //     for(AlaCarteEntity menuItem : this.alaCarteItems){
//     //         if(menuItem.getItemId()==menuItemID){
//     //             return menuItem;
//     //         }
//     //     }
//     // }

//     public void printAlaCarteItems(){

//     }

//     ////// BOUNDARY
//     public void printMenu(){
//         ArrayList<AlaCarteEntity> items = this.alaCarteItems;
//         ArrayList<PackageEntity> packages = this.packageItems;

//         System.out.println("\n==============================");
//         System.out.println("\nChef KuKu's Italian Cock with Tails");
//         System.out.println("\n==============================");
//         System.out.println("\nMenu Items: ");

//         System.out.println("\n- Appetisers: ");
//         for(AlaCarteEntity item : items){
//             if (item.getType() == Type.APPETISER
//             && !item.getRemovalStatus()){
//                 System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
//             }
//         }

//         System.out.println("\n- Main Courses: ");
//         for(AlaCarteEntity item : items){
//             if (item.getType() == Type.MAINCOURSE
//             && !item.getRemovalStatus()){
//                 System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
//             }
//         }

//         System.out.println("\n- Drinks: ");
//         for(AlaCarteEntity item : items){
//             if (item.getType() == Type.DRINK
//             && !item.getRemovalStatus()){
//                 System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
//             }
//         }

//         System.out.println("\n- Desserts: ");
//         for(AlaCarteEntity item : items){
//             if (item.getType() == Type.DESSERT
//             && !item.getRemovalStatus()){
//                 System.out.printf("\n    - %s          $%d", item.getName(), item.getPrice());
//             }
//         }

//         System.out.println("\n");
//         System.out.println("\nPackages: ");
//         for(PackageEntity pkg : packages){
//             if(!pkg.getRemovalStatus()){
//                 System.out.printf("\n- %s          $%d", pkg.getName(), pkg.getPrice());
            
//                 ArrayList<AlaCarteEntity> packageItems = pkg.getItems();
//                 for(AlaCarteEntity pkgItem : packageItems){
//                     if(!pkgItem.getRemovalStatus()){
//                         System.out.printf("\n    - %s          $%d", pkgItem.getName(), pkgItem.getPrice());
//                     }
//                 }
//             }
//         }
//     }
//     ////// BOUNDARY^


// ///// ENTITY
//     // public ArrayList<AlaCarteEntity> getAlaCarteItems(){
//     //     return this.alaCarteItems;
//     // }
    
//     // public ArrayList<PackageEntity> getPackageEntitys(){
//     //     return this.packageItems;
//     // }
// //// ENTITY^


//     public void addAlaCarteItem(){
//         // "Add new menu item"
//         // create new menu object
//         int createdId = this.alaCarteItems.size() + 1;
//         AlaCarteEntity acItem = new AlaCarteEntity(createdId);
        
//         Scanner scan = new Scanner(System.in);
//         // set name
//         System.out.println("\nEnter menu item name: ");
//         acItem.setName(scan.nextLine());

//         // set desc
//         System.out.println("\nEnter menu item description: ");
//         acItem.setDesc(scan.nextLine());

//         //set price
//         System.out.println("\nEnter menu item price: ");
//         acItem.setPrice(scan.nextDouble());

//         // set type
//         System.out.println("\nEnter menu item type (1-4): ");
//         System.out.println("\n1. Appetiser");
//         System.out.println("\n2. Main Course");
//         System.out.println("\n3. Drink");
//         System.out.println("\n4. Dessert");
//         int choice = scan.nextInt();

//         switch(choice){
//             case 1:
//                 acItem.setType(Type.APPETISER);
//             case 2:
//                 acItem.setType(Type.MAINCOURSE);
//             case 3:
//                 acItem.setType(Type.DRINK);
//             case 4:
//                 acItem.setType(Type.DESSERT);
//         }
//         scan.close();
        
//     }
    
//     public void addPackageEntity(){
//         // package item = 1 package
//         // 1 package item contains multiple menu items

//         // create new menu object
//         int createdId = this.packageItems.size() + 1;
//         PackageEntity pkgItem = new PackageEntity(createdId);
        
//         Scanner scan = new Scanner(System.in);
//         // set name
//         System.out.println("\nEnter package name: ");
//         pkgItem.setName(scan.nextLine());

//         // set desc
//         System.out.println("\nEnter package description: ");
//         pkgItem.setDesc(scan.nextLine());

//         //set price
//         System.out.println("\nEnter package price: ");
//         pkgItem.setPrice(scan.nextDouble());

//         // set type
//         System.out.println("\nEnter number of menu items to be added to package: ");
//         int num = scan.nextInt();

//         for(int i = 0; i<num; i++){
//             int itemCheck = 0; // check if item is deleted

//             while(itemCheck == 0){
//                 System.out.println("\nEnter menu item ID to add to package: ");
            
//                 // print out all menu items with id as options
//                 for(AlaCarteEntity menuItem : this.alaCarteItems){
//                     System.out.printf("\n    %d. %s --$%d", menuItem.getItemId(), menuItem.getName(), menuItem.getPrice());
//                 }
//                 int choice = scan.nextInt();

//                 if(choice > 1 && choice <= this.alaCarteItems.size()){
//                     if(!menuItem.isRemoved){

//                     }
//                 }
//             }
            
//             // add corresponding menu item to package
//             pkgItem.addItem(menuItem);

//         }
        
//         scan.close();
//     }

//     // GET ITEM BY ID
//     public void updateAlaCarteItem(int item_id){

//         // update package respectively

//         // choose item to update

//         // DO THIS ->> get item by item id

//         //////////////////////////////////////////
//         // temporary menuItem object
//         AlaCarteEntity menuItem = new AlaCarteEntity(item_id);
//         //////////////////////////////////////////

//         // print item to be updated
//         System.out.printf("\nUpdating menu item %d...", menuItem.getItemId());
//         System.out.printf("\nName: %s", menuItem.getName());
//         System.out.printf("\nDescription: %s", menuItem.getDesc());
//         System.out.printf("\nPrice: %s", menuItem.getPrice());
//         System.out.printf("\nType: %s", menuItem.getType());

//         // choose field to update
//         Scanner scan = new Scanner(System.in);
//         int choice;

//         while(true){
//             System.out.println("\nChoose field to update, press 0 to exit: ");
//             System.out.println("\n1. Name ");
//             System.out.println("\n2. Description ");
//             System.out.println("\n3. Price ");
//             System.out.println("\n4. Type ");
//             System.out.println("\n0. Update Complete ");
//             choice = scan.nextInt();
//             if(choice == 0){
//                 break;
//             }
//             switch(choice){
//                 case 1:
//                     System.out.println("\nEnter new menu item name: ");
//                     String name = scan.nextLine();
//                     menuItem.setName(name);
//                     System.out.printf("\nMenu item new name set as: %s", name);
                    
//                 case 2:
//                     System.out.println("\nEnter new menu item description: ");
//                     String desc = scan.nextLine();
//                     menuItem.setDesc(desc);
//                     System.out.printf("\nMenu item new description set as: %s", desc);

//                 case 3:
//                     System.out.println("\nEnter new menu item price: ");
//                     Double price = scan.nextDouble();
//                     menuItem.setPrice(price);
//                     System.out.printf("\nMenu item new name set as: %d", price);

//                 case 4:
//                     System.out.println("\nEnter new menu item type (1-4): ");
//                     System.out.println("\n1. Appetiser");
//                     System.out.println("\n2. Main Course");
//                     System.out.println("\n3. Drink");
//                     System.out.println("\n4. Dessert");
//                     int type = scan.nextInt();
            
//                     switch(type){
//                         case 1:
//                             menuItem.setType(Type.APPETISER);
//                             System.out.printf("\nMenu item new type set as: %s", Type.APPETISER);
//                         case 2:
//                             menuItem.setType(Type.MAINCOURSE);
//                             System.out.printf("\nMenu item new type set as: %s", Type.MAINCOURSE);
//                         case 3:
//                             menuItem.setType(Type.DRINK);
//                             System.out.printf("\nMenu item new type set as: %s", Type.DRINK);
//                         case 4:
//                             menuItem.setType(Type.DESSERT);
//                             System.out.printf("\nMenu item new type set as: %s", Type.DESSERT);
//                     }
//             }
//         }
//         scan.close();

//         // print updated menu item
//         System.out.printf("\nUpdated Menu item %d", menuItem.getItemId());
//         System.out.printf("\nName: %s", menuItem.getName());
//         System.out.printf("\nDescription: %s", menuItem.getDesc());
//         System.out.printf("\nPrice: %s", menuItem.getPrice());
//         System.out.printf("\nType: %s", menuItem.getType());
//     }
    
//     // GET ITEM BY ID
//     public void updatePackageEntity(int package_id){
//         // find a package, edit contents - add/remove menu item
//         // choose item to update

//         // DO THIS ->> get item by item id

//         //////////////////////////////////////////
//         // temporary menuItem object
//         PackageEntity pkg = new PackageEntity(package_id);
//         //////////////////////////////////////////

//         // print item to be updated
//         System.out.printf("\nUpdating package item %d...", pkg.getPackageId());
//         System.out.printf("\nName: %s", pkg.getName());
//         System.out.printf("\nDescription: %s", pkg.getDesc());
//         System.out.printf("\nPrice: %s", pkg.getPrice());
//         System.out.println("\nContents: ");
//         pkg.printPackageContents();

//         // choose field to update
//         Scanner scan = new Scanner(System.in);
//         int choice;

//         while(true){
//             System.out.println("\nChoose field to update, press 0 to exit: ");
//             System.out.println("\n1. Name ");
//             System.out.println("\n2. Description ");
//             System.out.println("\n3. Price ");
//             System.out.println("\n4. Add Item ");
//             System.out.println("\n5. Remove Item ");
//             System.out.println("\n0. Update Complete ");
//             choice = scan.nextInt();
//             if(choice == 0){
//                 break;
//             }
//             switch(choice){
//                 case 1:
//                     System.out.println("\nEnter new Package name: ");
//                     String name = scan.nextLine();
//                     pkg.setName(name);
//                     System.out.printf("\nPackage new name set as: %s", name);
                    
//                 case 2:
//                     System.out.println("\nEnter new Package description: ");
//                     String desc = scan.nextLine();
//                     pkg.setDesc(desc);
//                     System.out.printf("\nPackage new description set as: %s", desc);

//                 case 3:
//                     System.out.println("\nEnter new Package price: ");
//                     Double price = scan.nextDouble();
//                     pkg.setPrice(price);
//                     System.out.printf("\nPackage new name set as: %d", price);
                
//                 // add item -> // ref add package item
//                 case 4:
//                     System.out.println("\nEnter menu item ID to be added: ");
//                     // print all alacarte items
//                     // get menu item by id
//                     // check if pkg contains selected item
//                     // pkg.addItem();
//                     // print - item added
                
//                 // remove item
//                 case 5:
//                     System.out.println("\nEnter menu item ID to be removed: ");
//                     pkg.printPackageContents();
                    
//                     int item_id = scan.nextInt();
//                     // menuItem = get menu item by id
//                     // pkg.removeItem(menuItem);
//                     // System.out.printf("\nMenu item %s removed", menuItem.getName());
//             }
//         }
        
//         scan.close();

//         // print updated menu item
//         System.out.printf("\nUpdated package item %d...", pkg.getPackageId());
//         System.out.printf("\nName: %s", pkg.getName());
//         System.out.printf("\nDescription: %s", pkg.getDesc());
//         System.out.printf("\nPrice: %s", pkg.getPrice());
//         System.out.println("\nContents: ");
//         pkg.printPackageContents();
//     }

//     public void removeAlaCarteItem(int item_id){
//         // menuItem = get item by id
//         //  menuItem.removeItem()
//         // remove all packages containing this item
//     }
    
//     public void removePackageEntity(int package_id){
//         // packageItem = get package by id
//         //  packageItem.removePackage()
//     }

// }
