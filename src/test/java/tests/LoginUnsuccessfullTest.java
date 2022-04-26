package tests;

import io.restassured.RestAssured;
import models.UserRegisterModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUnsuccessfullTest extends BaseTest {

    @Test(dataProvider = "getUserData")
    public void loginUnsuccessfullTest(UserRegisterModel user, String expectedError) {
        UserRegisterModel correctUser = UserRegisterModel.builder().email("eve.holt@reqres.in").password("Test1234!").build();

        RestAssured.given(setRequestSpecs())
                .when()
                .body(correctUser)
                .post("/api/register")
                .then()
                .statusCode(HttpStatus.SC_OK);

        String error = RestAssured.given(setRequestSpecs())
                .when()
                .body(user)
                .when()
                .post("/api/login")
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
                {UserRegisterModel.builder().email("eve.holt@reqres.in").password("Test234!").build(), "Missing password"}, //bug here?
                {UserRegisterModel.builder().email("ev.holt@reqres.in").password("Test1234!").build(), "user not found"},
                {UserRegisterModel.builder().password("Test1234!").build(), "Missing email or username"}
        };
    }
}
