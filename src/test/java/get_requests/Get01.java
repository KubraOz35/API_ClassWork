package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

     /*
    1) Postman is used for manuel API testing.
    2) We use Rest-Assured Library for API Automation Testing.
    3) To type Automation scripts follow the given steps
          a) Understand the requirement
          b) Type our test cases
               i) To type our test cases we will use 'Gherkin Language'
                  'Gherkin Language' has some keywords to type test cases
                   The keywords are x)Given:It is used for pre-requisites
                                    y)When: It is for actions.
                                    z)Then: It is for outputs.
                                    t)And: It is for multiple given or when or then.

         c) Start to type Automation Scripts
            i) Set the URL
            ii)Set the expected the data(POST-PUT-PATCH)
            iii) Type the code to send request
            iv) Do Assertions/mean you will compare expected data with the response,they must match)
     */


   /* At the interview they should ask : Type the test case by using Gherkin Language.We need  able to write below test cases!!!

    Given
        https://restful-booker.herokuapp.com/booking/3
    When
        User send a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK

 */


    @Test
                                             //when you use "test" anotation,method will be test method.Test method doesn't main method

    public void get01() {                    //test method's return type MUST BE "void"


        //  i) Set the URL
        String url = " https://restful-booker.herokuapp.com/booking/3 ";

        //  ii)Set the expected the data(POST-PUT-PATCH)


        //  iii) Type the code to send request                            // send request everytime start with "given",after given=>when ,then =>action
        Response response = given().when().get(url);                      // after "when" we need to action,get,post,put,patch ..this example we will use get
                                                                          // response is a class in java that stores responses
        response.prettyPrint();                                           // Pay Attention: normally we are using sout()for printing but responses printed by this prettyprint() method


        //  iv) Do Assertions
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //How to print 'Status Code' on the console
        System.out.println("status code :" + response.getStatusCode());

        //How to print 'Content Type;
        System.out.println("Content type : " + response.getContentType());

        //How to print 'Status Line'
        System.out.println("Status Line : " + response.getStatusLine());

        //How to print 'Header
        System.out.println(response.getHeader("Connection"));

        //How to print 'Headers' on the console
        System.out.println(response.getHeaders());

        //How to print 'time' on the console
        System.out.println("Time : " + response.getTime());













    }


}
