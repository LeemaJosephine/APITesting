package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndOuery {

	@Test
	public void pathAndQueryParm() {
		
		given()
			.pathParam("myPath", "user") // routing the url where it should go
			.queryParam("page", "3") // filtering the data from the server
			
		.when()
			.get("https://reqres.in/api/{myPath}")
			
		.then()
			.statusCode(200)
			.log().all();
	}
}
