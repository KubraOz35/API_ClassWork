package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class patch01 extends JsonPlaceHolderBaseUrl {
    /*

    Given
	        https://jsonplaceholder.typicode.com/todos/198
	        {
                "title": "Wash the dishes"
            }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
		// i have 3 response but i will update just one:title,that's why partially update:patch request
     */

@Test
public void patch01(){

    //1-Set the Url
    spec.pathParams("first","todos","second",198);

    //2-Set the request body
    JsonPlaceHolderTestData requestBody= new JsonPlaceHolderTestData();//
   Map<String,Object> requestBodyMap= requestBody.expectedDataWithMissingKeys(null,"Wash the dishes",null);//i will send just title,that's why I switched to null other data
                                                                                                                              //  i created  "expectedDataWithMissingKeys" method :null yaptigimiz icin hata verir yoksa
    //3-Send the patch request and get data
    Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
    response.prettyPrint();

   //4-Do assertions
    //1.Way
    response.then().assertThat().statusCode(200).body("title",equalTo(requestBodyMap.get("title")));

    //when you do Patch assertion ,sadece neresi update ediliyorsa orayi assert yapmak yeterli,ama israrla hepsini assort et derlerse asagidaki sekilde yapiliyor:
    Map<String, Object> MapToAssertAllDetails =  requestBody.expectedDataWithAllKeys(10, "Wash the dishes", true);
    response.
            then().
            assertThat().
            statusCode(200).
            body("title", equalTo(MapToAssertAllDetails.get("title")),
            "userId", equalTo(MapToAssertAllDetails.get("userId")),
            "completed", equalTo(MapToAssertAllDetails.get("completed")));



}





}
