import java.time.LocalDate;

public class Employee
{
    String employeeName;
    LocalDate hireDate;


    public Employee(String employeeName, LocalDate hireDate) {
        this.employeeName = employeeName;
        this.hireDate = hireDate;
    }

    // Getter and Setter
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }


}
