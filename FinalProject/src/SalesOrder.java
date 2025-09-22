import java.util.Date;

public class SalesOrder extends Order {
    // Attributes
    private String customerId;
    private String shippingAddress;
    private String paymentMethod;

    // Constructors
    public SalesOrder() {}
    public SalesOrder(String orderId, Date orderDate, String status, double totalAmount,
                      String customerId, String shippingAddress, String paymentMethod) {
        super(orderId, orderDate, status, totalAmount);
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    // Methods
    public void processPayment() {}
    public void shipOrder() {}
    public void generateInvoice() {}
}
