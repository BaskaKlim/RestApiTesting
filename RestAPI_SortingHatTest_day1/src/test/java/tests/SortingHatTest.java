package tests;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIn.*;
import static org.hamcrest.collection.IsMapContaining.*;
import static org.hamcrest.text.IsEmptyString.*;

public class SortingHatTest {

    @Test
    /** SMOKE REST ASURED TEST - testujem endpoint SortingHat ci odpovie na request - code200**/
    void itShouldReturnStatusCode200() {
        //arrange - given     dana url
        //act   - when    zavolam GET REQUEST
        //assert   - then     statuscode bude 200
        given().baseUri("http://localhost:3000/sortingHat")
                .when().get()
                .then().statusCode(200);

    }

    @Test
    /** validate body request value - testujem odpoved, co mi pride v requeste**/

    void itShouldReturnCorrectKeys() {
        given().baseUri("http://localhost:3000/sortingHat")
                .when().get()
                .then().log().body()      //vypisanie requestu do koncoly
                .body("", hasKey("sortingHatSays"))
                .body("", hasKey("house"));
    }

}
