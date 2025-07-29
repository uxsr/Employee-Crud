package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.dto.EmployeeResponseDTO;
import com.example.employeemanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "Operations for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of employees",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDTO.class)))
    })
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieve a specific employee by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content)
    })
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @Parameter(description = "ID of the employee to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/department/{department}")
    @Operation(summary = "Get employees by department", description = "Retrieve all employees belonging to a specific department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employees by department",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDTO.class)))
    })
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByDepartment(
            @Parameter(description = "Department name to filter employees", required = true)
            @PathVariable String department) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(department));
    }

    @PostMapping
    @Operation(summary = "Create a new employee", description = "Add a new employee to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content)
    })
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Parameter(description = "Employee data to create", required = true)
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Update an existing employee's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content)
    })
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @Parameter(description = "ID of the employee to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated employee data", required = true)
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Remove an employee from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteEmployee(
            @Parameter(description = "ID of the employee to delete", required = true)
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}