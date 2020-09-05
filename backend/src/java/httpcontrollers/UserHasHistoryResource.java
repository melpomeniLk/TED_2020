/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

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

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("history")
public class UserHasHistoryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserHasHistoryResource() {
    }

    @Path("user")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userHistory(@QueryParam("id") Long id) {
        try {
            UserHasHistoryDaoIface dao = new UserHasHistoryDaoImpl();
            List<Auction> history = dao.userHistory(id);

            GenericEntity<List<Auction>> entities = new GenericEntity<List<Auction>>(history) {
            };

            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    
    @Path("viewsauction")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("userid") Long userid,@QueryParam("auctionid") Long auctionid) {
        try {
            UserHasHistoryDaoIface dao = new UserHasHistoryDaoImpl();
            
            boolean inserted = dao.userViewsAuction(userid,auctionid);
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
   
    
    @Path("deleteshistory")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") Long id) {
        try {
            UserHasHistoryDaoIface dao = new UserHasHistoryDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteHistory(id);
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
    
    @Path("deletesauction")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuction(@QueryParam("userid") Long userid,@QueryParam("auctionid") Long auctionid) {
        try {
            UserHasHistoryDaoIface dao = new UserHasHistoryDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteAuctionFromHistory(userid,auctionid);
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