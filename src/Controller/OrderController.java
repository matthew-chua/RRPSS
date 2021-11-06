package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.element.PackageElement;

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
            
            view.getQty(100, qty -> {
                addAlaCarteItem(choice-1, qty);
                view.displayResults("Item added");
            });
        });
    }


    private void addPackageFlow(){
        view.getPackageIndex(this.restaurant.getMenu().getPackages(), choice -> {
            
            view.getQty(100, qty -> {
                // addAlaCarteItem(choice-1, qty);
                addPackageItem(choice-1, qty);
                view.displayResults("Item added");
            });
        });
        // view.displayResults("Item added");
    }

    public void addAlaCarteItem(int index, int qty) {
        AlaCarteEntity item = this.restaurant.getMenu().getAlaCarteItems().get(index);
        this.order.addMenuItem(item, qty);
        restaurant.setTables(tables);
        // this.restaurant.set
    }

    public void addPackageItem(int index, int qty){
        PackageEntity pkg = this.restaurant.getMenu().getPackages().get(index);
        this.order.addSpecial(pkg, qty);
        restaurant.setTables(tables);
    }

    public void removeAlaCarteFlow(){

        ArrayList<AlaCarteEntity> alaCarteItems = new ArrayList<AlaCarteEntity>(this.order.getMenuItems().keySet());

        view.getAlaCarteItemIndex(alaCarteItems , choice -> {

            view.getQty(100, qty -> {
                // addPackageItem(choice-1, qty);
                // view.displayResults("Item added");

                // first, get the item
                AlaCarteEntity item = alaCarteItems.get(choice-1);
                // remove
                removeAlaCarteItem(item, qty);
                restaurant.setTables(tables);
                view.displayResults("Item removed");
            });
            
            // removeAlaCarteItem(choice-1);
            // restaurant.setTables(tables);
            // view.displayResults("Item removed");
        });

    }
    private void removePackageFlow(){
        ArrayList<PackageEntity> pkgItems = new ArrayList<PackageEntity>(this.order.getSpecials().keySet());
        view.getPackageIndex(pkgItems, choice -> {
            view.getQty(100, qty -> {
                // addPackageItem(choice-1, qty);
                // view.displayResults("Item added");

                // first, get the item
                PackageEntity item = pkgItems.get(choice-1);
                // remove
                removePackageItem(item, qty);
                restaurant.setTables(tables);
                view.displayResults("Item removed");
            });

            // removePackageItem(choice-1);
            // restaurant.setTables(tables);
            // view.displayResults("Item removed");
        });
    }

    public void removeAlaCarteItem(AlaCarteEntity item, int qty) {
        // AlaCarteEntity item = this.restaurant.getMenu().getAlaCarteItems().get(index);
        this.order.removeMenuItem(item, qty);
    }

    public void removePackageItem(PackageEntity item, int qty){
        // PackageEntity pkg = this.restaurant.getMenu().getPackages().get(index);
        this.order.removeSpecial(item, qty);
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
            // boolean isMember = choice == 1;
            // InvoiceEntity invoice = new InvoiceEntity(servingStaffEntity.getName(), this.table.getNumber(), this.order.getMenuItems(), this.order.getSpecials(), isMember);
            
            // view.printInvoiceFormat();
            // view.displayResults(invoice.toString());
            // // delete order from table
            // table.setOrder(null);

            // restaurant.addInvoice(invoice);

            // // save data
            // restaurant.setTables(tables);
        });
    }

    public void createOrder(StaffEntity servingStaffEntity) {
        OrderEntity newOrder = new OrderEntity(servingStaffEntity, table);
        // this.tables.get(tableIndex).setOrder(newOrder);
        table.setOrder(newOrder);
        order = newOrder;
        restaurant.setTables(tables);
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
