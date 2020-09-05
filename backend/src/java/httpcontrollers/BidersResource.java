/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.BidersDaoIface;
import dao.BidersDaoImpl;
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
import models.Biders;

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("biders")
public class BidersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public BidersResource() {
    }

    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBiders() {
        try {
            BidersDaoIface dao = new BidersDaoImpl();
            List<Biders> biders = dao.listBiders();

            GenericEntity<List<Biders>> entities = new GenericEntity<List<Biders>>(biders) {
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
            BidersDaoIface dao = new BidersDaoImpl();
            Biders bider = dao.find(id);

            List<Biders> biders = new ArrayList<>();

            if (bider != null) {
                biders.add(bider);
            }

            GenericEntity<List<Biders>> entities = new GenericEntity<List<Biders>>(biders) {
            };

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
            BidersDaoIface dao = new BidersDaoImpl();
            List<Biders> biders = dao.search(column,value);

            GenericEntity<List<Biders>> entities = new GenericEntity<List<Biders>>(biders) {
            };

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
            BidersDaoIface dao = new BidersDaoImpl();
            
            boolean created = dao.create(id);
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
    
    @Path("voteup")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response voteUp(@QueryParam("id") Long id) {
        try {
            BidersDaoIface dao = new BidersDaoImpl();
            
            boolean updated = dao.voteUp(id);
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
    
    @Path("votedown")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response voteDown(@QueryParam("id") Long id) {
        try {
            BidersDaoIface dao = new BidersDaoImpl();
            
            boolean updated = dao.voteDown(id);
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

    @Path("delete")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
     public Response delete(@QueryParam("id") Long id) {
        try {
            BidersDaoIface dao = new BidersDaoImpl();
            
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