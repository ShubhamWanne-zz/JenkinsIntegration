package com.securonix.MockitoTesting;

import com.securonix.MockitoTesting.controller.EmployeeController;
import com.securonix.MockitoTesting.dao.EmployeeDAO;
import com.securonix.MockitoTesting.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTests {
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeDAO employeeDAO;

    @Test
    public void testAddEmployee(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Employee employee = new Employee();
        employee.setId("1");
        when(employeeDAO.addEmployee(any(Employee.class))).thenReturn(employee);

        Employee employeeToAdd = new Employee();
        employeeToAdd.setName("Shubham");
        employeeToAdd.setAge("26");

        ResponseEntity<Object> response = employeeController.addEmployee(employeeToAdd);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/1");

    }

    @Test
    public void testFindAll(){
        List<Employee> employees = Arrays.asList(new Employee("1", "Shubham", "26"),
                                                new Employee("2", "Arun", "56"));
        when(employeeDAO.getAllEmployees()).thenReturn(employees);
        List<Employee> resultantEmployees = employeeController.getAllEmployees();
        assertThat(resultantEmployees.size()).isEqualTo(2);
        assertThat(resultantEmployees.get(1).getId()).isEqualTo(employees.get(1).getId());
        assertThat(resultantEmployees.get(1)).isInstanceOf(Employee.class);
    }

    @Test
    public void findEmployeeWithId(){
        Employee employee = new Employee("1", "Shubham", "26");
        when(employeeDAO.getEmployee(anyString())).thenReturn(employee);

        Employee result = employeeController.getEmployee("1");

        assertThat(result.getId()).isEqualTo(employee.getId());
        assertThat(result.getName()).isEqualTo(employee.getName());
        assertThat(result.getAge()).isEqualTo(employee.getAge());

    }
}
