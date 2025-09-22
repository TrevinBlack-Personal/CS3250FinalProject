public class Customer extends User {
    // Attributes
    private String customerId;
    private String address;
    private String phoneNumber;

    // Constructors
    public Customer() {}
    public Customer(int id, String name, String email, String password,
                    String customerId, String address, String phoneNumber) {
        super(id, name, email, password);
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // Methods
    public void placeOrder(String product, int quantity) {}
    public void trackOrder(String orderId) {}
    public void makePayment(double amount) {}
}