package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRerquest;
	public static Response response;
	public String empID =	"22";
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		logger = Logger.getLogger("EmployeeRestAPI"); 	//added Logger
		org.apache.log4j.PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		
		
	}
}
