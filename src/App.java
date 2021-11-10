import Controller.*;
import Entity.*;
// import controller.MainMenuController.Choice;


public class App {
    public static void main(String[] args) throws Exception {

        // On init, Home Controller runs its feature.


        RestaurantEntity resEntity = RestaurantEntity.getInstance();
        resEntity.printReservations();

        StaffController controller = new StaffController();

    }
}
