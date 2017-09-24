package library;
import library.entity.*;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class BookController {
    private BookService bookService;
    private static String HEADER_O = "Access-Control-Allow-Origin";
    private static String HEADER_M = "Access-Control-Allow-Methods";

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") int id) throws Exception{
        Response response = null;
        try{
            bookService = new BookService();
            response = Response.status(Response.Status.OK).entity(bookService.getBook(id)).header(HEADER_O, "*").header(HEADER_M, "GET").build();

        }
        catch (Exception e){
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").build();
        }
        return response;
    }

    @GET
    @Path("/bytitle/{title}")
    public Response getBookByTitle(@PathParam("title") String title) throws Exception{
        Response response = null;
        try {
            bookService = new BookService();
            List<Book> bookList = bookService.getBookByTitle(title);
            GenericEntity<List<Book>> entityList = new GenericEntity<List<Book>>(bookList) {};

            response = Response.status(Response.Status.OK).entity(entityList).header(HEADER_O, "*").header(HEADER_M, "GET").build();

        }catch(Exception e){
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").build();
            }
        return response;
    }

    @GET
    @Path("/byauthor/{author}")
    public Response getBookByAuthor(@PathParam("author") String author) throws Exception{
        Response response = null;
        try {
            bookService = new BookService();
            List<Book> bookList = bookService.getBookByAuthor(author);
            GenericEntity<List<Book>> entityList = new GenericEntity<List<Book>>(bookList) {};
            response = Response.status(Response.Status.OK).entity(entityList).header(HEADER_O, "*").header(HEADER_M, "GET").build();

        }catch(Exception e){
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").build();
        }
        return response;
    }

}
