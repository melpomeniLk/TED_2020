/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Message;

/**
 *
 * @author alicemts
 */
public interface MessageDaoIface {
    
    public List<Message> Inbox(long id) throws SQLException;
    
    public List<Message> Outbox(long id) throws SQLException;
    
    public Message find(long id) throws SQLException;
    
    public List<Message> search(String column, String value) throws SQLException;
    
    public boolean createMessage (Message message) throws SQLException;
    
    public boolean readMessage (long id) throws SQLException;
    
    public boolean markAsUnread (long id) throws SQLException;
    
    public boolean deleteByReceiver (long id) throws SQLException;
    
    public boolean deleteBySender (long id) throws SQLException;
    
    public boolean deleteMessage (long id) throws SQLException;
    
}
