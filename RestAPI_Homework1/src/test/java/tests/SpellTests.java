
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

    //TODO 1: dotiahnite si všetky spells a overte že zoznam všetkých kúziel obsahuje Avada Kedavra, Crucio a Imperio
    @Test
    void itShoudlContainsAvadaKedavraSpellinListOfSpells() {
        //zadefinujem si, co hladam
        String expectedSpell = "Avada Kedavra";

        //vytiahnem  si vsetky hodnoty z responde na GET request, konkretne atribut spell kazdej odpovede
        List<String> allSpellsOnWeb = when().get()
                .then().extract().response()
                .jsonPath().getList("spell");

        // porovnam ci obsahuje Avada Kedavra - co hladam
        assertThat(allSpellsOnWeb, hasItems(expectedSpell));
    }

    //TODO 2: zisti, kolko kuzel obsahuje list kuzel
    @Test
    void itShouldReturnSizeOfSpells() {
        int numberOfSpells = when().get()
                .then().extract().response()
                .jsonPath().getList("spell").size();

        System.out.println(numberOfSpells);

    }

    //TODO 3:  overte, že status-line obsahuje text OK
    @Test
    void itShouldContainsOKStatus() {
        when().get()
                .then().log()
                .status().statusLine(containsString("OK"));

    }

    //TODO 4: pomocou stream->filter skúste vytiahnuť len kúzla typu Curse a overte, že zoznam nie je prázdny
    @Test
    void itShouldCointainsCurseSpells() {
        //1. vytiahnem si vsetky spells do listu  / do hashmapy naplnenej objetami
        List<HashMap<Object, String>> spells = when().get()
                .then().extract().response()
                .jsonPath().getList("$");

        //2. cez stream filter vytiahem z listu len tie, ktore su typu curse, a nasledne collectnut do listu

        spells = spells.stream().filter(object -> object.get("type").equals("Curse"))
                .collect(toList());

        //3. overim, ci list tychto vyfiltrovanych hodnot je vacsi ako 10 napr.
        assertThat(spells, hasSize(equalTo(15)));
        // 4. vypisem si velkost listu kuzel typu curse 
        System.out.println(spells.size());

    }

    //TODO 5: pomocou stream->filter skúste vytiahnuť idčko kúzla Avada Kedavra , pošlite v rámci testu ďaľší
    // request a toto id použite. Potom overte, že kúzlo, ktoré sa vám vrátilo je práve Avada Kedavra.
    @Test
    void itShouldFindSpellIdAndSendItAsParameter() throws SpellNotFundException {
        String spellToFind = "Avada Kedavra";

        //1. vytiahnem si vsetky spells do listu  / do hashmapy naplnenej objetami
        List<HashMap<Object, String>> spells = when().get()
                .then().extract().response()
                .jsonPath().getList("$");

        //2. z hashmapy objektov si vyberiem objekt s danym id

        String id = spells.stream().filter(object -> object.get("spell").equals(spellToFind))
                .findFirst()
                .orElseThrow(() -> new SpellNotFundException(spellToFind))
                .get("id");

        //3. id pouzijem ako path paramenter
        given().pathParam("spellId", id)
                .when().get("/{spellId}")
                .then()
                .body("spell", equalTo(spellToFind))
                .body("effect", equalTo("murders opponent"))
                .body("type", equalTo("Curse"))
                .body("isUnforgivable", is(true));

    }

}

