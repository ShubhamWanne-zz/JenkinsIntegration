package com.securonix.MockitoTesting.dao;

import com.securonix.MockitoTesting.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDAO {
    static List<Employee> employeesDB = new ArrayList<>();

    public EmployeeDAO() {
    }

    public Employee addEmployee(Employee emp){
        employeesDB.add(emp);
        return  emp;
    }

    public List<Employee> getAllEmployees(){
        return employeesDB;
    }

    public Employee getEmployee(String id){
        return employeesDB.stream().filter(employee -> employee.getId().equals(id)).findAny().get();
    }

}
