package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderClass {

	@Test
	public void validateHeader() {
		
		Response response = when()
			.get("https://reqres.in/api/users?page=2");
		
//		String header = response.getHeader("Content-Type");
//		System.out.println(header);
		
		Headers headers = response.getHeaders();
		
		for(Header head : headers) {
			
			System.out.println("Head name: " + head.getName());
			System.out.println("Head value: " +head.getValue());
		}
			
//		.then()
//			.log().all()
//			.header("Content-Type" ,"application/json; charset=utf-8")
//			.and()
//			.header("Content-Encoding","gzip");
		
		
	}
}
