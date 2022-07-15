package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class delete01 extends JsonPlaceHolderBaseUrl {
    /*
Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }



     */
@Test

    public void delete01(){
    //1-Set the url
    spec.pathParams("first","todos","second",198);

    //2-Step the expected data
    Map<String,Object>expectedMap=new HashMap<>();

    //3-send Delete request and get the response
    Response response=given().spec(spec).when().delete("/{first}/{second}");
    response.prettyPrint();

    //4-Do assertions
    //1.way:
    Map<String,Object>actualMap=response.as(HashMap.class);
    response.then().assertThat().statusCode(200);
    assertEquals(expectedMap,actualMap);
    //2.way:
    response.then().assertThat().statusCode(200);
    assertTrue(actualMap.size()==0);//OR
    assertTrue(actualMap.isEmpty());//Recommended




}
}
