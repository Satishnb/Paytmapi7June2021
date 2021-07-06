package org.testing.utilities;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import io.restassured.response.Response;

// extract any field from response using ORG.JSON
// it required Response and parameter that u want to extract
public class JsonParsingUsingOrgJSON {
	
	

	public static HashSet<String> extractJson(String rs, String fetchParameter) {
		
		//Our Response staring With Object notation so we start with JSONObject
	   // take a JSONObject and pass the response. 
				
		JSONObject ob1= new JSONObject(rs);
		
		// Pass the Object to 2nd child object notation
		JSONObject ob2 = ob1.getJSONObject("mappedMerchants");
		// OR   JSONObject ob2 = new JSONObject(ob1.get("mappedMerchants").toString());
				
        // now pass the object to child JSONArray
		JSONArray jsonarray= ob2.getJSONArray("merchants");
		// OR   JSONArray jsonarray= new JSONArray(ob2.get("merchants").toString());
		
		
		//If we have multi object notations in Array notation then use the loop		
		System.out.println();
		int length= jsonarray.length();
		System.out.println("Below are the mids: ");
		System.out.println();
        
		HashSet<String> allmIds= new  HashSet<String>();
				for(int i=0;i<length;i++ ) {
					// Now we know our merchantid field in Object notation so pass the response into object notation from Array notation
					JSONObject ob = jsonarray.getJSONObject(i) ;
					
					// or first convert the array notation to object and then go to JSONObject notation using below code
					//Object obc = jsonarray.get(i);
					//JSONObject tt= new JSONObject(obc);
					//System.out.println(tt.get(fetchParameter));

					// now we will use a method getJSONObjcet() method to fetch the value from object notation for a particular field				
					System.out.println(ob.get(fetchParameter));
					
					// stroing all mids in a hashset
					allmIds.add((ob.get(fetchParameter).toString()));
					
		
	}
				return allmIds;

}
}

