package day05;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExtractPractice {
    /*
     extract() method of RestAssured
     enable you to extract data after validation
     in then section of the method chaining
     */
    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.90.101.103:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {

        reset();
    }

    @DisplayName("Testing GET /api/spartans/search with Basic auth")
    @Test
    public void testSearchAndExtractData() {

        // search for nameContains : a , gender Female
        // verify status code is 200
        // extract jsonPath object after validation
        // use that jsonPath object to get the list of all results
        // and get the numberOfElements field value
        // compare those 2


        JsonPath jp = given()
                            .log().all()
                            .auth().basic("admin", "admin")
                            .queryParam("nameContains", "a")
                            .queryParam("gender", "Female").
                      when()
                            .get("/spartans/search").
                      then()
                            .log().all()
                            .assertThat()
                            .statusCode(is(200))
                            .extract()
                            .jsonPath();

        // get the list of all names in String

        List<String> allNames = jp.getList("content.name");
        System.out.println("allNames = " + allNames);
        // we are getting numberOfElements field from json result
        // since it's a top level key , json path will be just numberOfElements
        int numOfElements = jp.getInt("numberOfElements");
        System.out.println("numOfElements = " + numOfElements);
        // verifying the numOfElements match the size of list we got
        // assertThat(numOfElements , equalTo( allNames.size() )    );
        assertThat(allNames.size()  , equalTo( numOfElements )    );

        // using hamcrest matcher collection support for asserting the list size
        assertThat( allNames , hasSize(numOfElements) );

    }
}