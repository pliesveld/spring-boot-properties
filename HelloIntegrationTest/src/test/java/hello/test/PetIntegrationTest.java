package hello.test;

import hello.test.core.AbstractIntegrationTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetIntegrationTest extends AbstractIntegrationTest {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void givenGet_whenHittingEndpoint_should200() throws Exception {
        when()
            .get("/pet")
        .then()
            .statusCode(200);

        when()
            .get("/pet/id/1")
        .then()
            .statusCode(200)
            .body("id", is(1));

        when()
            .get("/pet/id/2")
        .then()
            .statusCode(200);
    }

    @Test
    public void givenPets_shouldBeIterable() throws Exception {
        get("/pet").then().body("$", iterableWithSize(2));
    }
}

