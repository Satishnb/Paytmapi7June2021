package org.testing.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testing.TestSteps.HTTPMethods;
import org.testing.logs.log4j;

import io.restassured.response.Response;

public class MerchantListingResponse {
	
	public static Response listingResponse() throws IOException {
		log4j.info("Properties file loading");
		Properties pr= PropertiesFileHandling.ReadProperties("../paytm_api/URI.properties");
       
		HTTPMethods http = new HTTPMethods(pr);
		
		log4j.info("Setting Query params in Request Header");
		Map<String, String> params = new HashMap<String,String>();
		params.put("count","50");                                  
        params.put("vertical","LOCAL");                               
        params.put("collectionPermalink","live-on-ordering");
        params.put("contextPayload","%22collectionPermalink%22:%22live-on-ordering%22");
        params.put("lat","28.435303");                                
        params.put("lng","77.1102260000001");                               
        params.put("sortBy","NEAR_ME");
        params.put("rewardsPayMerchantsOnly","true");
        
		log4j.info("Setting Headers in Request Header");
    	Map<String, String> headers = new HashMap<String,String>();
    	headers.put("Connection", "keep-alive");
    	
		log4j.info("Getting Response after calling the http get request");
        Response rs= http.getMerchantListing("Merchant_Listing_1",params,headers);
        return rs;
	
			} 

}
