package domain;

/**
 * Represents a hotel employee and extends the personal information from Guest.
 */
public class Employee extends Guest {

    private static int nextEmployeeNumber = 0;
    private int employeeNumber;
    private String position;
    private double monthlySalary;
    private boolean active;

    /**
     * Creates an employee with an automatic employee number.
     */
    public Employee(String fullName, String email, long phoneNumber, long documentId, String position, double monthlySalary) {
        super(fullName, email, phoneNumber, documentId);
        nextEmployeeNumber++;
        this.employeeNumber = nextEmployeeNumber;
        setPosition(position);
        setMonthlySalary(monthlySalary);
        this.active = true;
    }

    public static int getNextEmployeeNumber() {
        return nextEmployeeNumber;
    }

    public static void setNextEmployeeNumber(int nextEmployeeNumber) {
        Employee.nextEmployeeNumber = nextEmployeeNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getPosition() {
        return position;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public boolean isActive() {
        return active;
    }

    public void setPosition(String position) {
        this.position = position == null || position.isBlank() ? "Undefined" : position.trim();
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary > 0 ? monthlySalary : 0;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Marks the employee as inactive without removing the record from the system.
     */
    public void dismiss() {
        this.active = false;
    }

    @Override
    public String toString() {
        return "Employee{employeeNumber=" + employeeNumber + ", fullName='" + getFullName() + '\'' +
                ", documentId=" + getDocumentId() + ", email='" + getEmail() + '\'' +
                ", phoneNumber=" + getPhoneNumber() + ", position='" + position + '\'' +
                ", monthlySalary=" + monthlySalary + ", active=" + active + '}';
    }
}
