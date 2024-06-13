package model;

import java.time.LocalDate;

public class Employee
{
    private String employeeName;
    private LocalDate hireDate;
    private long weight; // A variable to store weight used in lottery draw


    public Employee(String employeeName, LocalDate hireDate)
    {
        this.employeeName = employeeName;
        this.hireDate = hireDate;
    }

    // Getters and Setters
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

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
