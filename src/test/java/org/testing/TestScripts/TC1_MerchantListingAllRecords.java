package org.testing.TestScripts;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testing.ExtentReporting.extentReportWithTestng;
import org.testing.TestSteps.HTTPMethods;
import org.testing.utilities.ExcelDataProvider;
import org.testing.utilities.JsonFileHandling;
import org.testing.utilities.JsonParsingUsingJSONPath;
import org.testing.utilities.JsonParsingUsingOrgJSON;
import org.testing.utilities.MerchantListingResponse;
import org.testing.utilities.PropertiesFileHandling;
import org.testng.annotations.Test;
import org.testing.logs.log4j;

import ResponseValidation.CompleteResponseComparision;
import ResponseValidation.MerchantServiceResponseValidation;
import ResponseValidation.ResponseData;
import ResponseValidation.StatusCodeValidation;
import io.restassured.response.Response;

public class TC1_MerchantListingAllRecords extends extentReportWithTestng {
		

	@Test
	public void merchantlisting() throws IOException {
        test=extent.createTest("TC1_Validating - Merchant Listing");
		log4j.startTestCase("Start TC1_Validating - Merchant Listing");
		
		log4j.info("Properties file loading");
		Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
		HTTPMethods http = new HTTPMethods(pr);
		
		log4j.info("Setting Query params in Request Header using external excel file ");
		Map<String, Object> params = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "TC1_testdata");
		log4j.info(" overrite the specifix key value as getting double from excel sheet but we need as int");
		params.put("count", 50);
		
		log4j.info("Setting Headers in Request Header using external excel file");
		Map<String, Object> headers = ExcelDataProvider.testdata("../paytm_api/excel/test1.xlsx", "Headers");
    	
    	log4j.info("Getting Response after calling the http get request");
        Response rs= http.getRequest("Merchant_Listing_1",params,headers);
		
		log4j.info("Parsing the Response - Fetching Total Number of Merchants and Collection Name From Response");
        String totalMerchantCount= JsonParsingUsingJSONPath.parseJSON(rs,"mappedMerchants.offersTotalCount");
        String collectionName= JsonParsingUsingJSONPath.parseJSON(rs,"mappedMerchants.collectionTitle");
       	System.out.println("User clicked on the collection name: "+ collectionName );
        System.out.println("Total Merchant count in Merchant Listing: "+ totalMerchantCount );
        
		log4j.info("Parsing the Response - Geeting mids from response and Storing in a HashSet");
        HashSet<String> mids = new HashSet<String>();
        mids=JsonParsingUsingOrgJSON.extractJson(rs.asString(), "merchantId");
             
		log4j.info("Validating the Response - Able to fetch the expected mid or not");
        ResponseData.validateResponse(mids,"1075334" );
        
		log4j.info("Calling Merchant service request");
		List<Response> merchantServiceResponse=MerchantSeriveReq.MSMultiRequest(pr, mids);
		
		log4j.info("Validating Merchant Listing Response with Merchant Service Data");
 		MerchantServiceResponseValidation.ListingDataValidation
 		(rs, merchantServiceResponse);
        
		log4j.info("Validating and printing the status code of get request");
        StatusCodeValidation.StatusCodeMatch(rs, 200);
        System.out.println("TC1_Validating - Merchant Listing - Status Code is: "+ rs.statusCode());

		log4j.info("Printing the Response data");
        System.out.println("TC1_Validating - Merchant Listing - Response data is : "+rs.asString());

        log4j.endTestCase(" End Of TC1_Validating - Merchant Listing"); 
		
	}
	}
