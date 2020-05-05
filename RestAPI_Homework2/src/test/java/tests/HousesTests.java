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
        basePath = "/houses";
    }

    //TODO: Skúste navhrnúť testy na endpoint Houses, postupujte podľa dokumentácie, nájdete ju tu https://documenter.getpostman.com/view/6199862/SzYewFs9?version=latest
    @Test
    void itShouldReturnStatusCode403AfterGETRequestWithoutAuthorisation() {
        //1. overim si, ze ci ked dam get request prejde mi poziadavka
        when().get()
                .then().statusCode(403)
                .and().body("message", equalTo("Sorry Wizard you dont have TOKEN"));
        //2. zistim, ze nie, a ze hlasi error 403 - forbidden pristup, potrebujem sa autorizovat

    }
    
}
