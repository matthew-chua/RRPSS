package controller;
import Boundary.*;

public class HomeController {
    private HomeBoundary view;

    public HomeController(){
        this.view = new HomeBoundary();
        this.start();
    }

    public enum Choice{
        MENU,
        ORDERS,
        RESERVATIONS,
        SALES,
    }


    private void start(){
        view.showHomeMenu();
    }

    public void navigateTo(Choice choice){

        switch (choice){
        // case 
        case MENU:
        // MenuController.start();
        System.out.println("Hello 1231231");
        
        break;

        case ORDERS:

        break;

        case RESERVATIONS:

        break;

        case SALES:

        break;

        }
    }   



}