
package tests;

import java.util.*;
import org.hamcrest.collection.*;
import org.junit.jupiter.api.*;

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

    @Test
    void itShoulFilterSpellsBasedOnQueryType() {
        //1. nastavim parametre cez query a vytiahnem si list kuzel, ktory ich splna
        List<Spell> spells = given().queryParam("type", "Curse")
                .when().get()
                .then().extract().jsonPath().getList("$",Spell.class);

        // 2. overit ze kazde kuzlo je typu Curse
        spells.forEach(spell -> assertThat(spell.getType(), equalTo("Curse")));

        // 3. overit ze pocet je vacsi ako 0
        assertThat(spells, hasSize(greaterThan(0)));
    }


    // TODO: pridat nove kuzlo
    @Test
    void itShouldAddNewSpell(){
        //1. vytvorit objektu noveho kuzla
        HashMap<Object,Object> newSpell = new HashMap<>();
        newSpell.put("spell","Corona");
        newSpell.put("effect","sneezing forever");
        newSpell.put("type","Curse");
        newSpell.put("isUnforgivable","true");

        //2. pomocou POST poslem toto kuzlo serveru
          // treba za given nastavit hlavicku
        given().header("Content-Type","application/json")
                .body(newSpell).when().post().then().statusCode(201)
                .body("message", equalTo("Spell created"))
                .body("spell.id", not(emptyOrNullString()));
    }
}

