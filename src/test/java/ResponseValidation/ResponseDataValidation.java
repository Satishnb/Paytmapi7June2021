package ResponseValidation;

import io.restassured.response.Response;

public class ResponseDataValidation {
	
	// matching the first name 
	public static void responseDataMatch(Response rs, String JsonPath, String expectedData) {
		
		if(rs.jsonPath().get(JsonPath).equals(expectedData)){
			System.out.println("Actual and expected data are same");
			
		}
		else
			System.out.println("Actual and expected data are not same");
	}

}
