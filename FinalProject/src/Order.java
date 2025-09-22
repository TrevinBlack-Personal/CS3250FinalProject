import java.util.Date;

public class Order {
    // Attributes
    private String orderId;
    private Date orderDate;
    private String status;
    private double totalAmount;

    // Constructors
    public Order() {}
    public Order(String orderId, Date orderDate, String status, double totalAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    // Methods
    public void calculateTotal() {}
    public void cancelOrder() {}
    public void printOrderDetails() {}
}