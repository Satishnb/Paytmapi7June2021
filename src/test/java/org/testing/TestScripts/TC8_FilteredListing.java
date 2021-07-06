package org.testing.TestScripts;

import org.testng.annotations.Test;
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

import ResponseValidation.CompleteResponseComparision;
import ResponseValidation.MerchantServiceResponseValidation;
import ResponseValidation.StatusCodeValidation;
import io.restassured.response.Response;

public class TC8_FilteredListing extends extentReportWithTestng  {
	@Test
	public static void defaultFilteredData() throws IOException {
        test=extent.createTest("TC8_Validating -Filtered Merchant Listing");

		log4j.startTestCase("Start TC8_Validating -Filtered Merchant Listing");		
		log4j.info("Properties file loading");	
		Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
		
		HTTPMethods http = new HTTPMethods(pr);
		
		log4j.info("Setting Query params in Request Header");
		Map<String, Object> params = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "TC8_testdata");
		
		log4j.info(" overrite the specifix key value as getting double from excel sheet but we need as int");
		params.put("count", 50);
		
		log4j.info("Setting Headers in Request Header using external excel file");
		Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
    	
    	log4j.info("Getting Response after calling the http get request");
        Response rs= http.getRequest("Merchant_Filtered_Listing_1",params,headers);
        
        log4j.info("Parsing the Response - Geeting mids from response and Storing in a HashSet");
        HashSet<String> mids = new HashSet<String>();
        mids=JsonParsingUsingOrgJSON.extractJson(rs.asString(), "merchantId");
        
        log4j.info("Calling Merchant service request");
		List<Response> merchantServiceResponse=MerchantSeriveReq.MSMultiRequest(pr, mids);
		
		log4j.info("Validating Merchant Listing Response with Merchant Service Data");
 		MerchantServiceResponseValidation.ListingDataValidation(rs, merchantServiceResponse);
        
		log4j.info("Validating and printing the status code of get request");
        StatusCodeValidation.StatusCodeMatch(rs, 200);
        System.out.println("TC8_Validating -Filtered Merchant Listing - Status Code is: "+ rs.statusCode());

		log4j.info("Printing the Response data");
        System.out.println("TC8_Validating -Filtered Merchant Listing - Response data is : "+rs.asString());
        
        /*log4j.info("Comparing the Actual Response data with expected");
        String expectedResponse=JsonFileHandling.ReadJson("../paytm_api/src/test/java/org/testing/resaurces/TC8_ExpectedResponse.json");
        CompleteResponseComparision.responseMatch(rs, expectedResponse);*/

        log4j.endTestCase(" End Of TC8_Validating -Filtered Merchant Listing"); 		
		
	}
	

}
