package day04;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;


public class OpenMovieDB_Test {

        // http://www.omdbapi.com?t=The Orville

    @BeforeAll
    public static void setUp(){
        baseURI="http://www.omdbapi.com?t=The Orville";
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test Search Movie or OpenMovieDB Test")
    @Test
    public void testMovie(){

    }
}