
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
        RestAssured.basePath = "/houses";    // endpoint

    }

    //TODO: autentifikacia: nie som identifikovana, nepusti ma, lebo nemam token

    @Test
    void itShouldReturnErrorMessageWithoutToken() {
        when().get()
                .then().statusCode(403)
                .and().body("message", equalTo("Sorry Wizard you dont have TOKEN"));
    }

    
}

