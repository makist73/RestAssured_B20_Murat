package day04;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {

    private static String myToken;

    @BeforeAll
    public static void setUp() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void tearDown() {

        reset();
    }

    @DisplayName("Testing POST /login Endpoint")
    @Test
    public void testLogin() {
        /*
        Librarian1  email	librarian69@library
        Librarian1  password		KNPXrm3S
         */
        myToken =
                given()
                        .log().all()
                        .contentType(ContentType.URLENC)
                        .formParam("email", "librarian69@library")
                        .formParam("password", "KNPXrm3S").
                when()
                        .post("/login").
                then()
                        .log().all()
                        .assertThat()
                        .statusCode(is(200))
                        .contentType(ContentType.JSON)
                        .body("token", is(not(emptyString())))
                    .extract()
                        .jsonPath()
                        .getString("token")
        ;
        System.out.println("myToken = \n" + myToken);
        // How to extract some data out of response object
        // after doing validation in then section
        // without breaking the chain -->> use extract() method that return
    }

    @DisplayName("Testing GET /dashboard_stats Endpoint")
    @Test
    public void testzDashboard_stats() {

//      this is how we provide header .header("headerName","headerValue")
        given()
                .log().all()
                .header("x-library-token",myToken).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
        ;

    }

    // create a utility class LibraryUtility
    // create a static method called getToken(environment, username, password)



}