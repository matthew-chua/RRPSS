package Boundary;
import java.util.*;

public class Boundary {
    Scanner sc = new Scanner(System.in);

    public void resetUI(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String getStringInput(){
        return sc.next();
    }

    public int getIntInput(){
        return sc.nextInt();
    }
    
}
