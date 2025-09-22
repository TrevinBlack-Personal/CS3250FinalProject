import java.util.Date;

public class Schedule {
    // Attributes
    private String scheduleId;
    private String equipmentId;
    private Date scheduledDate;
    private String taskType;   // e.g., maintenance, inspection
    private String assignedTo;

    // Constructors
    public Schedule() {}
    public Schedule(String scheduleId, String equipmentId, Date scheduledDate,
                    String taskType, String assignedTo) {
        this.scheduleId = scheduleId;
        this.equipmentId = equipmentId;
        this.scheduledDate = scheduledDate;
        this.taskType = taskType;
        this.assignedTo = assignedTo;
    }

    // Getters and Setters
    public String getScheduleId() { return scheduleId; }
    public void setScheduleId(String scheduleId) { this.scheduleId = scheduleId; }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public Date getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(Date scheduledDate) { this.scheduledDate = scheduledDate; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    // Methods
    public void createSchedule() {}
    public void reschedule(Date newDate) {}
    public void cancelSchedule() {}
}
