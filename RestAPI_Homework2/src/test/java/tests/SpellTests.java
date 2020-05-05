
package tests;

import java.util.*;
import org.hamcrest.collection.*;
import org.junit.jupiter.api.*;

import com.github.javafaker.*;
import exceptions.*;
import io.restassured.http.*;
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

    @BeforeEach
    void resetSpells() {
        //TODO: vytvorte si metódu s anotáciou Before each a toto volanie resetu tam pridajte
        //zavolam konkretny endpoint tak si zresetujem kuzla: https://localhost:3009/spells/actions/reset
        when().get("/actions/reset")
                .then().statusCode(200);

    }

    @Test
        // TODO: overte, že po zavolaní endpointu /spells/actions/reset a následnom zavolaní spells  sa vráti presne 151 kúziel
    void itShouldResetSpells() {
        when().get("actions/reset")
                .then().statusCode(200);

        List<Spell> resetSpells = when().get()
                .then().extract().jsonPath().getList("");
        assertThat(resetSpells, hasSize(151));

    }

    @Test
    void itShouldUpdateSpell() {
        //TODO:  Spellelogy - PUT napíšte test na úpravu existujúceho kúzla
        //1.vytvorim si kuzlo
        Spell spell = new Spell(
                "Halabala",
                "Curse",
                "Sneezing forever",
                true
        );
        //2. pomocou POST poslem toto kuzlo serveru
        String id =
                given()
                        .contentType(ContentType.JSON)
                        .body(spell)
                        .when().post()
                        .then().log().body()
                        .statusCode(201)
                        .body("message", equalTo("Spell created")).extract()
                        .jsonPath()
                        .get("spell.id");

        //3. vytvorim si updatnute nove kuzlo ktore chcem vlozit namiesto povodneho
        Spell newSpell = new Spell("Nunanana",
                "Hex",
                "summons a broom",
                false);
        //4. predam serveru nove updatnute kuzlo
        given()
                .contentType(ContentType.JSON)
                .pathParam("idOfSpell", id)
                .body(newSpell)
                .when().put("/{idOfSpell}")
                .then().statusCode(201)
                .and().body("message", equalTo("Spell updated"));

    }
}
