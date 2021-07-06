package org.testing.TestScripts;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.testing.TestSteps.HTTPMethods;

import io.restassured.response.Response;

public class MerchantSeriveReq {
	
	private Set<String> merchantIds;
	private String merchantId;

	public Set<String> getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(Set<String> merchantIds) {
		this.merchantIds = merchantIds;
	}
	
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	public static List<Response> MSMultiRequest(Properties pr, Set<String> listingmids) {
		HTTPMethods http = new HTTPMethods(pr);
	    List<Response> merchantdetaillist= new ArrayList<Response>();
		
		for(String mid: listingmids) {
		String newURI=  MessageFormat.format(pr.getProperty("Merchant_Service_Detail"),mid);
		Response merchantresponse= http.getRequestNew(newURI, new HashMap<String, Object>(), new HashMap<String, Object>());
		merchantdetaillist.add(merchantresponse);
      }
      	
      	for(Response res: merchantdetaillist) {
      		System.out.println("Merchant Service Response data is: " + res.asString());
      	}
      	
      	return merchantdetaillist;
	}
	
	public static Response MSSingleRequest(Properties pr, String Responsemid) {
		HTTPMethods http = new HTTPMethods(pr);
		String newURI=  MessageFormat.format(pr.getProperty("Merchant_Service_Detail"),Responsemid);
		Response merchantresponse= http.getRequestNew(newURI, new HashMap<String, Object>(), new HashMap<String, Object>());
	     System.out.println("Merchant Service Response data is: " + merchantresponse.asString());
      	return merchantresponse;
      }

	
      	
      	
      	
		
	
	
}
