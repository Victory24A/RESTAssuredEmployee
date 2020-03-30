package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class Listeners extends TestListenerAdapter{
	
	@SuppressWarnings("deprecation")
	public ExtentHtmlReporter 	htmlReporter;
	public ExtentReports	extent;
	public ExtentTest	test;
	
	@SuppressWarnings("deprecation")
	public void onStart(ITestContext testContext)
	{
		htmlReporter	=	new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report");	// Title of Report
		htmlReporter.config().setReportName("REST API Testing Report");	// Name of Report
		htmlReporter.config().setTheme(Theme.DARK);	// Set Theme
		
		extent	=	new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Priject name", "Employee Database API Project");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Vishal");
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test	=	extent.createTest(result.getName());	// Create new entry in test result
		test.log(Status.PASS, "The Passed TestCase is - "+result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		test	=	extent.createTest(result.getName());	// Create new entry in test result
		test.log(Status.FAIL, "The Failed TestCase is - "+result.getName());
		test.log(Status.FAIL, "The Failed TestCase is - "+result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test	=	extent.createTest(result.getName());	// Create new entry in test result
		test.log(Status.SKIP, "The Skipped TestCase is - "+result.getName());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
