/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import dao.MessageDaoIface;
import dao.MessageDaoImpl;
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
import models.Message;
/**
 *
 * @author anastasiaeleftheriadi
 */
@Path("messages")
public class MessageResource {
   @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public MessageResource() {
    }
    
    
    @Path("inbox")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listInbox(@QueryParam("id") Long id) {
        try {
            MessageDaoImpl dao = new MessageDaoImpl();
            List<Message> actions = dao.Inbox(id);
            GenericEntity<List<Message>> entities = new GenericEntity<List<Message>>(actions) {};
            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("outbox")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOutbox(@QueryParam("id") Long id) {
        try {
            MessageDaoImpl dao = new MessageDaoImpl();
            List<Message> actions = dao.Outbox(id);
            GenericEntity<List<Message>> entities = new GenericEntity<List<Message>>(actions) {};
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
            MessageDaoImpl dao = new MessageDaoImpl();
            Message message = dao.find(id);
            List<Message> messages = new ArrayList<>();
            if (message != null) {
                messages.add(message);
            }
            GenericEntity<List<Message>> entities = new GenericEntity<List<Message>>(messages) {};
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
            MessageDaoImpl dao = new MessageDaoImpl();
            List<Message> messages = dao.search(column,value);

            GenericEntity<List<Message>> entities = new GenericEntity<List<Message>>(messages) {
            };

            Response res = Response.status(Response.Status.OK).entity(entities).build();
            return res;
        } catch (Exception ex) {
            String details = ex.getMessage();
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(details).build();
            return res;
        }
    }
    
    @Path("isread")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response readMessage(@QueryParam("id") Long id) {
        try {
            MessageDaoImpl dao = new MessageDaoImpl();
            boolean read = dao.readMessage(id);
            Response res;
            if (read){
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
    
    @Path("unread")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response markUnread(@QueryParam("id") Long id) {
        try {
            MessageDaoImpl dao = new MessageDaoImpl();
            boolean unread = dao.markAsUnread(id);
            Response res;
            if (unread){
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
            MessageDaoImpl dao = new MessageDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteMessage(id);
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
    
    @Path("deletebyreceiver")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByReceiver(@QueryParam("id") Long id) {
        try {
            MessageDaoImpl dao = new MessageDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteByReceiver(id);
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
    
    @Path("deletebysender")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBySender(@QueryParam("id") Long id) {
        try {
            MessageDaoImpl dao = new MessageDaoImpl();
            boolean deleted = false;
            deleted = dao.deleteBySender(id);
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
