


package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

    public class Get09 extends HerOkuAppBaseUrl {

        /*
            Given
                https://restful-booker.herokuapp.com/booking/1
            When
             I send GET Request to the url
          Then
             Response body should be like that;
             {
                 "firstname": "Eric",
                 "lastname": "Smith",
                 "totalprice": 456,
                 "depositpaid": false,
                 "bookingdates": {
                     "checkin": "2016-09-09",
                     "checkout": "2017-09-21"
                  }
                "additionalneeds"; "Breakfast"
             }
         */
        @Test
        public void get01(){

            //1.Step: Set the URL
            spec.pathParams("first", "booking", "second", 1);

            //2.Step: Set the Expected Data
            Map<String, String> bookingDatesMap = new HashMap<>();//This is inner map.Every json data needs another map.Start with inner everytime
            bookingDatesMap.put("checkin", "2018-01-01");
            bookingDatesMap.put("checkout", "2019-01-01");

            Map<String, Object> expectedDataMap = new HashMap<>();
            expectedDataMap.put("firstname", "Eric");
            expectedDataMap.put("lastname", "Smith");
            expectedDataMap.put("totalprice", 456);
            expectedDataMap.put("depositpaid", false);
            expectedDataMap.put("bookingdates", bookingDatesMap);//we refer to inner map
            expectedDataMap.put("additionalneeds", "Breakfast");
            System.out.println(expectedDataMap);

            //3.Step: Send the Request and Get the Response
            Response response  = given().spec(spec).when().get("/{first}/{second}");

            Map<String, Object> actualDataMap = response.as(HashMap.class);//converted "response" to a "map"
            System.out.println(actualDataMap);

            //Do Assertions
            //assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
            //assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
            //assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
            //assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));

            assertEquals(bookingDatesMap.get("checkin"),((Map) actualDataMap.get("bookingdates")).get("checkin"));
            assertEquals(bookingDatesMap.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

        }

    }

