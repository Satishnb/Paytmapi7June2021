package org.testing.TestScripts;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

public class TC6_OutletSearch extends extentReportWithTestng  {
	
	@Test
	public static void searchOutlet() throws IOException {
		test=extent.createTest("TC6_Search Outlet using Autosuggest");
		Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
		HTTPMethods http = new HTTPMethods(pr);
		
		log4j.info("Read test data from excel sheet and store in a Map");
		List<Map<String, Object>> params = ExcelDataProvider.testdata2("../paytm_api/excel/test1.xlsx", "TC6_testdata(2)");
		
		log4j.info("Setting Headers in Request Header using external excel file");
		Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
		
		for(Map<String, Object> param: params) {
			Response rs= http.getRequest("Search_Outlet", param, headers);
	        System.out.println("TC6_Search Outlet using Autosuggest - Response data is : "+rs.asString());
			

		}
		
		/*log4j.info("Validating and printing the status code of get request");
	    StatusCodeValidation.StatusCodeMatch(rs, 200);
        System.out.println("TC6_Search Outlet using Autosuggest - Status Code is : "+ rs.statusCode());
        
        log4j.info("Printing the Response data");
        System.out.println("TC6_Search Outlet using Autosuggest - Response data is : "+rs.asString());
        
        log4j.info("Comparing the Actual Response data with expected");
        String expectedResponse=JsonFileHandling.ReadJson("../paytm_api/src/test/java/org/testing/resaurces/TC6_ExpectedResponse.json");
        CompleteResponseComparision.responseMatch(rs, expectedResponse);
        log4j.endTestCase("End Of TC6_Search Outlet using Autosuggest"); */
				
		
	}

}
