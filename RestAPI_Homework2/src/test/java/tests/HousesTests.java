package tests;

import java.util.*;
import org.junit.jupiter.api.*;

import io.restassured.*;
import models.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HousesTests {

    // vytiahnem si token
    private static String token;

    @BeforeAll
    static void config() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

        //TODO: HousesTest - TOKEN si vytiahnite len raz pred všetkými testami. uložte ho do static premennej v rámci triedy a prepoužite v každom teste
        token = given().auth().preemptive().basic("admin", "supersecret")
                .when().get("/login")
                .then().extract().jsonPath().get("token");

    }

    //TODO: Skúste navhrnúť testy na endpoint Houses, postupujte podľa dokumentácie, nájdete ju tu https://documenter.getpostman.com/view/6199862/SzYewFs9?version=latest
    @Test
    void itShouldReturnStatusCode403AfterGETRequestWithoutAuthorisation() {
        //1. overim si, ze ci ked dam get request prejde mi poziadavka
        when().get("/houses")
                .then().statusCode(403)
                .and().body("message", equalTo("Sorry Wizard you dont have TOKEN"));
        //2. zistim, ze nie, a ze hlasi error 403 - forbidden pristup, potrebujem sa autorizovat

    }

    @Test
    void itShouldReturnListOfAllHousesInHarryPotter() {
        //1. ziskam token - hned v uvode testovacej triedy
        //2. vytiahnem si list houses

        List<String> allHousesByName = given()
                .header("Authorization", "Bearer ".concat(token))
                .when().get("/houses")
                .then().extract().jsonPath().getList("name", String.class);

        System.out.println(allHousesByName);

    }

    @Test
    void itShouldReturnSpecificHouseById() {

        // definovane houseid Gryffindoru
        String houseid = "5a05e2b252f721a3cf2ea33f";

        //test co vytiahne vsetky houses a overi, kolko ic je
        given()
                .header("Authorization", "Bearer ".concat(token))
                .pathParam("houseId", houseid)
                .when().get("/houses/{houseId}")
                .then().statusCode(200).statusLine(containsString("OK"))
                .body("name", equalTo("Gryffindor"));

    }

    @Test
    void itShouldReturnNameForEachCharacterForGryffindor() {
        //vytiahnem si  list membrov / su uvedene ako ids
        List<String> idsOfMember = given().header("Authorization", "Bearer ".concat(token))
                .when().get("houses/5a05e2b252f721a3cf2ea33f")  //gryffindor id
                .then().extract().response().jsonPath().getList("members", String.class);

        //pre kazdy zaznam urobim konkretny kod
        idsOfMember.forEach(memberId -> {
            //nalogujem sa na kazdu url pre kazdeho membra a chechnem kod 200
            given().pathParam("id", memberId)
                    .auth()
                    .preemptive()
                    .basic("admin", "supersecret")
                    .when().get("/characters/{id}")
                    .then().statusCode(200)
                    .body("name", not(emptyOrNullString()))
                    .body("house", equalTo("Gryffindor"));

        });

    }
}