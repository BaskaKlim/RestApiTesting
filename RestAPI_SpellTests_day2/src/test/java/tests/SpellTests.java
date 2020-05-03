
package tests;

import java.util.*;
import org.hamcrest.collection.*;
import org.junit.jupiter.api.*;

import com.github.javafaker.*;
import exceptions.*;
import models.*;

import static io.restassured.RestAssured.*;
import static java.util.stream.Collectors.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpellTests {

    @BeforeAll
    static void config() {
        baseURI = "http://localhost";
        port = 3000;
        basePath = "/spells";

    }

   /* @BeforeEach
    static void resetSpells() {
        // zavolam konkretny endpoint tak si vymazem a zresetujem kuzla: https://localhost:3009/spells/actions/reset
        when().get("/actions/deleteAll")
                .then().statusCode(200);

        when().get("/actions/reset")
                .then().statusCode(200);

    }
    */

    @Test
    void itShoulFilterSpellsBasedOnQueryType() {
        //1. nastavim parametre cez query a vytiahnem si list kuzel, ktory ich splna
        List<Spell> spells = given().queryParam("type", "Curse")
                .when().get()
                .then().extract().jsonPath().getList("$", Spell.class);

        // 2. overit ze kazde kuzlo je typu Curse
        spells.forEach(spell -> assertThat(spell.getType(), equalTo("Curse")));

        // 3. overit ze pocet je vacsi ako 0
        assertThat(spells, hasSize(greaterThan(0)));
    }

    // TODO: pridat nove kuzlo
    @Test
    void itShouldAddNewSpell() {

        Faker faker = new Faker();
        //1. vytvorit objektu noveho kuzla
        HashMap<Object, Object> newSpell = new HashMap<>();
        newSpell.put("spell", "Corona ".concat(faker.letterify("???")));
        newSpell.put("effect", faker.harryPotter().quote());
        newSpell.put("type", "Curse");
        newSpell.put("isUnforgivable", "true");

        //2. pomocou POST poslem toto kuzlo serveru
        // treba za given nastavit hlavicku
        given().header("Content-Type", "application/json")
                .body(newSpell).log().body() //zalogujem si co posielam na server
                .when().post().then().statusCode(201)
                .body("message", equalTo("Spell created"))
                .body("spell.id", not(emptyOrNullString()));
    }
}

