package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookieClass {

	@Test
	public void getCookie() {
		
		Response response = when()
			.get("https://www.google.com/");
		
		String cookie = response.getCookie("NID");
		//System.out.println("The cookie value of NID is: " +cookie);
		
		Map<String, String> cookies = response.getCookies();
		
		for(String cok : cookies.keySet()) {
			System.out.println(cok+" "+response.getCookie(cok));
		}
			
//		.then()
//			.log().cookies()
//			.cookie("AEC", "AVcja2cyC5WzYxF62ly3Ik1EqtJT6gI36kOuB8C_FWGxO9xfXtpzKoUptA");
	}
}
