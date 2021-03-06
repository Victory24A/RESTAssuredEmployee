package com.employeeapi.testCases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TC005_DELETE_Employee_Record extends TestBase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("******************* Started TC005_DELETE_Employee_Record *******************");
		RestAssured.baseURI = 	"http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		response 			=	httpRequest.request(Method.GET,"/employees");
			
		Thread.sleep(5000);
		String responseBodyAllE 	=	response.getBody().asString();
		logger.info("Response Body containing All Employees ==>" + responseBodyAllE);
		//First get the JSONpath Object instance from the response interface
		JsonPath jsonPathEvaluator	=	response.jsonPath();
		
		//Capture ID
		String empIDToDelete	=	jsonPathEvaluator.get("{0}.id");
		logger.info("Employee ID Selected for DELETE is ==>" + empIDToDelete);
		response 			=	httpRequest.request(Method.DELETE,"/delete/"+empIDToDelete);
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("******************* Checking Response Body *******************");
		String responseBody 	=	response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
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
	void checkResponseTime()
	{
		logger.info("******************* Checking Response Time *******************");
		long responseTime 	=	response.getTime();
		logger.info("Response Time is ==>" + responseTime);
		if (responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime<10000);
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
	void checkContentType()
	{
		logger.info("******************* Checking Content Type *******************");
		String contentType 	=	response.header("Content-Type");
		logger.info("Header Content Type is ==>" + contentType);
		Assert.assertEquals("application/json;charset=utf-8", contentType);
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
	
	@Test
	void checkContentlength()
	{
		logger.info("******************* Checking Content Length *******************");
		String contentLength 	=	response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);
		if (Integer.parseInt(contentLength)<100)
			logger.warn("Content Lenght is less than 100");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	void checkCookies()
	{
		logger.info("******************* Checking Cookies *******************");
		String cookie 	=	response.getCookie("PHPSESSID");
		logger.info("Content Enconding is ==>" + cookie);
//		Assert.assertEquals("b195fae316582c8f4d9abcc05d129b21", cookie);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("******************* Finished TC005_DELETE_Employee *******************");
	}
	
	
}
