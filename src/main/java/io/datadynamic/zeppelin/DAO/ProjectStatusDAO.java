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
@RegisterMapper(ProjectStatusMapper.class)
public interface ProjectStatusDAO 
{
    @SqlQuery("SELECT top 1 id, name, description FROM projectstatus where id = :id")
    public ProjectStatus getProjectStatusByID(@Bind("id") long id);
    
    @SqlQuery("SELECT top 1 id, name, description FROM projectstatus wehre name = :name")
    public ProjectStatus getProjectStatusByName(@Bind("name") String name);
    
    @SqlQuery("update projectstatus set name = :name, description = :description")
    public int updateProjectStatus(@BindBean ProjectStatus ps); 
    
    @SqlQuery("insert into projectstatus (name, description) values(:name, :description)")
    public int createProjectStatus(@BindBean ProjectStatus ps);
    
    @SqlQuery("delete from projectstatus where id = :id")
    public int deleteProjectStatusByID(@Bind("id") long id);
    
    @SqlQuery("select id, name, description FROM projectstatus")
    public List<ProjectStatus> getAllProjectStatus();
        
}
