package org.testing.TestSteps;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class HTTPMethods {
	
	
	Properties pr;
	
	public HTTPMethods(Properties pr) {
		this.pr=pr;
	}
	
	
	
	public Response getRequest(String uriKey, Map<String, Object> reqParams, Map<String, Object> reqHeaders) {
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
	
	public Response postRequest(String body,String uriKey) {
		// used POST instead of Get to fetch the merchant detail
		Response rs=
				
				given()
				.contentType(ContentType.JSON)
				.body(body)
				.when()
				.post(pr.getProperty(uriKey));
				 return rs;
		
	            }
	
	public Response getRequestNew(String uri, Map<String, Object> reqParams, Map<String, Object> reqHeaders) {
		Response rs=
				given()
				.contentType(ContentType.JSON)
	            .headers(reqHeaders)
	            .params(reqParams)
	            .when()
				.get(uri);
		         return rs;
	            }
	
	public Response getRequestSingleRecord(String uriKey, Map<String, Object> reqParams, Map<String, Object> reqHeaders) {
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Response getRequestdummy(String uriKey, Map<String, Object> reqParams, Map<String, String> reqHeaders) {
		String uri= pr.getProperty(uriKey);
		Response rs=
				given()
				.contentType(ContentType.JSON)
	            .headers(reqHeaders)
	            .params(reqParams)
	            .when()
				.get(uri)
		        .then()
                .statusCode(200)
                .log().body()    // will print the reponse body only on consol
       //         .log().all()   //will print response header n response body
      //        .body("jsonpath", equalsTo("expectedvalue"))    //comapring single key value in response body
      //        .body("jsonpath", hasItems("expectedvalue1","expectedvalue2","expectedvalue3"))
                .extract().response();    //will convert the response into Response type 
		       //String response= rs.asString();
		       //System.out.println("****************");
		      // System.out.println(response);
		
		         return rs;
	            }



		
}
