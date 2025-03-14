package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

public class PostRequest {

	/* 4 ways to create a response body
	 * 1) using HashMap
	 * 2) using org.json
	 * 3) using POJO class (Plain Old Java Object)
	 * 4) using external json file
	 */
	
	@Test
	public void postJson() {
		
		JSONObject data = new JSONObject();
		data.put("name", "Arun");
		data.put("job", "Manager");
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("https://reqres.in/api/users")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Arun"))
			.log().all();
		
	}
	
	@Test
	public void postPojo() {
		
		PojoClass obj = new PojoClass();
		obj.setName("Leema");
		obj.setJob("Mentor");
		
		given()
			.contentType("application/json")
			.body(obj)
		
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Leema"))
			.log().all();
	}
	
	@Test
	public void postJsonFile() throws FileNotFoundException {
		
		FileReader fr = new FileReader("C:\\Users\\Digital Suppliers\\eclipse-workspace\\APITesting\\src\\test\\java\\day2\\data.json");
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Arun"))
			.log().all();
		
	}
	
	@Test
	public void postMultiple() throws StreamReadException, DatabindException, IOException {
		
		// Read Json File
		ObjectMapper obj = new ObjectMapper();
		List<Map<String,String>> readValue = obj.readValue(new File("C:\\Users\\Digital Suppliers\\eclipse-workspace\\APITesting\\src\\test\\java\\day2\\data.json"), List.class);
		
		// Iterate over each set of data
		
		for(Map<String, String> data : readValue) {
			
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("https://reqres.in/api/users")
			
			.then()
				.statusCode(201)
				.log().all();
		}
		
		
		
		
	}
}
