package org.testing.TestScripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;
import org.testing.utilities.JsonParsingUsingJSONPath;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

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
			Map<String, String> params = new HashMap<String,String>();
			
			params.put("count","50");                                  
            params.put("vertical","LOCAL");                                
            params.put("locationPermalink","vipul-plaza-suncity-gurgaon");
            params.put("lat","28.435303");                                  
            params.put("lng","77.1102260000001");                                
            params.put("sortBy","NEAR_ME");
            params.put("rewardsPayMerchantsOnly","true");
            params.put("coverage", "CITY");
            params.put("city", "gurgaon");
            
			log4j.info("Setting headers in request body");
            Map<String, String> headers = new HashMap<String,String>();
	    	headers.put("Connection", "keep-alive");
		 
			log4j.info("Calling the http getMerchantListing method and getting response");
	        Response rs= http.getMerchantListing("Location_Listing_1",params,headers);
	        
			log4j.info("Validating and printing the status code of get request");
		    StatusCodeValidation.StatusCodeMatch(rs, 200);
	        System.out.println("TC2_LocationListingAllRecords - Status Code is : "+ rs.statusCode());
	       
	        log4j.info("Parsing the Response - Fetching and printing totalmerchantcount and collection name from respinse");
	        String totalMerchantCount= JsonParsingUsingJSONPath.parseJSON(rs,"mappedMerchants.offersTotalCount");
	        String collectionName= JsonParsingUsingJSONPath.parseJSON(rs,"mappedMerchants.locationTitle");
	        System.out.println("User clicked on the location: "+ collectionName );
		    System.out.println("Total Merchant count in Location Listing: "+ totalMerchantCount );
		    
		    log4j.info("Printing the Response data");
	        System.out.println("TC2_LocationListingAllRecords - Response data is : "+rs.asString());
	        log4j.endTestCase("End Of TC2_Validating - Location Listing"); 
			
		}

}
