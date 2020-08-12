package com.gautam.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//import com.gautam.service.EmployeeManagementService;
//import com.gautam.service.EmployeeManagementServiceImpl;

@Configuration 
@Import(DBConfig.class)
@ComponentScan(basePackages = {"com.gautam"})
public class Config {
	
//	@Bean("empService")
//	public EmployeeManagementService getEmployeeService() {
//		System.out.println("hi...");
//		return new EmployeeManagementServiceImpl();
//	}
	
}
