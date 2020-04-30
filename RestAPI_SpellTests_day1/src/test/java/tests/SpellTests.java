package tests;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpellTests {

    @Test
    /** SMOKE REST ASURED TEST - testujem endpoint Spells ci odpovie na request - code200 a ci obsahuje konkretny object spell **/
    void itShouldReturnSpecificSpell() {
        //arrange - given     dana url
        //act   - when    zavolam GET REQUEST
        //assert   - then     statuscode bude 200
        given().baseUri("http://localhost:3000/spells/5b74ef813228320021ab624c")
                .when().get()
                .then().statusCode(200)
                .body("spell",equalTo("Crucio"));

    }





}
