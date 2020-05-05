package tests;

import java.util.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HousesTests {

    @BeforeAll
    static void config() {
        baseURI = "http://localhost";
        port = 3000;

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
        //1. ziskam token
        String token = given().auth().preemptive().basic("admin", "supersecret")
                .when().get("/login")
                .then().extract().jsonPath().get("token");

        //2. vytiahnem si list houses

      List<String> allHousesByName=  given()
               .header("Authorization", "Bearer ".concat(token))
                .when().get("/houses")
                .then().extract().jsonPath().getList("name");

      System.out.println(allHousesByName);


    }
}
