/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;
import dao.ActivatedUsersDaoImpl;
import dao.ActivatedUsersDaoIface;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Activated_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
@Path("activatedUsers")
public class ActivatedUsersResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ActivatedUsersResource
     */
    public ActivatedUsersResource() {
    }
    
    
    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listActivatedUsers() {
        try {
            ActivatedUsersDaoIface dao = new ActivatedUsersDaoImpl();
            List<Activated_Users> activatedUsers = dao.list();
            GenericEntity<List<Activated_Users>> entities = new GenericEntity<List<Activated_Users>>(activatedUsers) {};
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
            ActivatedUsersDaoIface dao = new ActivatedUsersDaoImpl();
            Activated_Users activatedUser = dao.find(id);
            List<Activated_Users> activatedUsers = new ArrayList<>();
            if (activatedUser != null) {
                activatedUsers.add(activatedUser);
            }
            GenericEntity<List<Activated_Users>> entities = new GenericEntity<List<Activated_Users>>(activatedUsers) {};
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
            ActivatedUsersDaoIface dao = new ActivatedUsersDaoImpl();
            List<Activated_Users> activatedUsers = dao.search(column,value);

            GenericEntity<List<Activated_Users>> entities = new GenericEntity<List<Activated_Users>>(activatedUsers) {};
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
            ActivatedUsersDaoIface dao = new ActivatedUsersDaoImpl();
            Activated_Users activatedUser = new Activated_Users();
            activatedUser.setUser_userId(id);
            boolean created = dao.create(activatedUser);
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
            ActivatedUsersDaoIface dao = new ActivatedUsersDaoImpl();
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
