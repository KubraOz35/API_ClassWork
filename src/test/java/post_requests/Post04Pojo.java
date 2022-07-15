package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post04Pojo extends JsonPlaceHolderBaseUrl {
/*
Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }





 */
@Test
    public void postPojo01(){
  //1-set the url
  spec.pathParam("first","todos");
  //2.step
    JsonPlaceHolderPojo requestBody= new JsonPlaceHolderPojo(55,"Tidy your room",false);

  //3-send Post request and get respond
  Response response= given().
          spec(spec).
          contentType(ContentType.JSON).
          body(requestBody).
          when().
          post("/{first}");

  //4-Do assertions

    JsonPlaceHolderPojo actualBody = response.as(JsonPlaceHolderPojo.class);

    assertEquals(requestBody.getUserId(),actualBody.getUserId());
    assertEquals(requestBody.getCompleted(),actualBody.getCompleted());
    assertEquals(requestBody.getTitle(),actualBody.getTitle());






}

}
