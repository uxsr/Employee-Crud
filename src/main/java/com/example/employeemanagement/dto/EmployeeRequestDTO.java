package com.example.employeemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Data Transfer Object for creating or updating an employee")
public class EmployeeRequestDTO {

    @NotBlank(message = "First name is required")
    @Schema(description = "First name of the employee", example = "John", required = true)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Schema(description = "Last name of the employee", example = "Doe", required = true)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Email address of the employee", example = "john.doe@company.com", required = true)
    private String email;

    @NotBlank(message = "Department is required")
    @Schema(description = "Department where the employee works", example = "Engineering", required = true)
    private String department;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    @Schema(description = "Monthly salary of the employee", example = "5000.00", required = true)
    private Double salary;

    @NotBlank(message = "Position is required")
    @Schema(description = "Job position/title of the employee", example = "Software Engineer", required = true)
    private String position;

    // Default constructor
    public EmployeeRequestDTO() {
    }

    // Constructor with all fields
    public EmployeeRequestDTO(String firstName, String lastName, String email,
                              String department, Double salary, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.position = position;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}