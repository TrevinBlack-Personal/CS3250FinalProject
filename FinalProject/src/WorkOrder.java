import java.util.Date;

public class WorkOrder extends Order {
    // Attributes
    private String description;
    private String assignedEmployee;
    private Date dueDate;

    // Constructors
    public WorkOrder() {}
    public WorkOrder(String orderId, Date orderDate, String status, double totalAmount,
                     String description, String assignedEmployee, Date dueDate) {
        super(orderId, orderDate, status, totalAmount);
        this.description = description;
        this.assignedEmployee = assignedEmployee;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAssignedEmployee() { return assignedEmployee; }
    public void setAssignedEmployee(String assignedEmployee) { this.assignedEmployee = assignedEmployee; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    // Methods
    public void assignWork(String employee) {}
    public void markComplete() {}
    public void extendDueDate(Date newDate) {}
}