package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Boundary.MenuBoundary;
import Boundary.OrderBoundary;
import Entity.OrderEntity;
import Entity.PackageEntity;
import Entity.RestaurantEntity;
import Entity.StaffEntity;
import Entity.AlaCarteEntity;
import Entity.MenuEntity;
import Entity.*;
import Helpers.*;


public class OrderController {

    //restaurant
    private RestaurantEntity restaurant;

    // ui
    private OrderBoundary view;

    // entities
    // private ArrayList<OrderEntity> orders;
    private ArrayList<Table> tables;
    private Table table;
    private int tableIndex;
    private OrderEntity order;
    Scanner sc = new Scanner(System.in);
    StaffEntity servingStaffEntity;

    private boolean shouldExitManager = false;

    // constructor
    public OrderController() {
        this.view = new OrderBoundary();
        this.restaurant = RestaurantEntity.getInstance();
        // this.orders = restaurant.getOrders();
        // this.servingStaffEntity = new StaffEntity();
        this.servingStaffEntity = restaurant.getCurrentStaff();
        this.tables = restaurant.getTables();
        this.start();
    }


    private void start() {

        view.getTableChoice(tableNumber -> {
            this.tableIndex = tableNumber-1;
            this.table = restaurant.getTables().get(tableIndex);
            this.order = table.getOrder();
            getUserManagerChoice();
        });
    }

    public void getUserManagerChoice(){
        if (table.getOrder() == null){
            view.createOrderForTable(table.getNumber(), choice -> {
                createOrder(servingStaffEntity);
                view.showTableCreated();
            });
        }
        if (table.getOrder() != null){
            updateOrderFlow();
        }
        // after creation, carry on
    }
    public void updateOrderFlow(){
        view.updateOrderForTable(table.getNumber(), table.getOrder(), c -> {
            switch (c){
            case 1:
            // add item
            addItemFlow();
            getUserManagerChoice();
            break;

            case 2:
            // remove item
            removeItemFlow();
            getUserManagerChoice();
            break;
            
            case 3:
            // print invoice
            printInvoice();
            getUserManagerChoice();
            

            break;

            default:
            break;
            }
        });
    }


    private void addItemFlow(){
        view.getMenuItemType(choice -> {
            switch(choice){
                case 1:
                addAlaCarteFlow();
                break;
                case 2:
                addPackageFlow();
                break;
                default:
                break;
            }
        });
    }

    private void addAlaCarteFlow(){
        view.getAlaCarteItemIndex(this.restaurant.getMenu().getAlaCarteItems(), choice -> {
            addAlaCarteItem(choice-1);
            view.displayResults("Item added");
        });
    }
    private void addPackageFlow(){
        view.getPackageIndex(this.restaurant.getMenu().getPackages(), choice -> {
            addPackageItem(choice-1);
        });
        view.displayResults("Item added");
    }

    public void addAlaCarteItem(int index) {
        AlaCarteEntity item = this.restaurant.getMenu().getAlaCarteItems().get(index);
        this.order.addMenuItem(item);
        restaurant.setTables(tables);
        // this.restaurant.set
    }

    public void addPackageItem(int index){
        PackageEntity pkg = this.restaurant.getMenu().getPackages().get(index);
        this.order.addSpecial(pkg);
        restaurant.setTables(tables);
    }

    public void removeAlaCarteFlow(){
        view.getAlaCarteItemIndex(this.order.getMenuItems(), choice -> {
            // addAlaCarteItem(choice-1);
            removeAlaCarteItem(choice-1);
            restaurant.setTables(tables);
            view.displayResults("Item removed");
        });
    }
    private void removePackageFlow(){
        view.getPackageIndex(this.order.getSpecials(), choice -> {
            removePackageItem(choice-1);
            restaurant.setTables(tables);
            view.displayResults("Item removed");
        });
    }

    public void removeAlaCarteItem(int index) {
        AlaCarteEntity item = this.restaurant.getMenu().getAlaCarteItems().get(index);
        this.order.removeMenuItem(index);
        // this.restaurant.set
    }

    public void removePackageItem(int index){
        PackageEntity pkg = this.restaurant.getMenu().getPackages().get(index);
        this.order.removeSpecial(index);
    }



    public void removeItemFlow(){
        view.getMenuItemType(choice -> {
            switch(choice){
                case 1:
                removeAlaCarteFlow();
                break;
                case 2:
                removePackageFlow();
                break;
                default:
                break;
            }
        });
    }

    public void printInvoice(){
        
        view.getUserMembership(choice -> {
            view.resetUI();
            boolean isMember = choice == 1;
            InvoiceEntity invoice = new InvoiceEntity(servingStaffEntity.getName(), this.table.getNumber(), this.order.getMenuItems(), this.order.getSpecials(), isMember);
            
            view.printInvoiceFormat();
            view.displayResults(invoice.toString());
            // delete order from table
            table.setOrder(null);

            restaurant.addInvoice(invoice);

            // save data
            restaurant.setTables(tables);
        });
    }

    public void createOrder(StaffEntity servingStaffEntity) {
        OrderEntity newOrder = new OrderEntity(servingStaffEntity, table);
        // this.tables.get(tableIndex).setOrder(newOrder);
        table.setOrder(newOrder);
        order = newOrder;
        restaurant.setTables(tables);
    }

    public void viewOrder() {
        // idk how to check for an order that is not created yet lol
        printMenuItems();
        printSpecialItems();
    }

    public void printMenuItems() {
        System.out.println("MENU ITEMS");
        System.out.println("===========================");
        
        ArrayList<AlaCarteEntity> menuItems = order.getMenuItems();

        if (menuItems.size() == 0) {
            System.out.println("No Menu Items Ordered Yet.");
        }

        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(menuItems.get(i));
        }
    }

    public void printSpecialItems() {
        System.out.println("SPECIAL ITEMS");
        System.out.println("===========================");
        ArrayList<PackageEntity> specials = order.getSpecials();

        if (specials.size() == 0) {
            System.out.println("No Specials Ordered Yet.");
        }

        for (int i = 0; i < specials.size(); i++) {
            System.out.println(specials.get(i));
        }
    }

    public void updateOrder() {
        System.out.println("Would you like to remove or add an item?");
        System.out.println("0. Go Back");
        System.out.println("1. Add item");
        System.out.println("2. Remove item");

        int input = sc.nextInt();

        switch (input) {
        case 1:
            // addItem();
            break;
        case 2:
            // removeItem();
            break;

        case 0:
            break;
        default:
            break;

        }

    }


    public void removeItem() {
        System.out.println("What item would you like to remove?");
        System.out.println("1. Menu Items");
        System.out.println("2. Specials");

        int input = sc.nextInt();
        if (input == 1) {
            printMenuItems();
            System.out.println("Which item do you want to remove?");
            int removeIndex = sc.nextInt();
            this.order.removeMenuItem(removeIndex);
        } else if (input == 2) {
            printSpecialItems();
            System.out.println("Which special do you want to remove?");
            int removeIndex = sc.nextInt();
            this.order.removeSpecial(removeIndex);
        } else {
            return;
        }

    }

    // public void printInvoice(StaffEntity servingStaffEntity, int tableNo) {
    //     System.out.println("Is customer a member? \n 1. Yes\n 2. No");
    //     int member = sc.nextInt();
    //     Boolean membership;
    //     if (member == 1){
    //         membership = true;
    //     }else{
    //         membership = false;
    //     }
    //     // init invoice instance
    //     InvoiceEntity invoice = new InvoiceEntity(servingStaffEntity.getName(), tableNo, this.order.getMenuItems(), this.order.getSpecials(), membership);

    //     // do whatever logic required
    //     invoice.printInvoice();
    // }
}
