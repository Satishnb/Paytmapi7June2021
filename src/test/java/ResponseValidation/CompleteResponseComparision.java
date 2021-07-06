package ResponseValidation;

import java.util.HashSet;
import java.util.Map;

import org.testng.Assert;

import com.github.wnameless.json.flattener.JsonFlattener;

import io.restassured.response.Response;

public class CompleteResponseComparision {
	
	public static void responseMatch(Response rs, String expectedResponse) {
		String actualResponse= rs.asString();
		
		Map<String, Object> map = JsonFlattener.flattenAsMap(actualResponse);
		Map<String, Object> map1 = JsonFlattener.flattenAsMap(expectedResponse);
		
		//System.out.println("*******************************************");
		//System.out.println(map.toString());
		//System.out.println(map1.toString());

		Assert.assertTrue(map.equals(map1));
	}

}
