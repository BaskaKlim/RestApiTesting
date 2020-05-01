
package tests;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class SpellTests {
    //TODO: dotiahnite si všetky spells a overte že zoznam všetkých kúziel obsahuje Avada Kedavra, Crucio a Imperio

    @BeforeAll
    static void config() {
        baseURI = "http://localhost";
        port = 3000;
        basePath = "/spells";

    }

    @Test
    void itShoudlContainAvadaKedavra() {

    }
}

