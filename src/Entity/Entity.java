package Entity;
import java.util.*;

public class Entity {
    
    public void resetUI(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void getInput(){
        Scanner sc = new Scanner(System.in);
        sc.next();
        return ;
    }


}
