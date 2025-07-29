package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeMapper;
import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.dto.EmployeeResponseDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toResponseDTOList(employees);
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return employeeMapper.toResponseDTO(employee);
    }

    public List<EmployeeResponseDTO> getEmployeesByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        return employeeMapper.toResponseDTOList(employees);
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        if (employeeRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalStateException("Email already in use");
        }

        Employee employee = employeeMapper.toEntity(requestDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponseDTO(savedEmployee);
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        // Check if email is being changed and if new email already exists
        if (!employee.getEmail().equals(requestDTO.getEmail()) &&
                employeeRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalStateException("Email already in use");
        }

        employeeMapper.updateEntityFromDTO(employee, requestDTO);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponseDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    // Additional method to get Employee entity (for internal use)
    public Employee getEmployeeEntityById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }
}