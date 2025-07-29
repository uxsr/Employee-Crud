package com.example.employeemanagement.dto;

import com.example.employeemanagement.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    /**
     * Convert Employee entity to EmployeeResponseDTO
     */
    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary(),
                employee.getPosition()
        );
    }

    /**
     * Convert EmployeeRequestDTO to Employee entity
     */
    public Employee toEntity(EmployeeRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        employee.setPosition(requestDTO.getPosition());

        return employee;
    }

    /**
     * Update existing Employee entity with EmployeeRequestDTO data
     */
    public void updateEntityFromDTO(Employee employee, EmployeeRequestDTO requestDTO) {
        if (employee == null || requestDTO == null) {
            return;
        }

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        employee.setPosition(requestDTO.getPosition());
    }

    /**
     * Convert list of Employee entities to list of EmployeeResponseDTOs
     */
    public List<EmployeeResponseDTO> toResponseDTOList(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
