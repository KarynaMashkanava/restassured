package tests;

import io.restassured.RestAssured;
import models.UserRegisterModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSuccessTest extends BaseTest {

    @Test
    public void loginTest() {
        UserRegisterModel user = UserRegisterModel.builder()
                .email("eve.holt@reqres.in")
                .password("Test1234!")
                .build();

        RestAssured.given(setRequestSpecs())
                .when()
                .body(user)
                .post("/api/register")
                .then()
                .statusCode(HttpStatus.SC_OK);

        String token = RestAssured.given(setRequestSpecs())
                .when()
                .body(user)
                .when()
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("token");

        Assert.assertNotEquals(token, "");
    }
}
