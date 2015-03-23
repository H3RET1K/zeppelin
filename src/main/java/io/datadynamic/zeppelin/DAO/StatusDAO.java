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
 * @author H3RET1K
 */
@RegisterMapper(StatusMapper.class)
public interface StatusDAO 
{
     
    @SqlQuery("SELECT top 1 id, name, description FROM status where id = :id")
    public ProjectStatus getStatusByID(@Bind("id") long id);
    
    @SqlQuery("SELECT top 1 id, name, description FROM status where name = :name")
    public ProjectStatus getStatusByName(@Bind("name") String name);
    
    @SqlUpdate("update status set name = :name, description = :description where id = :id")
    public int updateStatus(@BindBean ProjectStatus ps); 
    
    @SqlUpdate("insert into status (name, description) values(:name, :description)")
    public int createStatus(@BindBean ProjectStatus ps);
    
    @SqlUpdate("delete from status where id = :id")
    public int deleteStatusByID(@Bind("id") long id);
    
    @SqlQuery("select id, name, description FROM status order by id")
    public List<Status> getAllStatus();
            
}
