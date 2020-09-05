/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.AuctionDaoIface;
import dao.AuctionDaoImpl;
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
import models.Auction;
/**
 *
 * @author anastasiaeleftheriadi
 */
@Path("auctions")
public class AuctionResource {
   @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public AuctionResource() {
    }
    
    
    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAuctions() {
        try {
            AuctionDaoImpl dao = new AuctionDaoImpl();
            List<Auction> auctions = dao.list();
            GenericEntity<List<Auction>> entities = new GenericEntity<List<Auction>>(auctions) {};
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
            AuctionDaoImpl dao = new AuctionDaoImpl();
            Auction auction = dao.find(id);
            List<Auction> auctions = new ArrayList<>();
            if (auction != null) {
                auctions.add(auction);
            }
            GenericEntity<List<Auction>> entities = new GenericEntity<List<Auction>>(auctions) {};
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
            AuctionDaoImpl dao = new AuctionDaoImpl();
            List<Auction> auctions = dao.search(column,value);

            GenericEntity<List<Auction>> entities = new GenericEntity<List<Auction>>(auctions) {
            };

            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    
    @Path("active")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listActiveAuctions(@QueryParam("id") Long id) {
        try {
            AuctionDaoImpl dao = new AuctionDaoImpl();
            List<Auction> auctions = dao.listActiveAuctions(id);
            GenericEntity<List<Auction>> entities = new GenericEntity<List<Auction>>(auctions) {};
            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    
    @Path("inactive")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listInactiveAuctions(@QueryParam("id") Long id) {
        try {
            AuctionDaoImpl dao = new AuctionDaoImpl();
            List<Auction> auctions = dao.listInactiveAuctions(id);
            GenericEntity<List<Auction>> entities = new GenericEntity<List<Auction>>(auctions) {};
            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    
//    @Path("create")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response create() {
//        try {
//            UserDaoIface dao = new UserDaoImpl();
//            User user = new User();
//            user.setUsername("maria_Del");
//            user.setPassword("as28f");
//            user.setName("maria");
//            user.setLast_name("sdalkafouki");
//            user.setEmail("maraki@yahoo.gr");
//            user.setPhone_number("6945156547");
//            user.setLocation_latitude(23.2);
//            user.setLocation_longitude(27.4);
//            user.setAfm("54896357");
//            boolean created = dao.createUser(user);
//            Response res;
//            if (created){
//               res = Response.status(Response.Status.OK).build();
//            }else{
//                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//            }
//            return res;
//        } catch (SQLException ex) {
//            String details = ex.getMessage();
//            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
//            return res;
//        }
//    }
//    
//    @Path("update")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(@QueryParam("column") String column,@QueryParam("value") String value,@QueryParam("id") Long id) {
//        try {
//            UserDaoIface dao = new UserDaoImpl();
//            User newUser = new User();
//            boolean updated = dao.changeData(column,value,id);
//            Response res;
//            if (updated){
//               res = Response.status(Response.Status.OK).build();
//            }else{
//                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//            }
//            return res;
//        } catch (SQLException ex) {
//            String details = ex.getMessage();
//            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
//            return res;
//        }
//    }

//    @Path("delete")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response delete(@QueryParam("id") Long id) {
//        try {
//            UserDaoIface dao = new UserDaoImpl();
//            boolean deleted = false;
//            deleted = dao.deleteUser(id);
//            Response res;
//            if (deleted){
//                res = Response.status(Response.Status.OK).build();
//            }else{
//                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//            }
//            return res;
//        } catch (SQLException ex) {
//             String details = ex.getMessage();
//            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
//            return res;
//        }
//
//    }
    

    
}
