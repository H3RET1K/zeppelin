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
@RegisterMapper(ProjectMapper.class)
public interface ProjectDAO
{
    @SqlQuery("SELECT top 1 p.id, p.name, p.description, ps.description as projectstatus FROM projects p inner join projectstatus ps on p.projectstatusid = ps.id where name = :name")
    public Project getProjectByName(@Bind("name") String name);
    
    @SqlQuery("SELECT top 1 p.id, p.name, p.description, ps.description as projectstatus FROM projects p inner join projectstatus ps on p.projectstatusid = ps.id where p.id = :id")
    public Project getProjectByID(@Bind("id") long id);
    
    @SqlQuery("SELECT p.id, p.name, p.description, ps.description as projectstatus FROM projects p inner join projectstatus ps on p.projectstatusid = ps.id order by p.id")
    public List<Project> getAllProjects();
        
    @SqlUpdate("update projects p set p.name = :name, p.description = :description, p.projectstatusid = (select top 1 id from projectstatus ps where ps.description = :projectStatus) where p.id = :id")
    public int updateProject(@BindBean Project p);
    //public int updateProjectByID(@Bind("id") long id, @Bind("name") String name, @Bind("description") String description, @Bind("projectstatus") String projectstatus);        

    @SqlUpdate("insert into projects(name, description, projectstatusid) values (:name, :description, (select top 1 id from projectstatus ps where ps.description = :projectStatus))")
    public int addProject(@BindBean Project p);
    //public int updateProjectByID(@Bind("id") long id, @Bind("name") String name, @Bind("description") String description, @Bind("projectstatus") String projectstatus);        

    @SqlUpdate("delete from projects where id = :id")
    public int deleteProjectByID(@Bind("id") long id);
    
    @SqlUpdate("update projects p set p.projectstatusid = (select top 1 id from projectstatus ps where ps.description = :projectstatus) where p.id = :projectid")
    public int setProjectStatusByProjectStatusName(@Bind("projectid") long projectid, @Bind("projectstatus") String projectstatus);
    
    @SqlUpdate("update projects p set p.projectstatusid = :projectstatusid where p.id = :projectid")
    public int setProjectStatusByProjectStatusID(@Bind("projectid") long projectid, @Bind("projectstatusid") long projectstatusid);    
}