/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;
import dao.PendingUsersDaoIface;
import dao.PendingUsersDaoImpl;
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
import models.Pending_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
@Path("pendingUsers")
public class PendingUsersResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PendingUsersResource
     */
    public PendingUsersResource() {
    }
    
    
    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPendingUsers() {
        try {
            PendingUsersDaoIface dao = new PendingUsersDaoImpl();
            List<Pending_Users> actions = dao.list();
            GenericEntity<List<Pending_Users>> entities = new GenericEntity<List<Pending_Users>>(actions) {};
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
            PendingUsersDaoIface dao = new PendingUsersDaoImpl();
            Pending_Users pendingUser = dao.find(id);
            List<Pending_Users> pendingUsers = new ArrayList<>();
            if (pendingUser != null) {
                pendingUsers.add(pendingUser);
            }
            GenericEntity<List<Pending_Users>> entities = new GenericEntity<List<Pending_Users>>(pendingUsers) {};
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
            PendingUsersDaoIface dao = new PendingUsersDaoImpl();
            List<Pending_Users> pendingUsers = dao.search(column,value);

            GenericEntity<List<Pending_Users>> entities = new GenericEntity<List<Pending_Users>>(pendingUsers) {};
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
            PendingUsersDaoIface dao = new PendingUsersDaoImpl();
            Pending_Users pendingUser = new Pending_Users();
            pendingUser.setUser_userId(id);
            boolean created = dao.create(pendingUser);
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
            PendingUsersDaoIface dao = new PendingUsersDaoImpl();
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
