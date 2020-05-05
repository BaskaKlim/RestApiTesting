
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


}

