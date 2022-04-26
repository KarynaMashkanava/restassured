package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class BaseTest {
    public RequestSpecification setRequestSpecs() {
        return new RequestSpecBuilder().setBaseUri("https://reqres.in").setContentType(ContentType.JSON).build();
    }

    public ResponseSpecification setResponseSpecs() {
        return new ResponseSpecBuilder().expectResponseTime(Matchers.lessThan(10000L)).build();
    }
}
