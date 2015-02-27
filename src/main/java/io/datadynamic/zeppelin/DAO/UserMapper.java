/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author vgoff
 */
public class UserMapper implements ResultSetMapper<User>
{
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new User(r.getLong("id"), r.getString("username"), r.getString("password"), r.getString("role"));
    }
}   