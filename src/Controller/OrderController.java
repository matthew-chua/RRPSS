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



/**
 * 
 * Is the controller that updates the boundary and entities for all Menu related scenes.
 * 
 * @author      Wong Wei Bin
 * @author      Ivan Teo
 * @author      Grace Wong
 * @author      Goh Xue Zhe
 * @author      Matthew Chua
 * @version     0.1.0
 * @since       2021-11-11
 * 
 */
public class OrderController {


    /** A reference to the shared instance of restaurant entity */
    private RestaurantEntity restaurant;

    /** The Boundary responsible for displaying the Order UI */
    private OrderBoundary view;

    // entities

    /** Reference to the tables available */
    private ArrayList<Table> tables;
    /** Reference to the selected table */
    private Table table;
    /** Index of the selected table */
    private int tableIndex;

    /** Current order for the selected table */
    private OrderEntity order;
    
    Scanner sc = new Scanner(System.in);
    /** Reference to the current staff taking the order */
    StaffEntity servingStaffEntity;

    /** Flag to determine if Order Controller should terminate */
    private boolean shouldExitManager = false;

    /** Constructor */ 
    public OrderController() {
        this.view = new OrderBoundary();
        this.restaurant = RestaurantEntity.getInstance();
        // this.orders = restaurant.getOrders();
        // this.servingStaffEntity = new StaffEntity();
        this.servingStaffEntity = restaurant.getCurrentStaff();
        this.tables = restaurant.getTables();
        this.start();
    }


    /** Runs the sequenece of events for Order flows */
    private void start() {

        view.getTableChoice(tableNumber -> {
            this.tableIndex = tableNumber-1;
            this.table = restaurant.getTables().get(tableIndex);
            this.order = table.getOrder();
            getUserManagerChoice();
        });
    }

    /** Cbecks whether the table has an existing order and display the correct flow */
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

    /** The flow to update the user's order */
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

    /** The flow to select either a package or ala carte item to add */
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

    /** The flow to add an ala carte item */
    private void addAlaCarteFlow(){
        view.getAlaCarteItemIndex(this.restaurant.getMenu().getAlaCarteItems(), choice -> {
            
            view.getQty(100, qty -> {
                addAlaCarteItem(choice-1, qty);
                view.displayResults("Item added");
            });
        });
    }


    /** The flow to add a package item */
    private void addPackageFlow(){
        view.getPackageIndex(this.restaurant.getMenu().getPackages(), choice -> {
            
            view.getQty(100, qty -> {
                // addAlaCarteItem(choice-1, qty);
                addPackageItem(choice-1, qty);
                view.displayResults("Item added");
            });
        });
    }


    /** Saves the ala carte item to restaurant and order entity */
    public void addAlaCarteItem(int index, int qty) {
        AlaCarteEntity item = this.restaurant.getMenu().getAlaCarteItems().get(index);
        this.order.addMenuItem(item, qty);
        restaurant.setTables(tables);
        // this.restaurant.set
    }

    /** Saves the package item to restaurant and order entity */
    public void addPackageItem(int index, int qty){
        PackageEntity pkg = this.restaurant.getMenu().getPackages().get(index);
        this.order.addSpecial(pkg, qty);
        restaurant.setTables(tables);
    }

    /** The flow to remove an ala carte item */
    public void removeAlaCarteFlow(){

        ArrayList<AlaCarteEntity> alaCarteItems = new ArrayList<AlaCarteEntity>(this.order.getMenuItems().keySet());

        view.getAlaCarteItemIndex(alaCarteItems , choice -> {

            view.getQty(100, qty -> {

                // first, get the item
                AlaCarteEntity item = alaCarteItems.get(choice-1);
                // remove
                removeAlaCarteItem(item, qty);
                restaurant.setTables(tables);
                view.displayResults("Item removed");
            });
            
        });

    }

    /** The flow to remove a package item */
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
        });
    }

    /**
     * Removes an ala carte item from the order
     * @param item  the AlaCarteEntity to remove from the order
     * @param qty   the quantity of selected items to remove from the order
     */
    public void removeAlaCarteItem(AlaCarteEntity item, int qty) {

        this.order.removeMenuItem(item, qty);
    }

    /**
     * Removes a package item from the order
     * @param item  the PackageEntity to remove from the order
     * @param qty   the quantity of selected items to remove from the order
     */
    public void removePackageItem(PackageEntity item, int qty){
        this.order.removeSpecial(item, qty);
    }



    /** The flow to remove either an ala carte item or package item */
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

    /** The flow to print an invoice at the end of the order, which updates UI and saves the invoice, and removes order from table */
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

    /** Creates an order pegged to the table */
    public void createOrder(StaffEntity servingStaffEntity) {
        OrderEntity newOrder = new OrderEntity(servingStaffEntity, table);
        // this.tables.get(tableIndex).setOrder(newOrder);
        table.setOrder(newOrder);
        order = newOrder;
        restaurant.setTables(tables);
    }

}
