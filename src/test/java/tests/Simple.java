package tests;

import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class Simple {
	
	
	@Test
	public void simpleAPI()
	{
		
		Response res=
					given()
					.contentType(ContentType.JSON)
					.when()
					.get("https://reqres.in/api/users");
			System.out.println(res.getStatusCode());

		
	}

}
