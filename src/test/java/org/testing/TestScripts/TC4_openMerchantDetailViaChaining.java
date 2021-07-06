package org.testing.TestScripts;

import org.testng.annotations.Test;

import com.google.gson.Gson;

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
import org.testing.utilities.ExcelDataProvider;
import org.testing.utilities.JSONReplacement;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.JsonParsingUsingJSONPath;
import org.testing.utilities.MerchantListingResponse;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

import ResponseValidation.CompleteResponseComparision;
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
			
			log4j.info("Read data from excel sheet and store in a Map");
			Map<String, Object> params = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx","TC4_testdata");
			
			log4j.info(" overrite the specifix key value as getting double from excel sheet but we need as int");
			params.put("count", 50);
			            
			log4j.info("Setting Headers in Request Header using external excel file");
			Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
	    	
	    	log4j.info("Calling the http getMerchantListing method and getting response");
	        Response rs= http.getRequest("Merchant_Listing_1",params,headers);
	        
				    
	        log4j.info("Parsing the Response - Fetching and printing the 0th position mid from response");
		    String mid= JsonParsingUsingJSONPath.parseJSON(rs, "mappedMerchants.merchants[0].merchantId");
			System.out.println("Extracted mid from TC1 Response: " + mid);
			
			 MerchantSeriveReq req= new MerchantSeriveReq();
		     req.setMerchantId(mid);
		     
		     log4j.info("convert java object to JSONObject format");
             Gson gson = new Gson();
		     System.out.println(gson.toJson(req));
		
			log4j.info("Calling the http openMerchantDetail method and getting response");
     		//Response res= http.postRequest("{\"merchantId\":\"" + mid + "\"}", "MDP_Via_MerchantListing");
			Response res= http.postRequest(gson.toJson(req), "MDP_Via_MerchantListing");
			
     		
     	    log4j.info("Validating and printing the status code of get request");
		    StatusCodeValidation.StatusCodeMatch(res, 200);
	        System.out.println("TC4_openMerchantDetailViaChaining - Status Code is : "+ res.statusCode());
	        
	        log4j.info("Printing the Response data");
	        System.out.println("TC4_openMerchantDetailViaChaining - Response data is : "+res.asString());
	        
	        /*log4j.info("Comparing the Actual Response data with expected");
	        String expectedResponse=JsonFileHandling.ReadJson("../paytm_api/src/test/java/org/testing/resaurces/TC4_ExpectedResponse.json");
	        CompleteResponseComparision.responseMatch(res, expectedResponse);*/
	        
	        log4j.endTestCase("End Of TC4_openMerchantDetailViaChaining"); 
		
	}

}
