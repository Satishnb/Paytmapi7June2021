package ResponseValidation;

import org.testng.Assert;

import io.restassured.response.Response;

public class StatusCodeValidation {
	
	// it needs response rs for actual status code
	// will stop the code if hard assertion is get failed
	public static void StatusCodeMatch(Response rs, int expectedStatusCode) {
		//iTS HARD ASSERTION(Assertion given by testng)
		Assert.assertEquals(rs.getStatusCode(), expectedStatusCode);
		
		// soft assertion( created by us)
		// will not stop the code if soft assertion is get failed
		
		/* if(rs.getStatusCode() == expectedStatusCode) {
			System.out.println("Status code is matching");
			
		}
		else
			System.out.println("Status code is not  matching"); */
		
		
	}

}
