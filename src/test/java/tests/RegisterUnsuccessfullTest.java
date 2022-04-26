package tests;

import io.restassured.RestAssured;
import models.UserRegisterModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterUnsuccessfullTest extends BaseTest {

    @Test(dataProvider = "getUserData")
    public void registerUnsuccessfullTest(UserRegisterModel user, String expectedError) {
        String error = RestAssured.given(setRequestSpecs())
                .when()
                .body(user)
                .post("/api/register")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .extract()
                .path("error");

        Assert.assertEquals(error, expectedError);
    }

    @DataProvider(name = "getUserData")
    public Object[][] getUserData() {
        return new Object[][] {
                {UserRegisterModel.builder().email("eve.holt@reqres.in").build(), "Missing password"},
                {UserRegisterModel.builder().password("Test1234!").build(), "Missing email or username"}
        };
    }
}
