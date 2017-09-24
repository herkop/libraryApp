import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookControllerTest {


    @Test
    public void testGetBook() throws Exception {
        given().when().get("http://localhost:8080/Library_war_exploded/books/294562").then().statusCode(200).body("title", equalTo("Java Developer"));
    }

    @Test
    public void testGetBookByTitle() throws Exception {
        List list = new ArrayList();
        list.add("Java Developer");
        list.add("Java algajatele");
        given().when().get("http://localhost:8080/Library_war_exploded/rest/books/bytitle/Java").then().statusCode(200).body("title", equalTo(list));
    }

    @Test
    public void testGetBookByAuthor() throws Exception {
        List list = new ArrayList();
        list.add("Mart Maas");
        given().when().get("http://localhost:8080/Library_war_exploded/rest/books/byauthor/Maas").then().statusCode(200).body("author", equalTo(list));
    }
}
