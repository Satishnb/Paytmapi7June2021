package org.testing.TestScripts;

import org.testng.annotations.Test;

import ResponseValidation.CompleteResponseComparision;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;
import org.testing.utilities.ExcelDataProvider;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.JsonParsingUsingOrgJSON;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC5_RewardsStorefront extends extentReportWithTestng {
	
	@Test
	public static void RewardSF() throws IOException {
		test=extent.createTest("TC5_Open Rewards Storefront");
	    log4j.startTestCase("Start TC5_Validating - Rewards Storefront");
	    log4j.info("Load properties file first");
		Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
		log4j.info("Create object of Http method class and intitalize the value");
		HTTPMethods http= new HTTPMethods(pr);
		
		log4j.info("Read data from excel sheet and store in a Map");
		Map<String, Object> params = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "TC5_testdata");
		
		log4j.info(" overrite the specifix key value as getting double from excel sheet but we need as int");
		params.put("noOfSections", 50);
		
		log4j.info("Setting Headers in Request Header using external excel file");
		Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
		
		log4j.info("\"Calling the http getMerchantListing method and getting response\"");
		Response rs=http.getRequest("Rewards_SF", params, headers);
        
		
		log4j.info("Validating and printing the status code of get request");
		System.out.println("TC5_Validating - Rewards Storefront - Status Code is : "+rs.getStatusCode());
		log4j.info("Validating and printing the response body of get request");
		System.out.println("TC5_Validating - Rewards Storefront - Response data is : "+rs.asString());
		
		/*log4j.info("Comparing the Actual Response data with expected");
        String expectedResponse=JsonFileHandling.ReadJson("../paytm_api/src/test/java/org/testing/resaurces/TC5_ExpectedResponse.json");
        CompleteResponseComparision.responseMatch(rs, expectedResponse);*/
		log4j.endTestCase("End Of TC5_Validating - Rewards Storefront"); 
		
		
		
		
		

		
	}

}
