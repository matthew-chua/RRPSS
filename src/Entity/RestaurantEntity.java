package Entity;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



// public enum RestaurantDataType{
//     TABLE, STAFF, ORDER, INVOICE, RESERVATION
// }

public class RestaurantEntity {

    static RestaurantEntity shared = null;

    public RestaurantEntity(){
        // this.shared = new RestaurantEntity();

        // read from file
        // try{
            // loadAllData();
            loadAllData2();
        // }catch (FileNotFoundException err){
        //     System.out.println("Error");
        // }
    }

    private Table[] tables;
    private StaffEntity[] staff;
    private OrderEntity[] orders;
    // private InvoiceEntity[] invoices;

    public static RestaurantEntity getInstance(){
        if (shared == null){
            shared = new RestaurantEntity();
        }
        return shared;
    }

    

    private void loadAllData() throws FileNotFoundException{
        
        System.out.println("Loading all data");
        // try{
        File file = new File("Reservations.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }

    }

    private void loadAllData2(){
        File file = new File("Reservations.txt");
        StaffEntity staff = new StaffEntity();
        // ReservationEntity reservation = new ReservationEntity("name", "something", 10, "again", "yet again");

        try{
            FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(staff);
            // o.writeObject(reservation);

            FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			StaffEntity pr1 = (StaffEntity) oi.readObject();
			// ReservationEntity pr2 = (ReservationEntity) oi.readObject();

			System.out.println(pr1.getName());
            System.out.println(pr1.getGender());

        } catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Load Data
    private void loadData(String fileName, Object o){



    }


    

    
}
