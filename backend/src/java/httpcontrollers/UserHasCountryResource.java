/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.UserHasCountryDaoIface;
import dao.UserHasCountryDaoImpl;
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
import models.Country;
import models.User;

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("user")
public class UserHasCountryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserHasCountryResource() {
    }

    @Path("hascountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCountry(@QueryParam("id") Long id) {
        try {
            UserHasCountryDaoIface dao = new UserHasCountryDaoImpl();
            List<Country> countries = dao.userHasCountry(id);

            GenericEntity<List<Country>> entities = new GenericEntity<List<Country>>(countries) {
            };

            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("countryhasusers")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("id") Long id) {
        try {
            UserHasCountryDaoIface dao = new UserHasCountryDaoImpl();
            List<User> users = new ArrayList<>();
            
            users = dao.countryHasUsers(id);
            
            GenericEntity<List<User>> entities = new GenericEntity<List<User>>(users) {
            };
            
//            GenericEntity<List<Long>> entities = new GenericEntity<List<Long>>(ids) {
//            };

            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("insertscountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("userid") Long userid,@QueryParam("countryid") Long countryid) {
        try {
            UserHasCountryDaoIface dao = new UserHasCountryDaoImpl();
            
            boolean inserted = dao.userInsertsCountry(userid,countryid);
            Response res;
            if (inserted){
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
    
    @Path("updatescountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("userid") Long userid,@QueryParam("countryid") Long countryid) {
        try {
            UserHasCountryDaoIface dao = new UserHasCountryDaoImpl();
            
            boolean updated = dao.userChangesCountry(userid,countryid);
            Response res;
            if (updated){
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
    
    @Path("deletescountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") Long id) {
        try {
            UserHasCountryDaoIface dao = new UserHasCountryDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteUserFromCountry(id);
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