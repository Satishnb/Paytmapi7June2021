package org.testing.TestScripts;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.json.JSONObject;
import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;
import org.testing.utilities.JSONReplacement;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.JsonParsingUsingJSONPath;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;

import ResponseValidation.CompleteResponseComparision;
import ResponseValidation.MerchantServiceResponseValidation;
import ResponseValidation.StatusCodeValidation;
import io.restassured.response.Response;


public class TC3_openMerchantDetail extends extentReportWithTestng {
	
	
	    @Test	
        public void merchantDetail() throws IOException {	
	        test=extent.createTest("TC3_openMerchantDetail");
	        
		    log4j.startTestCase("Start TC3_openMerchantDetail");
		
			log4j.info("Load properties file first");
		    Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
		    
			log4j.info("Load/Read Json file for payload/body");
		    String bodyData= JsonFileHandling.ReadJson("../paytm_api/src/test/java/org/testing/resaurces/bodyData.json");
           
		    log4j.info("Calling the http openMerchantDetail method and getting response");
		    HTTPMethods http = new HTTPMethods(pr);
		    Response rs= http.postRequest(bodyData, "MDP_Via_MerchantListing");
		    
		    String resmid= JsonParsingUsingJSONPath.parseJSON(rs, "merchantId");
		    
		    log4j.info("Calling Merchant service request");
			Response merchantServiceResponse=MerchantSeriveReq.MSSingleRequest(pr, resmid);
			
			log4j.info("Validating Merchant Listing Response with Merchant Service Data");
	 		MerchantServiceResponseValidation.MerchantDetailDataValidation(rs, merchantServiceResponse);
		
	 		log4j.info("Printing the Response data from Merchant Deatil");
	        System.out.println("Printing the Response data from Merchant Deatil");
	        System.out.println("TC3_openMerchantDetail - Response data is : "+rs.asString());
		    
		    log4j.info("Validating and printing the status code of get request");
		    StatusCodeValidation.StatusCodeMatch(rs, 200);
	        System.out.println("TC3_openMerchantDetail - Status Code is : "+ rs.statusCode());
	        
	        log4j.endTestCase("End Of TC3_openMerchantDetail"); 
	        
		
	}
	   
}
