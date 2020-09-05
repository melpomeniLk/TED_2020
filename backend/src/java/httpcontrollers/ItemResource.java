/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.ItemDaoIface;
import dao.ItemDaoImpl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Item;

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("items")
public class ItemResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public ItemResource() {
    }

    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listItems() {
        try {
            ItemDaoIface dao = new ItemDaoImpl();
            List<Item> items = dao.list();

            GenericEntity<List<Item>> entities = new GenericEntity<List<Item>>(items) {
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
            ItemDaoIface dao = new ItemDaoImpl();
            Item item = dao.find(id);

            List<Item> items = new ArrayList<>();

            if (item != null) {
                items.add(item);
            }

            GenericEntity<List<Item>> entities = new GenericEntity<List<Item>>(items) {
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
            ItemDaoIface dao = new ItemDaoImpl();
            Item newItem = new Item();
            boolean created = dao.create(newItem);
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
            ItemDaoIface dao = new ItemDaoImpl();
            
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