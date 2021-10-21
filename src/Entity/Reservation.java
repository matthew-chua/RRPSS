package Entity;

public class Reservation {
    private String date;
    private String time;
    private int pax;
    private String name;
    private String contact;
    private int table;

    public Reservation(String date, String time, int pax, String name, String contact, int table){
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
        this.table = table;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public int getPax(){
        return this.pax;
    }

    public String[] getDetails(){
        String[] details = {name, contact};
        return details;
    }

    public int getTable(){
        return this.table;
    }
}
