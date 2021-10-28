package Entity;

import java.time.LocalDateTime;

public class InvoiceEntity {

    private String StaffEntityName; // need on receipt?
    private LocalDateTime timestamp = LocalDateTime.now(); // or the order time?
    private int tableNumber; // get from Table class?
    private ArrayList<MenuItem> menuItems; // get from Order class?
    private double paymentDue; // do in order or invoice
    private Boolean membership; // checking here?

    // constructor
    public InvoiceEntity() {

    }

    public InvoiceEntity(String name, String gender, int employeeID, String jobTitle) {

    }

    // getters;

    // setters

}
