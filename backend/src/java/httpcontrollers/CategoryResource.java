/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.CategoryDaoIface;
import dao.CategoryDaoImpl;
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
import models.Category;
//import models.Dog;

/**
 * REST Web Service
 *
 * @author alicemts
 */
@Path("category")
public class CategoryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public CategoryResource() {
    }

    @Path("list")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCountries() {
        try {
            CategoryDaoIface dao = new CategoryDaoImpl();
            List<Category> countries = dao.list();

            GenericEntity<List<Category>> entities = new GenericEntity<List<Category>>(countries) {
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
            CategoryDaoIface dao = new CategoryDaoImpl();
            Category category = dao.find(id);

            List<Category> countries = new ArrayList<>();

            if (category != null) {
                countries.add(category);
            }

            GenericEntity<List<Category>> entities = new GenericEntity<List<Category>>(countries) {
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
    public Response create(@QueryParam("name") String name) {
        try {
            CategoryDaoIface dao = new CategoryDaoImpl();
            Category newCategory = new Category();
            
            newCategory.setName(name);
            boolean created = dao.create(newCategory);
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
            CategoryDaoIface dao = new CategoryDaoImpl();
            boolean deleted;
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