package day02;
// For future projects copy and past all this classes

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanTest {


    @BeforeAll
    public static void setUp(){

        // baseURI and basePath is static fields of RestAssured Class
        // Since we static imported RestAssured, we can access all static field directly just like it's in our own class here
        // you can use static way as below
        //RestAssured.baseURI = "http://100.26.101.158:8000";
        // or you can directly use as below
         baseURI = "http://100.26.101.158:8000";
        //RestAssured.basePath = "/api" ;
        basePath = "/api" ;
        // baseURI + basePath + whatever you provided in http method like get post
        // for example :
        // get("/spartans") -->>  get(baseURI + basePath + "/spartans")

    }

    @AfterAll
    public static void tearDown(){
        // resetting the value of baseURI , basePath to original value
//        RestAssured.reset();
        reset();
    }


    @DisplayName("Testing /api/spartans endpoint")
    @Test
    public void testGetAllSpartan(){

        // send a get request to above endpoint
        // save the response
        // print out the result
        // try to assert the status code
        // content type header

        Response response = get("/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is(200) ) ;
        assertThat(response.contentType() , is(ContentType.JSON.toString() ) );

    }


    @DisplayName("Testing /api/spartans endpoint XML Response")
    @Test
    public void testGetAllSpartanXML(){

        /**
         * given
         *      --- RequestSpecification
         *      used to provide additional information about the request
         *      base url  base path
         *      header , query params , path variable , payload
         *      authentication authorization
         *      logging , cookie
         * when
         *      --- This is where you actually send the request with http method
         *      -- like GET POST PUT DELETE .. with the URL
         *      -- We get Response Object after sending the request
         * then
         *      -- ValidatableResponse
         *      -- This is where we can do validation
         *      -- validate status code , header , payload , cookie
         *      -- responseTime , structure of the payload , logging response
         */
         given()
                 .header("accept" , "application/xml").
         when()
                 .get("/spartans").
         then()
//                 .assertThat()  // this is not required , but can be added to make it obvious that this is where we start assertions
                 .statusCode(200)
//                 .and() // this is not required at all , just for readability , optional
                 .header("Content-Type","application/xml" )
         ;

         // This will do same exact thing as above in slightly different way
        // since accept header and content type header is so common , RestAssured has good support or those header by providing method directly rather than using header method we used above
        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .assertThat()
                .statusCode(  is(200)  )
                .and()
                .contentType(ContentType.XML) ;


    }


}
