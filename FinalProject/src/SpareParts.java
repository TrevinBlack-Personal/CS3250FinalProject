public class SpareParts {
    // Attributes
    private String partId;
    private String name;
    private String equipmentId; // relates to equipment
    private int quantityInStock;
    private double cost;

    // Constructors
    public SpareParts() {}
    public SpareParts(String partId, String name, String equipmentId,
                     int quantityInStock, double cost) {
        this.partId = partId;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.cost = cost;
    }

    // Getters and Setters
    public String getPartId() { return partId; }
    public void setPartId(String partId) { this.partId = partId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    // Methods
    public void usePart(int quantity) {}
    public void restock(int quantity) {}
    public void displayPartInfo() {}
}