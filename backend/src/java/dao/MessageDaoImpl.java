/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.Long.getLong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Message;

/**
 *
 * @author alicemts
 */
public class MessageDaoImpl implements MessageDaoIface {

    private static final String SQL_SELECT = "SELECT * from Message order by name asc";
    private static final String SQL_SELECT_BY_SENDER = "SELECT * from Message where from_userId = ?";
    private static final String SQL_SELECT_BY_RECEIVER = "SELECT * from Message where to_userId = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Message where id = ?";
    private static final String SQL_SELECT_BY_TEXT = "SELECT * from Message where text = ?";
    private static final String SQL_SELECT_BY_TIME = "SELECT * from Message where time = ?";
    private static final String SQL_SELECT_READ_MES = "SELECT * from Message where Message.read = ?";
    private static final String SQL_SELECT_BY_AUCTION = "SELECT * from Message where auction_idAuction = ?";
    private static final String SQL_INSERT = "INSERT INTO Message (from_userId,to_userId,time,text,auction_idAuction) VALUES (?,?,?,?,?)";
    private static final String SQL_MARK_READ = "UPDATE ted.Message SET Message.read = true WHERE id =?";    
    private static final String SQL_MARK_UNREAD = "UPDATE Message SET Message.read = 0 WHERE id = ?";    
    private static final String SQL_DELETE_BY_RECEIVER = "UPDATE Message SET deleted_by_receiver = 1 WHERE id = ?";
    private static final String SQL_DELETE_BY_SENDER = "UPDATE Message SET deleted_by_sender = 1 WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Message WHERE id = ?";

    @Override
    public List<Message> Inbox(long id) throws SQLException {
        List<Message> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_RECEIVER);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Message message = new Message();
            message = find(set.getLong("id"));
            if(message.isDeleted_by_receiver() == false)
                list.add(message);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public List<Message> Outbox(long id) throws SQLException {
        List<Message> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_SENDER);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Message message = new Message();
            message = find(set.getLong("id"));
            if(message.isDeleted_by_sender() == false)
                list.add(message);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Message find(long id) throws SQLException {
        Message message = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            message = new Message();
            message.setId(set.getLong("id"));
            message.setFrom_userId(set.getLong("from_userId"));
            message.setTo_userId(set.getLong("to_userId"));
            message.setTime(set.getTimestamp(4));
            message.setText(set.getString("text"));
            message.setRead(set.getBoolean("read"));
            message.setDeleted_by_sender(set.getBoolean("deleted_by_sender"));
            message.setDeleted_by_receiver(set.getBoolean("deleted_by_receiver"));
            message.setAuction_idAuction(set.getLong("auction_idAuction"));
        }
        set.close();
        statement.close();
        conn.close();
        return message;
    }

    @Override
    public List<Message> search(String column, String value) throws SQLException {
        ArrayList<Message> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        
        switch(column) 
        { 
            case "id": 
                statement = conn.prepareStatement(SQL_SELECT_BY_ID);
                statement.setLong(1, Long.parseLong(value));
                break;
            case "fromUserId":
                statement = conn.prepareStatement(SQL_SELECT_BY_SENDER);
                statement.setLong(1, Long.parseLong(value));
                break;
            case "toUserId":
                statement = conn.prepareStatement(SQL_SELECT_BY_RECEIVER);
                statement.setLong(1, Long.parseLong(value));
                break;
            case "text":
                statement = conn.prepareStatement(SQL_SELECT_BY_TEXT);
                statement.setString(1, value);
                break;
            case "time":
                statement = conn.prepareStatement(SQL_SELECT_BY_TIME);
                statement.setTimestamp(1, Timestamp.valueOf(value));
                break;
            case "read":
                statement = conn.prepareStatement(SQL_SELECT_READ_MES);
                statement.setBoolean(1, Boolean.parseBoolean(value));
                break;
            case "auctionId":
                statement = conn.prepareStatement(SQL_SELECT_BY_AUCTION);
                statement.setLong(1, Long.parseLong(value));
                break;
            case "default":
                break;
        }
        
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Message message = new Message();
            message = find(set.getLong("id"));
            list.add(message);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public boolean createMessage(Message message) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
        rs = statement.getGeneratedKeys();
        statement.setLong(1, message.getFrom_userId());
        statement.setLong(2, message.getTo_userId());
        statement.setTimestamp(3, message.getTime());
        statement.setString(4, message.getText());
        statement.setLong(5, message.getAuction_idAuction());
        
        //set.close();
        statement.close();
        conn.close();
        return created;
    }

    @Override
    public boolean readMessage(long id) throws SQLException {
        boolean read = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_MARK_READ);
        statement.setLong(1, id);
        if (statement.executeUpdate() == 1)
            read = true;

        statement.close();
        conn.close();
        return read;
    }

    @Override
    public boolean deleteMessage(long id) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        Message message = new Message();
        message = find(id);
        if(message.isDeleted_by_receiver() && message.isDeleted_by_sender()){
            statement = conn.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1)
                delete = true;
        }
           
        statement.close();
        conn.close();
        return delete;
    }

    @Override
    public boolean deleteByReceiver(long id) throws SQLException {
        boolean deleted = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_DELETE_BY_RECEIVER);
        statement.setLong(1, id);
        if (statement.executeUpdate() == 1)
            deleted = true;

        statement.close();
        conn.close();
        return deleted;
    }

    @Override
    public boolean deleteBySender(long id) throws SQLException {
        boolean deleted = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_DELETE_BY_SENDER);
        statement.setLong(1, id);
        if (statement.executeUpdate() == 1)
            deleted = true;

        statement.close();
        conn.close();
        return deleted;
    }

    @Override
    public boolean markAsUnread(long id) throws SQLException {
        boolean unread = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_MARK_UNREAD);
        statement.setLong(1, id);
        if (statement.executeUpdate() == 1)
            unread = true;

        statement.close();
        conn.close();
        return unread;
    }
    

    

}
