package org.testing.ExtentReporting;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class extentReportWithTestng {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setup() {
		// will create the extent report in html format
    htmlReporter = new ExtentHtmlReporter("extent.html");
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		// will figure out the result after every method and comapre
		if(result.getStatus()==ITestResult.FAILURE) {
			test.fail(MarkupHelper.createLabel(result.getName()+"Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
	
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName()+"Test Case Passes", ExtentColor.GREEN));
			
		}
		
		else {
			test.skip(MarkupHelper.createLabel(result.getName()+"Test Case Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());

			
		}
		
	}
	
	
	@AfterMethod
	@AfterSuite
	public void tearDown() {
		extent.flush();
		
	}

}
