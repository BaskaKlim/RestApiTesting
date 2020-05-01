
package tests;

import java.util.*;
import org.hamcrest.collection.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpellTests {
    //TODO: dotiahnite si všetky spells a overte že zoznam všetkých kúziel obsahuje Avada Kedavra, Crucio a Imperio

    @BeforeAll
    static void config() {
        baseURI = "http://localhost";
        port = 3000;
        basePath = "/spells";

    }

    @Test
    void itShoudlContainsAvadaKedavraSpellinListOfSpells() {
        //zadefinujem si, co hladam
        String expectedSpell = "Avada Kedavra";

        //vytiahnem  si vsetky hodnoty z responde na GET request, konkretne atribut spell kazdej odpovede
        List<String> allSpellsOnWeb =  when().get()
                .then().extract().response()
                .jsonPath().getList("$.spell");

        // porovnam ci obsahuje Avada Kedavra - co hladam
        assertThat(allSpellsOnWeb, hasItems(expectedSpell)) ;
    }
}

