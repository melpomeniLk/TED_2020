/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Rejected_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
public interface RejectedUsersDaoIface {
    public List<Rejected_Users> list() throws SQLException;
    public Rejected_Users find(long id) throws SQLException;
    public List<Rejected_Users> search(String column, String value) throws SQLException;
    public boolean create(Rejected_Users rejectedUser) throws SQLException;
    public boolean delete(long id) throws SQLException;
}
