package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import Boundary.OrderBoundary;
import Entity.OrderEntity;
import Entity.Special;
import Entity.Staff;
import Entity.MenuItem;

public class OrderController {

    // ui
    private OrderBoundary view;
    private OrderEntity order;
    Scanner sc = new Scanner(System.in);

    // constructor
    public OrderController() {
        this.view = new OrderBoundary();
        this.start();
    }

    public void createOrder(Staff servingStaff) {
        this.order = new OrderEntity(servingStaff);
    }

    public void viewOrder() {

        // idk how to check for an order that is not created yet lol
        printMenuItems();
        printSpecialItems();
    }

    public void printMenuItems() {
        System.out.println("MENU ITEMS");
        System.out.println("===========================");
        ArrayList<MenuItem> menuItems = order.getMenuItems();

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
        ArrayList<Special> specials = order.getSpecials();

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

        int input = sc.nextInt();

        // do some logic here to differentiate between menu and special

        if (input < 10) { // maybe menu items from 1-9 are ALC

            MenuItem item = new MenuItem(input);
            this.order.addMenuItem(item);
        } else {
            Special item = new Special(input);
            this.order.addSpecial(item);
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

    public void printInvoice() {

        // init invoice instance

        // do whatever logic required
    }

    private void start() {

        // get rid of this next time and pull the staff from somewhere else
        System.out.println("Hello");
        Staff servingStaff = new Staff();
        view.getManagerChoice(choice -> {
            switch (choice) {
                case 0:
                    System.out.println("quitted");
                    return;

                case 1:
                    System.out.println("Create Order");
                    createOrder(servingStaff);
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
                    printInvoice();
                    break;

                default:
                    break;
            }
        });
    }

}
