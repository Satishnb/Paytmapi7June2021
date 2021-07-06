package ResponseValidation;

import java.util.Map;

import org.testng.Assert;
import io.restassured.response.Response;

public class ResponseDataValidation {
	
	public static void responseDataMatch(Response rs, String JsonPath, String expectedValue) {
		
		String actualValue=rs.jsonPath().get(JsonPath);
		
		//hard assertion
		Assert.assertEquals(actualValue,expectedValue);


           //soft assertion
		/*if(rs.jsonPath().get(JsonPath).equals(expectedValue)){
			System.out.println("Actual and expected data are same");
			
		}
		else
			System.out.println("Actual and expected data are not same");*/
	
	}

}
