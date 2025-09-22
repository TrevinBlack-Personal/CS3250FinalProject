public class Manager extends Employee {
    // Attributes
    private int teamSize;
    private String level; // e.g., Senior Manager, General Manager

    // Constructors
    public Manager() {}
    public Manager(int id, String name, String email, String password,
                   String employeeId, String department, double salary,
                   int teamSize, String level) {
        super(id, name, email, password, employeeId, department, salary);
        this.teamSize = teamSize;
        this.level = level;
    }

    // Getters and Setters
    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    // Methods
    public void assignTask(String task, Employee employee) {}
    public void evaluateEmployee(Employee employee) {}
    public void approveLeave(Employee employee) {}
}