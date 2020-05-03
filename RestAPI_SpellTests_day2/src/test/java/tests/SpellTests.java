
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

}

