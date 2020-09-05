/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;
import dao.RejectedUsersDaoIface;
import dao.RejectedUsersDaoImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Rejected_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
@Path("rejectedUsers")
public class RejectedUsersResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RejectedUsersResource
     */
    public RejectedUsersResource() {
    }
    
    
    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRejectedUsers() {
        try {
            RejectedUsersDaoIface dao = new RejectedUsersDaoImpl();
            List<Rejected_Users> rejectedUsers = dao.list();
            GenericEntity<List<Rejected_Users>> entities = new GenericEntity<List<Rejected_Users>>(rejectedUsers) {};
            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    

    @Path("find")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("id") Long id) {
        try {
            RejectedUsersDaoIface dao = new RejectedUsersDaoImpl();
            Rejected_Users rejectedUser = dao.find(id);
            List<Rejected_Users> rejectedUsers = new ArrayList<>();
            if (rejectedUser != null) {
                rejectedUsers.add(rejectedUser);
            }
            GenericEntity<List<Rejected_Users>> entities = new GenericEntity<List<Rejected_Users>>(rejectedUsers) {};
            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("search")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("column") String column,@QueryParam("value") String value) {
        try {
            RejectedUsersDaoIface dao = new RejectedUsersDaoImpl();
            List<Rejected_Users> rejectedUsers = dao.search(column,value);

            GenericEntity<List<Rejected_Users>> entities = new GenericEntity<List<Rejected_Users>>(rejectedUsers) {};
            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("create")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("id") Long id) {
        try {
            RejectedUsersDaoIface dao = new RejectedUsersDaoImpl();
            Rejected_Users rejectedUser = new Rejected_Users();
            rejectedUser.setUser_userId(id);
            boolean created = dao.create(rejectedUser);
            Response res;
            if (created){
               res = Response.status(Response.Status.OK).build();
            }else{
                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            return res;
        } catch (SQLException ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("delete")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") Long id) {
        try {
            RejectedUsersDaoIface dao = new RejectedUsersDaoImpl();
            boolean deleted = false;
            deleted = dao.delete(id);
            Response res;
            if (deleted){
                res = Response.status(Response.Status.OK).build();
            }else{
                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            return res;
        } catch (SQLException ex) {
             String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
}
