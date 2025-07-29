package com.example.employeemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for employee response")
public class EmployeeResponseDTO {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Long id;

    @Schema(description = "First name of the employee", example = "John")
    private String firstName;

    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the employee", example = "john.doe@company.com")
    private String email;

    @Schema(description = "Department where the employee works", example = "Engineering")
    private String department;

    @Schema(description = "Monthly salary of the employee", example = "5000.00")
    private Double salary;

    @Schema(description = "Job position/title of the employee", example = "Software Engineer")
    private String position;

    @Schema(description = "Full name of the employee", example = "John Doe")
    private String fullName;

    // Default constructor
    public EmployeeResponseDTO() {
    }

    // Constructor with all fields
    public EmployeeResponseDTO(Long id, String firstName, String lastName, String email,
                               String department, Double salary, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.position = position;
        this.fullName = firstName + " " + lastName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        updateFullName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        updateFullName();
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Helper method to update full name
    private void updateFullName() {
        if (firstName != null && lastName != null) {
            this.fullName = firstName + " " + lastName;
        }
    }
}