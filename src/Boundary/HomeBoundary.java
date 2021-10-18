package Boundary;

public class HomeBoundary extends Boundary {
    public HomeBoundary(){

    }

    public void showHomeMenu(){
        resetUI();
        System.out.println("Hi bobberino tan ah gua, what would you like to do today?\n1. Manage Menu\n2. Manage Orders\n3. Manage Reservations\n4. Manage Sales\n5. Change Staff\n");
        int numberOfChoices = 5;
        switch (getIntInput(numberOfChoices)){
            case 0:
            System.out.println("quitted");
            return;

            case 1:
            System.out.println("Manage Menu");
            break;

            case 2:
            System.out.println("Manage Orders");
            break;

            case 3: 
            System.out.println("Manage Reservation");
            break;

            case 4:
            System.out.println("Manage Sales");
            break;

            case 5:
            System.out.println("Manage Staff");
            break;

            default:
            break;
        }
    }

}
