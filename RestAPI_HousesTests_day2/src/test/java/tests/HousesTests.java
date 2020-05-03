
package tests;

import java.util.*;
import org.hamcrest.collection.*;
import org.junit.jupiter.api.*;

import com.github.javafaker.*;
import exceptions.*;
import io.restassured.*;
import io.restassured.http.*;
import models.*;

import static io.restassured.RestAssured.*;
import static java.util.stream.Collectors.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HousesTests {

    @BeforeAll
    static void config() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

    }

    //TODO: autentifikacia: nie som identifikovana, nepusti ma, lebo nemam token

    @Test
    void itShouldReturnErrorMessageWithoutToken() {
        when().get("/houses")
                .then().statusCode(403)
                .and().body("message", equalTo("Sorry Wizard you dont have TOKEN"));
    }

    @Test
    void itShouldReturnHousesWhenUserHasToken() {
        //1 request  - ziskam token
        String token = given().auth().preemptive().basic("admin", "supersecret")
                .when().get("/login")
                .then().extract().jsonPath().get("token");

        System.out.println(token);

        //2 request - pouzijem token a dotiahnem houses
            //natvrdo zadavam v hlavicke Bearer roken
        given().header("Authorization", "Bearer ".concat(token))
                .when().get("houses")
                .then().statusCode(200);
    }
}

