public class Vendor extends User {
    // Attributes
    private String vendorId;
    private String companyName;
    private String productCategory;

    // Constructors
    public Vendor() {}
    public Vendor(int id, String name, String email, String password,
                  String vendorId, String companyName, String productCategory) {
        super(id, name, email, password);
        this.vendorId = vendorId;
        this.companyName = companyName;
        this.productCategory = productCategory;
    }

    // Getters and Setters
    public String getVendorId() { return vendorId; }
    public void setVendorId(String vendorId) { this.vendorId = vendorId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    // Methods
    public void supplyProduct(String product, int quantity) {}
    public void updateCatalog() {}
    public void communicateWithManager(Manager manager) {}
}