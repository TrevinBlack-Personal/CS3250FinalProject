import java.util.Date;


public class PurchaseOrder extends Order {
    // Attributes
    private String vendorId;
    private Date expectedDeliveryDate;
    private String shippingMethod;

    // Constructors
    public PurchaseOrder() {}
    public PurchaseOrder(String orderId, Date orderDate, String status, double totalAmount,
                         String vendorId, Date expectedDeliveryDate, String shippingMethod) {
        super(orderId, orderDate, status, totalAmount);
        this.vendorId = vendorId;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.shippingMethod = shippingMethod;
    }

    // Getters and Setters
    public String getVendorId() { return vendorId; }
    public void setVendorId(String vendorId) { this.vendorId = vendorId; }

    public Date getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }

    public String getShippingMethod() { return shippingMethod; }
    public void setShippingMethod(String shippingMethod) { this.shippingMethod = shippingMethod; }

    // Methods
    public void sendToVendor() {}
    public void receiveGoods() {}
    public void approvePurchase() {}
}
