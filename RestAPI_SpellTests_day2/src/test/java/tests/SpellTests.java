
package tests;

import java.util.*;
import org.hamcrest.collection.*;
import org.junit.jupiter.api.*;

import exceptions.*;

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
        List<HashMap <Object, String>> spells = given().queryParam("type", "Curse")
                .when().get()
                .then().extract().jsonPath().getList("$");

        // 2. overit ze kazde kuzlo je typu Curse
        spells.forEach(spell -> assertThat(spell.get("type"), equalTo("Curse")));

        // 3. overit ze pocet je vacsi ako 0
        assertThat(spells, hasSize(greaterThan(0)));
    }

}

