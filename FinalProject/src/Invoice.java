import java.util.Date;

public class Invoice extends Order {
    // Attributes
    private String invoiceNumber;
    private Date dueDate;
    private boolean paid;

    // Constructors
    public Invoice() {}
    public Invoice(String orderId, Date orderDate, String status, double totalAmount,
                   String invoiceNumber, Date dueDate, boolean paid) {
        super(orderId, orderDate, status, totalAmount);
        this.invoiceNumber = invoiceNumber;
        this.dueDate = dueDate;
        this.paid = paid;
    }

    // Getters and Setters
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    // Methods
    public void markAsPaid() {}
    public void sendInvoiceToCustomer() {}
    public void applyLateFee() {}
}
