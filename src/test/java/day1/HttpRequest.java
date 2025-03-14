package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class HttpRequest {

	int id; 
	
	@Ignore
	@Test
	public void getUser() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.body("page", equalTo(1))
			.log().all();
	}
	
	@Test(priority = 1)
	public void postUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "John");
		data.put("job", "Manager");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			//.jsonPath().getInt("id");
		
		.then()
			.statusCode(201)
			.body("name", equalTo("John"))
			.log().all();
	}
	
	@Test(priority=2, dependsOnMethods = {"postUser"})
	public void putUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "John");
		data.put("job", "Mentor");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=3)
	public void deleteUser() {
		
		when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
}
