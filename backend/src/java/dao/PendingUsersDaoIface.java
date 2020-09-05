/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Pending_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
public interface PendingUsersDaoIface {
    public List<Pending_Users> list() throws SQLException;
    public Pending_Users find(long id) throws SQLException;
    public List<Pending_Users> search(String column, String value) throws SQLException;
    public boolean create(Pending_Users pendingUser) throws SQLException;
    public boolean delete(long id) throws SQLException;
}
