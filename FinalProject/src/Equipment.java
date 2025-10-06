import java.util.Date;

public class Equipment {
    // Attributes
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private Date purchaseDate;
    private String status; // e.g., Active, Under Maintenance, Retired

    // Constructors
    public Equipment() {}
    public Equipment(String equipmentId, String name, EquipmentType type,
                     Date purchaseDate, String status) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    // Getters and Setters
    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public EquipmentType getType() { return type; }
    public void setType(EquipmentType type) { this.type = type; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Methods
    public void assignToProject(String projectId) {}
    public void markAsUnderMaintenance() {}
    public void retireEquipment() {}
}