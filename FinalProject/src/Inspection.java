import java.util.Date;

public class Inspection {
    // Attributes
    private String inspectionId;
    private String equipmentId;
    private Date inspectionDate;
    private String inspectorName;
    private String description;
    private boolean passed;

    // Constructors
    public Inspection() {}
    public Inspection(String inspectionId, String equipmentId, Date inspectionDate,
                      String inspectorName, String description, boolean passed) {
        this.inspectionId = inspectionId;
        this.equipmentId = equipmentId;
        this.inspectionDate = inspectionDate;
        this.inspectorName = inspectorName;
        this.description = description;
        this.passed = passed;
    }

    // Getters and Setters
    public String getInspectionId() { return inspectionId; }
    public void setInspectionId(String inspectionId) { this.inspectionId = inspectionId; }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public Date getInspectionDate() { return inspectionDate; }
    public void setInspectionDate(Date inspectionDate) { this.inspectionDate = inspectionDate; }

    public String getInspectorName() { return inspectorName; }
    public void setInspectorName(String inspectorName) { this.inspectorName = inspectorName; }

    public String getDescription() { return description; }
    public void setdescription(String description) { this.description = description; }

    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }

    // Methods
    public void performInspection() {}
    public void recorddescription(String description, boolean passed) {}
    public void scheduleFollowUpInspection(Date date) {}
}
