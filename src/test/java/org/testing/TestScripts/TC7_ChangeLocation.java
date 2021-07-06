package org.testing.TestScripts;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;
import org.testing.utilities.ExcelDataProvider;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

import ResponseValidation.CompleteResponseComparision;
import ResponseValidation.StatusCodeValidation;
import io.restassured.response.Response;

public class TC7_ChangeLocation extends extentReportWithTestng  {
	
	@Test
	public static void searchOutlet() throws IOException {
		test=extent.createTest("TC7_Change User Location");
		Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
		HTTPMethods http = new HTTPMethods(pr);
		
		log4j.info("Read test data from excel sheet and store in a Map");
		Map<String,Object> params = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "TC7_testdata");
		
		log4j.info("Setting Headers in Request Header using external excel file");
		Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
		
		Response rs= http.getRequest("Change_Location", params, headers);
		
		log4j.info("Validating and printing the status code of get request");
	    StatusCodeValidation.StatusCodeMatch(rs, 200);
        System.out.println("TC7_Change User Location - Status Code is : "+ rs.statusCode());
        
        log4j.info("Printing the Response data");
        System.out.println("TC7_Change User Location - Response data is : "+rs.asString());
        
        /*log4j.info("Comparing the Actual Response data with expected");
        String expectedResponse=JsonFileHandling.ReadJson("../paytm_api/src/test/java/org/testing/resaurces/TC7_ExpectedResponse.json");
        CompleteResponseComparision.responseMatch(rs, expectedResponse);*/
        
        log4j.endTestCase("End Of TC7_Change User Location"); 
				
		
	}

}
