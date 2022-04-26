package tests;

import io.restassured.RestAssured;
import models.UserRegisterModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void registerTest() {
        UserRegisterModel user = UserRegisterModel.builder()
                                    .email("eve.holt@reqres.in")
                                    .password("Test1234!")
                                    .build();

        Integer id = RestAssured.given(setRequestSpecs())
                .when()
                .body(user)
                .post("/api/register")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("id");

        Assert.assertNotNull(id);
    }
}
