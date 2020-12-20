package day05;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
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
    public void testSearchAndExtractData(){

















}
