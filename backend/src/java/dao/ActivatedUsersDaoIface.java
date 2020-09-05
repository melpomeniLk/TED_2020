/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Activated_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
public interface ActivatedUsersDaoIface {
    public List<Activated_Users> list() throws SQLException;
    public Activated_Users find(long id) throws SQLException;
    public List<Activated_Users> search(String column, String value) throws SQLException;
    public boolean create(Activated_Users activatedUser) throws SQLException;
    public boolean delete(long id) throws SQLException;
}
