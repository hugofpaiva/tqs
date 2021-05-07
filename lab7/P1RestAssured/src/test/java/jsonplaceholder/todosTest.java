package jsonplaceholder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

class todosTest {
    String baseUrl = "https://jsonplaceholder.typicode.com/todos";

    @Test
    void testListAllToDos() {
        given().when().get(baseUrl)
                .then().assertThat().statusCode(200)
                .and().body("", hasSize(200));
    }

    @Test
    void testToDo4Title() {
        String title = "et porro tempora";

        given().when().get(baseUrl + "/4")
                .then().assertThat().statusCode(200)
                .and().body("title", equalTo(title))
                .and().body("id", equalTo(4));
    }

    @Test
    void testListAllToDosInclude198And199() {
        given().when().get(baseUrl)
                .then().assertThat().statusCode(200)
                .and().body("id", hasItems(198, 199));
    }
}