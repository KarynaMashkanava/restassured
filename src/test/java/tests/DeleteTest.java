package tests;

import io.restassured.RestAssured;
import models.UserModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class DeleteTest extends BaseTest {

    @Test
    public void deleteUserTest() {
        UserModel user = UserModel.builder()
                .name("Karyna")
                .job("Student")
                .build();

        String id = RestAssured
                .given(setRequestSpecs())
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .extract()
                .path("id");

        RestAssured.given(setRequestSpecs())
                .when()
                .delete("/api/users/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        RestAssured.given(setRequestSpecs())
                .when()
                .get("/api/users/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
