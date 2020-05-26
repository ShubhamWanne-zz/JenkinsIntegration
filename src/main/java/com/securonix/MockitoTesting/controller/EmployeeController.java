package com.securonix.MockitoTesting.controller;

import com.securonix.MockitoTesting.dao.EmployeeDAO;
import com.securonix.MockitoTesting.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeDAO employeeDAO;

    @GetMapping(path = "/", produces = "application/json")
    public List<Employee> getAllEmployees(){
        return  employeeDAO.getAllEmployees();
    }

    @PostMapping(path = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        Employee result = employeeDAO.addEmployee(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{empID}", produces = "application/json")
    public Employee getEmployee(@PathVariable String empID){
        return employeeDAO.getEmployee(empID);
    }
}
