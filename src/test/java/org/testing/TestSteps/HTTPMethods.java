package org.testing.TestSteps;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class HTTPMethods {
	
	
	Properties pr;
	
	public HTTPMethods(Properties pr) {
		this.pr=pr;
	}
	
	public Response getMerchantListing(String uriKey, Map<String, String> reqParams, Map<String, String> reqHeaders) {
		String uri= pr.getProperty(uriKey);
		Response rs=
				given()
				.contentType(ContentType.JSON)
	            .headers(reqHeaders)
	            .params(reqParams)
	            .when()
				.get(uri);
		         return rs;
	            }
	
	
	public Response openMerchantDetail(String body,String uriKey) {
		// used POST instead of Get to fetch the merchant detail
		Response rs=
				
				given()
				.contentType(ContentType.JSON)
				.body(body)
				.when()
				.post(pr.getProperty(uriKey));
				 return rs;
		
	}
	
	public void openMerchantDetailtest(String body,String uriKey) {
		// used POST instead of Get to fetch the merchant detail
				Response rs=
				given()
				.contentType(ContentType.JSON)
				.body(body)
				.when()
				.post(pr.getProperty(uriKey))
		        .then()
		        .statusCode(200)
		        .log().body()
		        .extract().response();
				String response= rs.asString();
				System.out.println("****************");
				System.out.println(response);
				
		
	}
	
}
