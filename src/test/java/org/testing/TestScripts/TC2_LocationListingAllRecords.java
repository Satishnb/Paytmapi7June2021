package org.testing.TestScripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;
import org.testing.utilities.ExcelDataProvider;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.JsonParsingUsingJSONPath;
import org.testing.utilities.JsonParsingUsingOrgJSON;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

import ResponseValidation.CompleteResponseComparision;
import ResponseValidation.MerchantServiceResponseValidation;
import ResponseValidation.StatusCodeValidation;
import io.restassured.response.Response;

public class TC2_LocationListingAllRecords extends extentReportWithTestng {
	

	    @Test()
		public void loctionlisting() throws IOException {
	    	test=extent.createTest("TC2_LocationListingAllRecords");
		    log4j.startTestCase("Start TC2_Validating - Location Listing");
		    
			log4j.info("Load properties file first");
			Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
	       
			log4j.info("Setting query params in request body");
			HTTPMethods http = new HTTPMethods(pr);
			
			log4j.info("Read data from excel sheet and store in a Map");
			Map<String, Object> params = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "TC2_testdata");
			
			log4j.info(" overrite the specifix key value as getting double from excel sheet but we need as int");
			params.put("count", 50);
			            
			log4j.info("Setting Headers in Request Header using external excel file");
			Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
		 
			log4j.info("Calling the http getMerchantListing method and getting response");
	        Response rs= http.getRequest("Location_Listing_1",params,headers);
	        
			log4j.info("Validating and printing the status code of get request");
	        System.out.println("TC2_LocationListingAllRecords - Status Code is : "+ rs.statusCode());
		    StatusCodeValidation.StatusCodeMatch(rs, 200);
	       
	        log4j.info("Parsing the Response - Fetching and printing totalmerchantcount and collection name from respinse");
	        String totalMerchantCount= JsonParsingUsingJSONPath.parseJSON(rs,"mappedMerchants.offersTotalCount");
	        String collectionName= JsonParsingUsingJSONPath.parseJSON(rs,"mappedMerchants.locationTitle");
	        System.out.println("User clicked on the location: "+ collectionName );
		    System.out.println("Total Merchant count in Location Listing: "+ totalMerchantCount );
		    
	        log4j.info("Parsing the Response - Fetching all mids from response");
		    Set<String> mids = new HashSet<String>();
	        mids=JsonParsingUsingOrgJSON.extractJson(rs.asString(), "merchantId");
	        
		    log4j.info("Calling Merchant service request");
			List<Response> merchantServiceResponse=MerchantSeriveReq.MSMultiRequest(pr, mids);
			
			log4j.info("Validating Merchant Listing Response with Merchant Service Data");
	 		MerchantServiceResponseValidation.ListingDataValidation(rs, merchantServiceResponse);
		    
		    log4j.info("Printing the Response data");
	        System.out.println("TC2_LocationListingAllRecords - Response data is : "+rs.asString());
	        	        
	        log4j.endTestCase("End Of TC2_Validating - Location Listing"); 
			
		}

}
