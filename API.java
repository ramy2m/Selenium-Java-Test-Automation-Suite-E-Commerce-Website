package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

    // ================================
    // Test Case 1 — Create User (POST)
    // ================================
    @Test(priority = 1)
    public void testCreateUser() {

        String body = """
                {
                  "firstName": "Mohamed",
                  "lastName": "Elshaarawy",
                  "age": 26
                }
                """;

        Response res = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("https://dummyjson.com/users/add");

        // Assertions
        Assert.assertEquals(res.statusCode(), 201);
        Assert.assertEquals(res.jsonPath().getString("firstName"), "Mohamed");
        Assert.assertEquals(res.jsonPath().getString("lastName"), "Elshaarawy");
        Assert.assertTrue(res.jsonPath().getInt("id") > 0, "ID must be generated");
    }


    // ================================
    // Test Case 2 — Update User (PUT)
    // ================================
    @Test(priority = 2)
    public void testUpdateUser() {

        String body = """
                {
                  "firstName": "UpdatedName",
                  "lastName": "UpdatedLast",
                  "age": 30
                }
                """;

        Response res = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .put("https://dummyjson.com/users/1");

        // Assertions
        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("firstName"), "UpdatedName");
        Assert.assertEquals(res.jsonPath().getString("lastName"), "UpdatedLast");
        Assert.assertEquals(res.jsonPath().getInt("age"), 30);
    }
}
