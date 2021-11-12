package Helpers;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenceManager {
    
    // Load Data -- generic constructor
    public <T> void loadData(String fileName, ArrayList dataList){
        File file = new File(fileName);
        try{
            FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fileInput);

			// Read objects
            while (true){
                T data = (T) input.readObject();
                // reservations
                dataList.add(data);
                System.out.println(data);
            }
            
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Loading complete");
		} 
        catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    public void saveData(String fileName, ArrayList dataList){
        File file = new File(fileName);
        try{
            FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fileOutput);

            dataList.forEach(data -> {
                try{
                    output.writeObject(data);
                }catch(IOException e) {
                    System.out.println("Error initializing stream");
                } 
            });
            
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
		} 
        catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
    }

}
