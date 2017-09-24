package library;


import library.entity.Lending;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lending")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LendingController {
    private static String HEADER_O = "Access-Control-Allow-Origin";
    private static String HEADER_M = "Access-Control-Allow-Methods";



    @POST
    @Path("/add")
    public Response addLending(Lending lending) throws Exception{
        Response response = null;

        try {
            LendingService lendingService = new LendingService();
            response = Response.status(Response.Status.OK).entity(lendingService.addLending(lending)).header(HEADER_O, "*").header(HEADER_M, "POST, GET, PUT, DELETE").header("Access-Control-Allow-Headers", "Content-Type").build();
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").header(HEADER_M, "POST").build();
        }
        return response;
    }



    @GET()
    @Path("{id}")
    public Response getLending(@PathParam("id") int id) throws Exception{
        Response response = null;
        try {
            LendingService lendingService = new LendingService();
            response = Response.status(Response.Status.OK).entity(lendingService.getLending(id)).header(HEADER_O, "*").header(HEADER_M, "GET").build();
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").header(HEADER_M, "GET").build();
        }
        return response;
    }

    @DELETE()
    @Path("/delete/{firstname}/{lastname}/{bookID}")
    public Response deleteLending(@PathParam("firstname") String firstname, @PathParam("lastname") String lastname, @PathParam("bookID") String bookID) throws Exception{
        Response response = null;
        try {
            LendingService lendingService = new LendingService();
            Lending lending = lendingService.getLending(firstname, lastname, bookID);
            response = Response.status(Response.Status.OK).entity(lendingService.deleteLending(lending)).header(HEADER_O, "*").header(HEADER_M, "DELETE").build();
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").header(HEADER_M, "DELETE").build();
        }
        return response;
    }

    @OPTIONS
    @Path("/add")
    public Response addLending1() throws Exception{
        Response response = null;

        try {
            response = Response.status(Response.Status.OK).entity("aa").header(HEADER_O, "*").header(HEADER_M, "POST, GET, PUT, OPTIONS, DELETE").header("Access-Control-Allow-Headers", "Content-Type").build();
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").header(HEADER_M, "POST").build();
        }
        return response;
    }

    @OPTIONS()
    @Path("/delete/{firstname}/{lastname}/{bookID}")
    public Response deleteLending1() throws Exception{
        Response response = null;
        try {
            response = Response.status(Response.Status.OK).entity("ssss").header(HEADER_O, "*").header(HEADER_M, "OPTIONS, DELETE").build();
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.NOT_FOUND).header(HEADER_O, "*").header(HEADER_M, "OPTIONS, DELETE").build();
        }
        return response;
    }
}
