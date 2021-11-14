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

/**
 * 
 * Class to manage loading and saving of data
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class PersistenceManager {
    
    /**
     * Generic load data function for 
     * @param <T>       Generic type of data to load into -- needs to conform to serializable
     * @param fileName  is a string representing the file name of the text file stored at 'src' folder   
     * @param dataList  is a generic ArrayList to add the data into
     */
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

    /**
     * Saves an ArrayList of serializable objects into a textfile.
     * @param fileName  is a string representing the file name of the text file stored at 'src' folder   
     * @param dataList  is a generic ArrayList with data to store.
     */
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
