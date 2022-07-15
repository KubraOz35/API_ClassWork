package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class post02 extends JsonPlaceHolderBaseUrl {
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
    public void post01() {

        //1-Step:Set the url

        spec.pathParam("first", "todos");

        //2-Step: Set the expected data(  ==>Set the request body-Payload)
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(55, "Tidy your room", false);


        //3-Step: send Post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //4-Step:Do assertions
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));





    }

}
