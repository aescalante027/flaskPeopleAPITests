package com.escacorp.personapi.restassured;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class PersonApiRestAssuredTests {
	
	private static final String URL = "http://127.0.0.1:5000/person";
	private RequestSpecification httpRequest;
	
	@BeforeTest
	void setUp() {
		RestAssured.baseURI = URL;
		httpRequest = RestAssured.given();
	}
	
	  @Test
	  public void testGetAllPeople() {
		  //Map testObject = PersonDataGenerator.generatePersonJSONObject(1, "Reynolds", "Ryan", 46);
		  Response response = httpRequest.request(Method.GET, "/all");
		  JSONObject jsonResponse = new JSONObject(response.jsonPath().getMap(""));
		  //List allValues = (List)jsonResponse.get("data");
		  //JSONObject data = new JSONObject(allValues.get(0));
		  assertEquals(200, response.getStatusCode());
		  assertTrue(jsonResponse.containsKey("data"));
		  //assertTrue(allValues.contains(testObject));
	  }
	  
	  @Test
	  public void testLastNameChange() {
		  Response initalResponse = httpRequest.request(Method.GET, "/3");
		  assertEquals(200, initalResponse.getStatusCode());
		  JSONObject initialJson = new JSONObject(initalResponse.jsonPath().getMap(""));
		  assertEquals("Swift", initialJson.get("lastname"));
		  assertEquals("Taylor", initialJson.get("firstname"));
		  
		  Response changeResponse = httpRequest.request(Method.PUT, "/3/lastname/Sjöberg");
		  assertEquals(200, changeResponse.getStatusCode());
		  JSONObject changeJson = new JSONObject(changeResponse.jsonPath().getMap(""));
		  assertEquals("Sjöberg", changeJson.get("lastname"));
		  assertEquals("Taylor", changeJson.get("firstname"));
		  
		  Response reverseResponse = httpRequest.request(Method.PUT, "/3/lastname/Swift");
		  assertEquals(200, reverseResponse.getStatusCode());
		  JSONObject reverseJson = new JSONObject(reverseResponse.jsonPath().getMap(""));
		  assertEquals("Swift", reverseJson.get("lastname"));
		  assertEquals("Taylor", reverseJson.get("firstname"));
	  }
	  
	  @Test
	  public void testAgeChange() {
		  Response initalResponse = httpRequest.request(Method.GET, "/5");
		  assertEquals(200, initalResponse.getStatusCode());
		  JSONObject initialJson = new JSONObject(initalResponse.jsonPath().getMap(""));
		  assertEquals(22, initialJson.get("age"));
		  
		  Response changeResponse = httpRequest.request(Method.PUT, "/5/age/99");
		  assertEquals(200, changeResponse.getStatusCode());
		  JSONObject changeJson = new JSONObject(changeResponse.jsonPath().getMap(""));
		  assertEquals(99, changeJson.get("age"));
		  
		  Response reverseResponse = httpRequest.request(Method.PUT, "/5/age/22");
		  assertEquals(200, reverseResponse.getStatusCode());
		  JSONObject reverseJson = new JSONObject(reverseResponse.jsonPath().getMap(""));
		  assertEquals(22, reverseJson.get("age"));
	  }
	  
	  @Test
	  public void testCheckInvalidId() {
		  Response response = httpRequest.request(Method.GET, "/0");
		  assertEquals(400, response.statusCode());
	  }
	  
	  @Test
	  public void testRemoveUserWithBadId() {
		  Response response = httpRequest.request(Method.DELETE, "/delete/0");
		  assertEquals(200, response.statusCode());
	  }
}
