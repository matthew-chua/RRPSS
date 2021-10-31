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


public class OrderController {

    //restaurant
    private RestaurantEntity restaurant;

    // ui
    private OrderBoundary view;

    private OrderEntity order;
    Scanner sc = new Scanner(System.in);

    // constructor
    public OrderController() {
        this.view = new OrderBoundary();
        this.restaurant = RestaurantEntity.getInstance();
        this.start();
    }

    public void createOrder(StaffEntity servingStaffEntity) {
        this.order = new OrderEntity(servingStaffEntity);
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
            addItem();
            break;
        case 2:
            removeItem();
            break;

        case 0:
            break;
        default:
            break;

        }

    }

    public void addItem() {
        System.out.println("What item would you like to add?");

        // print menu here??

        //get the items from the restaurant entity
        MenuEntity menu = this.restaurant.getMenu();

        MenuBoundary menuBoundary = new MenuBoundary();
        menuBoundary.printMenu(menu.getAlaCarteItems(), menu.getPackages());

        int input = sc.nextInt();

        // do some logic here to differentiate between menu and special

        if (input < 10) { 

            // AlaCarteEntity item = new AlaCarteEntity(input);
            // this.order.addMenuItem(item);
        } else {
            // PackageEntity item = 
            // this.order.addSpecial(item);
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

    public void printInvoice(StaffEntity servingStaffEntity, int tableNo) {

        System.out.println("Is customer a member? \n 1. Yes\n 2. No");
        int member = sc.nextInt();
        Boolean membership;
        if (member == 1){
            membership = true;
        }else{
            membership = false;
        }
        

        // init invoice instance
        InvoiceEntity invoice = new InvoiceEntity(servingStaffEntity.getName(), tableNo, this.order.getMenuItems(), this.order.getSpecials(), membership);

        // do whatever logic required
        invoice.printInvoice();
    }

    private void start() {

        // get rid of this next time and pull the StaffEntity from somewhere else
        System.out.println("Hello");

        //need to get some table logic here also, idk where to pull table data from though
        // System.out.println("Which table are you serving?");
        // int tableNo = sc.nextInt();

        int tableNo = 1;

        StaffEntity servingStaffEntity = new StaffEntity();
        view.getManagerChoice(choice -> {
            switch (choice) {
            case 0:
                System.out.println("quitted");
                return;

            case 1:
                System.out.println("Create Order");
                createOrder(servingStaffEntity);
                break;

            case 2:
                System.out.println("View Order");
                viewOrder();
                break;

            case 3:
                System.out.println("Update Order");
                updateOrder();
                break;

            case 4:
                System.out.println("Print Order Invoice");
                printInvoice(servingStaffEntity, tableNo);
                break;

            default:
                break;
            }
        });
    }

}
