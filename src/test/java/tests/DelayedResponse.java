package tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class DelayedResponse extends BaseTest {
    @Test
    public void delayedResponseTest() {
        RestAssured.given(setRequestSpecs(), setResponseSpecs())
                .get("/api/users?delay=5")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
