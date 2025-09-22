public class EquipmentType {
    // Attributes
    private String typeId;
    private String typeName;  // e.g., Excavator, Drill, Truck
    private String description;

    // Constructors
    public EquipmentType() {}
    public EquipmentType(String typeId, String typeName, String description) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.description = description;
    }

    // Getters and Setters
    public String getTypeId() { return typeId; }
    public void setTypeId(String typeId) { this.typeId = typeId; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Methods
    public void addEquipmentType() {}
    public void updateDescription(String description) {}
    public void displayTypeInfo() {}
}