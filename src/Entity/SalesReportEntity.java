package Entity;
import java.time.LocalDateTime;
import java.util.ArrayList; 

public class SalesReportEntity {
    
    private ArrayList<InvoiceEntity> invoice;

    // constructor 
    public SalesReportEntity(ArrayList<InvoiceEntity> invoice) {
        
        // // something like get all invoices available(?)
        // for (int i = 0; i = invoiceSize; i++)
        //     this.invoice[i] = invoice;
    }

    // getters 
    public ArrayList<InvoiceEntity> getReportbyDays(LocalDateTime date) {
        // some function to get all invoices from selected date
    }

    public ArrayList<InvoiceEntity> getReportbyMonth(LocalDateTime month) {
        // some function to get all invoices from selected month
    }

    // shouldnt have setters
}
