import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LendingControllerTest {

    @Test
    public void testGetLending() throws Exception {
        given().when().get("http://localhost:8080/Library_war_exploded/rest/lending/294561").then().statusCode(200).body("firstname", equalTo("aa"));
    }

    @Test
    public void testAddLending() throws Exception{
        Map<String,String> json = new HashMap<String, String>();
        json.put("firstname", "Mari");
        json.put("lastname", "Mustikas");
        json.put("email", "mari.mustiks@example.com");
        json.put("bookID", "294562");

        given().contentType("application/json")
                .body(json)
                .when().post("http://localhost:8080/Library_war_exploded/rest/lending/add").then()
                .statusCode(200);
        given().when().get("http://localhost:8080/Library_war_exploded/rest/lending/294562").then().statusCode(200)
                .body("firstname", equalTo("Mari"));
    }

    @Test
    public void testDeleteLending() throws Exception{
        Map<String,String> json = new HashMap<String, String>();
        json.put("firstname", "Mari");
        json.put("lastname", "Mustikas");
        json.put("bookID", "294562");

        given().when().delete("http://localhost:8080/Library_war_exploded/rest/lending/delete/Mari/Mustikas/294562").then().statusCode(200);
        given().when().get("http://localhost:8080/Library_war_exploded/rest/lending/294562").then().statusCode(404);
    }
}
