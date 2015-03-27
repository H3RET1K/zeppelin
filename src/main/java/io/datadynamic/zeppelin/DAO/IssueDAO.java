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
public interface IssueDAO 
{
    @SqlQuery("SELECT "
            + "top 1 "
            + "i.id as id, "
            + "i.labels as labels, "
            + "s.name as status, "
            + "i.description as description, "
            + "i.version as version, "
            + "p.name as project, " 
            + "u.username as assignedUser, "            
            + "i.createdDate, "
            + "i.closedDate " 
            + "FROM issues i "
            + "inner join projects p on p.id = i.projectid "
            + "inner join status s on s.id = i.statusid "            
            + "inner join users u on u.id = (select top 1 id from users where username = i.assigneduser)"            
            + "where i.id = :id")
    public Issue getIssueByID(@Bind("id") long issueid);
         
    @SqlQuery("SELECT "
            + "i.id as id, "
            + "i.labels as labels, "
            + "s.name as status, "
            + "i.description as description, "
            + "i.version as version, "
            + "p.name as project, " 
            + "u.username as assignedUser, "            
            + "i.createdDate, "
            + "i.closedDate " 
            + "FROM issues i "
            + "inner join projects p on p.id = i.projectid "
            + "inner join status s on s.id = i.statusid "            
            + "inner join users u on u.id = (select top 1 id from users where username = i.assigneduser)"            
            + "where i.id = :id")
    public List<Issue> getAllIssues();
        
    @SqlUpdate("update issues set "         
            + "labels = :labels, "
            + "statusid = (select top 1 id from status where name = :status), "
            + "i.description = :description, "
            + "i.version = :version, "
            + "i.projectid = (select top 1 id from project where name = :project), "
            + "i.userid = (select top 1 id from users where username = :username), "            
            + "i.createddate = :createddate, "
            + "i.closeddate = :closeddate "
            + "where i.id = :id")
    public int updateIssue(@BindBean Issue i);
    
    @SqlUpdate("delete from issues where id = :id")
    public int deleteIssueID(@Bind("id") long id);
    
    @SqlUpdate("insert into issues(labels, statusid, description, version, projectid, userid, createddate, closeddate)"
            + "values("         
            + "labels = :labels, "
            + "statusid = (select top 1 id from status where name = :status), "
            + "i.description = :description, "
            + "i.version = :version, "
            + "i.projectid = (select top 1 id from project where name = :project), "
            + "i.userid = (select top 1 id from users where username = :username), "            
            + "i.createddate = :createddate, "
            + "i.closeddate = :closeddate)")
    public int addIssue(@BindBean Issue i);
}

