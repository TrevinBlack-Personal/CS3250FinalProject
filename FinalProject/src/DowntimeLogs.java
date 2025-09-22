import java.util.Date;

public class DowntimeLogs {
    // Attributes
    private String logId;
    private String equipmentId;
    private Date startTime;
    private Date endTime;
    private String reason;
    private double duration; // in hours

    // Constructors
    public DowntimeLogs() {}
    public DowntimeLogs(String logId, String equipmentId, Date startTime,
                       Date endTime, String reason, double duration) {
        this.logId = logId;
        this.equipmentId = equipmentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.duration = duration;
    }

    // Getters and Setters
    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }

    // Methods
    public void logDowntime() {}
    public void calculateDuration() {}
    public void generateDowntimeReport() {}
}
