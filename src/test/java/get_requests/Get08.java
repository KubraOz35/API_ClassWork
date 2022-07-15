package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {


    /*
 Given
            https://jsonplaceholder.typicode.com/todos/2
        When
	  		I send GET Request to the URL
	    Then
	  		Status code is 200
	  		And "completed" is false
	  		And "userId" is 1
	  		And "title" is "quis ut nam facilis et officia qui"
	  		And header "Via" is "1.1 Vegur"
	  		And header "Server" is "cloudflare"
	  		{
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get01() {

        //1-Set the url
        spec.pathParams("first", "todos", "second", 2);

        //2-Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");

        //3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4- Do assertions

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));

    }

    @Test
    public void get02(){

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 2);

        //2.Step:Set the Expected Data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();//I created object to access the method which is non-static
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(1, "quis ut nam facilis et officia qui", false);//I used the method with 3 parameters it returned in a Map

        expectedDataMap.put("StatusCode", 200);//I added other data into map(These are headers.i m putting headers to response body.Headers will use rarely==>
        expectedDataMap.put("Via", "1.1 vegur");//==> because of that we don't put in response body.If you believe you will use this header part many times,you should put in response body
        expectedDataMap.put("Server", "cloudflare");//I added other data into map

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do Assertions
        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"), response.getHeader("Server"));
    }


}
