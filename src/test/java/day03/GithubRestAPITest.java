package day03;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GithubRestAPITest {


        // create a test for testing github rest api users/user endpoint

        @DisplayName("Test GitHub GET /users/{username}")
        @Test
        public void testGitHub(){

            // provide your username as path variable in give section of the chain
            given()
                    .pathParam("username", "makist73").
            when()
                    .get("https://api.github.com/users/{username}").
            then()
                    .assertThat()
                    .statusCode(  is(200)  )
                    .contentType(ContentType.JSON)
                    .header("server", "GitHub.com")
                    .body("login", is("makist73") )

                    ;




        }




}
