/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.ItemHasCategoryDaoIface;
import dao.ItemHasCategoryDaoImpl;
import dao.UserHasHistoryDaoIface;
import dao.UserHasHistoryDaoImpl;
import java.sql.SQLException;
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
import models.Auction;
import models.Category;

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("item")
public class ItemHasCategoryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public ItemHasCategoryResource() {
    }

    @Path("categories")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response itemCategories(@QueryParam("id") Long id) {
        try {
            ItemHasCategoryDaoIface dao = new ItemHasCategoryDaoImpl();
            List<Category> categories = dao.itemHasCategories(id);

            GenericEntity<List<Category>> entities = new GenericEntity<List<Category>>(categories) {
            };

            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    
    @Path("insertcategory")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCategory(@QueryParam("itemid") Long itemid,@QueryParam("categoryid") Long categoryid) {
        try {
            ItemHasCategoryDaoIface dao = new ItemHasCategoryDaoImpl();
            
            boolean inserted = dao.itemInsertsCategory(itemid,categoryid);
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
   
    
    @Path("delete")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") Long id) {
        try {
            ItemHasCategoryDaoIface dao = new ItemHasCategoryDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteItem(id);
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
    
    @Path("deletecategory")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuction(@QueryParam("itemid") Long itemid,@QueryParam("categoryid") Long categoryid) {
        try {
            ItemHasCategoryDaoIface dao = new ItemHasCategoryDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteItemFromCategory(itemid,categoryid);
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