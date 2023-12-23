import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Example {
    @Test(description = "check working allure и Jenkins")
    public void testAllureAndJenkins() {
        assertEquals(1, 1);
    }

    @Test(description = "GET DATA SINGLE USER")
    public void testListUsers() {
        baseURI = "https://reqres.in/api";
        given()
                .param("page", 2)
        .when()
                .get("/users/2")
                .then()
                .body("data.id", equalTo(2))
                .body("data.email", equalTo( "janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar",  equalTo("https://reqres.in/img/faces/2-image.jpg"))
                .statusCode(200).log().all();
    }

    @Test(description = "create user")
    public void testCreateUser() {
        baseURI = "https://reqres.in/api";

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "morpheus");
        requestParams.put("job", "leader");

        given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
        .when()
                .post("/users")
        .then()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .statusCode(201).log().all();
    }

    @Test(description = "update user with PUT")
    public void testUpdateUserPUT() {
        baseURI = "https://reqres.in/api";

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "morpheus");
        requestParams.put("job", "zion resident");

        given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
        .when()
                .put("/users/2")
        .then()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .statusCode(200).log().all();
    }

    @Test(description = "DELETE USER")
    public void testDeleteUser() {
        baseURI = "https://reqres.in/api";

        delete("/users/2")
                .then()
                .statusCode(204).log().all();
    }

}
