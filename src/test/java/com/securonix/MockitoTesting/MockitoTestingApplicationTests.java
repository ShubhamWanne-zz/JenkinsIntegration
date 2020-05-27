package com.securonix.MockitoTesting;

import com.securonix.MockitoTesting.model.Employee;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MockitoTestingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MockitoTestingApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate restTemplate;

	@BeforeEach
	public void initData(){
		Employee employee= new Employee("1", "shubham", "26");
		this.restTemplate.postForEntity("http://localhost:"+port+"/employee/", employee, Employee.class);
	}

	@Test
	public void testAllEmployees(){
		assertThat(this.restTemplate.getForObject("http://localhost:"+ port+"/employee/"+"1", Employee.class).getId()).isEqualTo("1");
	}


}
