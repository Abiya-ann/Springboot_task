package com.example.demo.Service; // Ensure correct package name

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Save a new employee or update an existing one
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Find all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Find an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Find employees by department and hire year
    public List<Employee> findByDepartmentAndHireDateYear(String department, int year) {
        try {
            return employeeRepository.findByDepartmentAndHireDateYear(department, year);
        } catch (Exception e) {

            e.printStackTrace();
            return List.of();
        }
    }

    // Delete an employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
