import java.util.Date;

public class MaintenanceLog {
    // Attributes
    private String recordId;
    private String equipmentId;
    private Date maintenanceDate;
    private String description;
    private String performedBy;

    // Constructors
    public MaintenanceLog() {}
    public MaintenanceLog(String recordId, String equipmentId, Date maintenanceDate,
                             String description, String performedBy) {
        this.recordId = recordId;
        this.equipmentId = equipmentId;
        this.maintenanceDate = maintenanceDate;
        this.description = description;
        this.performedBy = performedBy;
    }

    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public Date getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(Date maintenanceDate) { this.maintenanceDate = maintenanceDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }

    // Methods
    public void addMaintenanceLog() {}
    public void updateMaintenanceDetails(String description) {}
    public void printRecordDetails() {}
}
