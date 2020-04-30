package tests;

import java.util.*;
import org.junit.jupiter.api.*;
import io.restassured.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpellTests {

    @BeforeAll
    static void config() {
        baseURI = "http://localhost";
        port = 3000;
        basePath = "/spells";
    }

    @Test
    /** SMOKE REST ASURED TEST - testujem endpoint Spells ci odpovie na request - code200 a ci obsahuje konkretny object spell **/
    void itShouldReturnSpecificSpell() {
        //arrange - given     dana url
        //act   - when    zavolam GET REQUEST
        //assert   - then     statuscode bude 200
        given().pathParam("spellId", "5b74ef813228320021ab624c")
                .when().get("/{spellId}")
                .then().statusCode(200)
                .body("spell", equalTo("Crucio"))
                .body("type", equalTo("Curse"))
                .body("effect", equalTo("tortures a person"))
                .body("isUnforgivable", is(true));

    }

    @Test
    void itShouldReturnErrorMessageSpellNotFound() {
        given().pathParam("spellId", "invalid")
                .when().get("/{spellId}")
                .then().body("message", equalTo("Spell not found"))
                .statusCode(404);

    }

    //**deserializacia z jsonu na hasmapu alebo objekt**//
    @Test
    void itShouldReturnSpellForEachSpellInList() {
        List<HashMap<Object, Object>> spells =
                when().get()
                        .then().extract().response()
                        .jsonPath().getList("$");

        
        spells.forEach(spell -> System.out.println(spell.get("effect")));

    }

}