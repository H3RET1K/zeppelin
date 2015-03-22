/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.DAO;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author vgoff
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO 
{
    @SqlQuery("SELECT top 1 id, username, password, role FROM users where username = :username")
    public User getUserByName(@Bind("username") String username);
    
     @SqlQuery("SELECT top 1 id, username, password, role FROM users where id = :id")
    public User getUserById(@Bind("id") long id);
    
    @SqlUpdate("update users set username = :username, password = :password, role = :role")
    public int updateUser(@BindBean User u); 
    
    @SqlUpdate("insert into users (username, password, role) values(:username, :password, :role)")
    public int createUser(@BindBean User u);
    
    @SqlUpdate("delete from users where id = :id")
    public int deleteUserById(@Bind("id") long id);
    
    @SqlQuery("select id, username, password, role FROM users order by username")
    public List<User> getAllUser();
}

