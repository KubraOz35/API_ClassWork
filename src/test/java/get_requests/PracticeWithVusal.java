package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.ResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PracticeWithVusal  extends HerOkuAppBaseUrl {
     /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }

             }
        When
          I send POST Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                  "bookingid": 16,
                                  "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }}

                                     }*/


    @Test
    public void get05(){
        //set ther url
        spec.pathParam("first","booking");
        //set the expected data
        BookingDatesPojo bookingDatesPojo= new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo bookingPojo=new BookingPojo("ali","can",999,true,bookingDatesPojo,"breakfast");
        //response body
        Response response=given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");
        //actual data
        ResponseBodyPojo actualPojo= response.as(ResponseBodyPojo.class);
        assertEquals(200,response.getStatusCode());


    }




}
