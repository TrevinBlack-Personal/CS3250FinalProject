public class Fuel {
    // Attributes
    private String fuelId;
    private String fuelType; // e.g., Diesel, Gasoline
    private double quantity; // in liters
    private double costPerUnit;

    // Constructors
    public Fuel() {}
    public Fuel(String fuelId, String fuelType, double quantity, double costPerUnit) {
        this.fuelId = fuelId;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.costPerUnit = costPerUnit;
    }

    // Getters and Setters
    public String getFuelId() { return fuelId; }
    public void setFuelId(String fuelId) { this.fuelId = fuelId; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getCostPerUnit() { return costPerUnit; }
    public void setCostPerUnit(double costPerUnit) { this.costPerUnit = costPerUnit; }

    // Methods
    public void refuelEquipment(Equipment equipment, double liters) {}
    public void addFuelStock(double liters) {}
    public double calculateTotalCost() { return 0.0; }
}