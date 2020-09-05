/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.ItemHasCountryDaoIface;
import dao.ItemHasCountryDaoImpl;
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
import models.Item;

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("item")
public class ItemHasCountryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public ItemHasCountryResource() {
    }

    @Path("country")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCountry(@QueryParam("id") Long id) {
        try {
            ItemHasCountryDaoIface dao = new ItemHasCountryDaoImpl();
            List<Country> countries = dao.ItemHasCountry(id);

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
    
    @Path("find")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("id") Long id) {
        try {
            ItemHasCountryDaoIface dao = new ItemHasCountryDaoImpl();
            List<Item> items = new ArrayList<>();
            
            //items = dao.ItemHasCountry(id);
            
            GenericEntity<List<Item>> entities = new GenericEntity<List<Item>>(items) {
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
    
    @Path("insertsCountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create() {
        try {
            ItemHasCountryDaoIface dao = new ItemHasCountryDaoImpl();
            
            boolean inserted = dao.itemInsertsCountry(3,2);
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
    
    @Path("updatesCountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update() {
        try {
            ItemHasCountryDaoIface dao = new ItemHasCountryDaoImpl();
            
            boolean updated = dao.itemChangesCountry(3,3);
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
    
    @Path("deleteCountry")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete() {
        try {
            ItemHasCountryDaoIface dao = new ItemHasCountryDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteItemFromCountry(3);
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