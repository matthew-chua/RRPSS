// package Boundary;

// import java.util.Scanner;

// public class Reservation extends Boundary{
//     public static void main(String[] args) {
//         int choice;
//         System.out.println("1. Find / Remove reservation booking");
//         System.out.println("2. Check table availability for reservations");
//         System.out.println("3. Create a reservation");
//         System.out.println("0. Back to main menu");

//         do {
//             System.out.println();
//             System.out.print("Enter the number of your choice: ");
//             choice = getInput();
//             switch (choice) {
//                 case 1: /* make reservation */
//                     findReservation();
//                     break;
//                 case 2: /* show list of available tables */
//                     checkAvailability();
//                     break;
//                 case 3: /* create reservation */
//                     createReservation();
//                     break;
//                 case 0: /* Back to main menu */
//                     break;
//             }while (choice < 4);
//         }
//     }

//     private static void findReservation() {
//         System.out.println("=============== Find Reservations ===============");
//         System.out.println("Enter the name of reserver:");
//         String customerName = getStringInput();
//         getReservations(customerName);
//         System.out.println("1. Cancel Reservation");
//         System.out.println("2. Return");
        
//         int choice = getIntInput();
//     }
// }