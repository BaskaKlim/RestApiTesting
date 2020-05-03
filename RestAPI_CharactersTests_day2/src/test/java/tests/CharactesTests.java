
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

public class CharactesTests {

    @BeforeAll
    static void config() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "/characters";    // endpoint

    }

    //TODO: autentifikacia: nie som identifikovana, nepusti ma, lebo nemam meno heslo

    @Test
    void itShouldReturnErrorMessageWhenUserIsNotAuthenticated() {
        when().get()
                .then().statusCode(401)
                .and().body("message", equalTo("Sorry Wizard, can't let you in."));
    }
}

