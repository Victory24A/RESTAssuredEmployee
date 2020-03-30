package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC003_POST_Employee_Record extends TestBase {
	
	String	empName	=	RestUtils.empName(); 
	String	empSal	=	RestUtils.empSal(); 
	String	empAge	=	RestUtils.empAge(); 
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("******************* Started TC003_POST_Employee_Record *******************");
		RestAssured.baseURI = 	"http://dummy.restapiexample.com/api/v1/create";
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject	requestParams	=	new JSONObject();
		logger.info("Employee Name is==> " + empName);
		logger.info("Employee Salary is==> " + empSal);
		logger.info("Employee Age is==> " + empAge);
		
		requestParams.put("name", empName);
		requestParams.put("sal", empSal);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		
		Response	response	=	httpRequest.request(Method.POST);
		
		Thread.sleep(10000);
	}
	
	
	@Test
	void checkResponseBody()
	{
		logger.info("******************* Checking Response Body *******************");
		String responseBody 	=	response.getBody().asString();
		
		logger.info("Response Body ==>" + responseBody);
		
		Assert.assertEquals(true,responseBody.contains(empName));
		Assert.assertEquals(true,responseBody.contains(empSal));
		Assert.assertEquals(true,responseBody.contains(empAge));
	}

	@Test
	void checkStatusCode()
	{
		logger.info("******************* Checking Status Code *******************");
		int statusCode 	=	response.getStatusCode();
		logger.info("Status Code is ==>" + statusCode);
		Assert.assertEquals(200, statusCode);
	}
	
	
	@Test
	void checkStatusLine()
	{
		logger.info("******************* Checking Status Line *******************");
		String statusLine 	=	response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
	}

	@Test
	void checkServerType()
	{
		logger.info("******************* Checking Server Type *******************");
		String serverType 	=	response.header("Server");
		logger.info("Server Content Type is ==>" + serverType);
		Assert.assertEquals("nginx/1.16.0", serverType);
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("******************* Checking Content Encoding *******************");
		String contentEncoding 	=	response.header("Content-Encoding");
		logger.info("Content Enconding is ==>" + contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("******************* Finished TC003_POST_Employee_Record *******************");
	}
	
	
}
