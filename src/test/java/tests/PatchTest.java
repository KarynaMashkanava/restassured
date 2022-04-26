package tests;

import io.restassured.RestAssured;
import models.UserModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PatchTest extends BaseTest {

    @Test
    public void patchUserTest() {
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

        user = UserModel.builder()
                .job("Engineer")
                .build();

        RestAssured.given(setRequestSpecs())
                .body(user)
                .when()
                .patch("/api/users/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("job", equalTo("Engineer"));;
    }
}
