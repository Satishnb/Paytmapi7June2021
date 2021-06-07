package org.testing.TestScripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;
import org.testing.utilities.JSONReplacement;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.JsonParsingUsingJSONPath;
import org.testing.utilities.MerchantListingResponse;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

import ResponseValidation.StatusCodeValidation;
import io.restassured.response.Response;


public class TC4_openMerchantDetailViaChaining extends extentReportWithTestng {
	
	    @Test()	
        public void merchantDetailvialisting() throws IOException {	
	        test=extent.createTest("TC4_openMerchantDetailViaChaining");
	        
		    log4j.startTestCase("Start TC4_openMerchantDetailViaChaining");
		
			log4j.info("Load properties file first");
			Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
			
			log4j.info("Calling the http getMerchantListing method and getting response for TC1");
			HTTPMethods http = new HTTPMethods(pr);
	    	Response rs= MerchantListingResponse.listingResponse();
	    
	        log4j.info("Parsing the Response - Fetching and printing the 0th position mid from response");
		    String mid= JsonParsingUsingJSONPath.parseJSON(rs, "mappedMerchants.merchants[0].merchantId");
			System.out.println("Extracted mid from TC1 Response: " + mid);

			log4j.info("Calling the http openMerchantDetail method and getting response");
     		Response res= http.openMerchantDetail("{\"merchantId\":\"" + mid + "\"}", "MDP_Via_MerchantListing");
     		
     	    log4j.info("Validating and printing the status code of get request");
		    StatusCodeValidation.StatusCodeMatch(res, 200);
	        System.out.println("TC4_openMerchantDetailViaChaining - Status Code is : "+ res.statusCode());
	        
	        log4j.info("Printing the Response data");
	        System.out.println("TC4_openMerchantDetailViaChaining - Response data is : "+res.asString());
	        log4j.endTestCase("End Of TC4_openMerchantDetailViaChaining"); 
		
	}

}
