package com.escacorp.personapi.restassured;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class PersonDataGenerator {

	public static JSONObject generatePersonJSONObject(int id, String lastname, String firstname, int age) {
		JSONObject json = new JSONObject();
		json.put("age", id);
		json.put("firstname", firstname);
		json.put("id", id);
		json.put("lastname", lastname);
		return json;
	}
	
	public static Map<String, String> generatePersonMapObject(int id, String lastname, String firstname, int age) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("age", String.valueOf(age));
		map.put("firstname", firstname);
		map.put("id", String.valueOf(id));
		map.put("lastname", lastname);
		return map;
	}
}
