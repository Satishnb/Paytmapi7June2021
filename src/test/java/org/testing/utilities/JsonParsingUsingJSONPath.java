package org.testing.utilities;

import java.util.HashSet;
import java.util.List;

import io.restassured.response.Response;

public class JsonParsingUsingJSONPath {
	
	// for parsing it needs response and Json path
	// it will return 
	public static String parseJSON(Response rs, String JSONPath) {
		return rs.jsonPath().get(JSONPath).toString();
		
	}
	
	
	
	
}