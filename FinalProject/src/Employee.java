public class Employee extends User {
    // Attributes
    private String employeeId;
    private String department;
    private double salary;

    // Constructors
    public Employee() {}
    public Employee(int id, String name, String email, String password,
                    String employeeId, String department, double salary) {
        super(id, name, email, password);
        this.employeeId = employeeId;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    // Methods
    public void clockIn() {}
    public void clockOut() {}
    public void requestLeave(int days) {}
}
