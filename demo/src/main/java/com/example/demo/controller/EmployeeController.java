package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.Service.EmployeeService; // Ensure correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Test endpoint to check if the server is up and running
    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, this is a test endpoint!";
    }

    // Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Retrieve all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Retrieve an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Search employees by department and hire year
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> findByDepartmentAndHireDateYear(
            @RequestParam String department, @RequestParam int year) {
        try {
            List<Employee> employees = employeeService.findByDepartmentAndHireDateYear(department, year);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            e.printStackTrace(); // Replace with proper logging
            return ResponseEntity.status(500).body(null); // Or handle as needed
        }
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            e.printStackTrace(); // Replace with proper logging
            return ResponseEntity.status(500).build(); // Or handle as needed
        }
    }
}
