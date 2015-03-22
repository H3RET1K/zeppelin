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
    @SqlQuery("SELECT top 1 i.id, i.labels, s.name as status, i.description, i.version, p.name as project, u.username as user, is.note, i.createddate, i.closeddate FROM issues i "
            + "inner join projects p on p.id = i.projectid"
            + "inner join status s on s.id = i.statusid "
            + "inner join issuenotes is on is.id = i.noteid "
            + "inner join users u on u.id = i.assigneduser "            
            + "where i.id = :id")
    public Project getIssueByID(@Bind("id") String name);
     
    @SqlQuery("SELECT top 1 i.id, i.labels, s.name as status, i.description, i.version, p.name as project, u.username as user, is.note, i.createddate, i.closeddate FROM issues i "
            + "inner join projects p on p.id = i.projectid"
            + "inner join status s on s.id = i.statusid "
            + "inner join issuenotes is on is.id = i.noteid "
            + "inner join users u on u.id = i.assigneduser "            
            + "order by i.id")
    public List<Issue> getAllIssues();
        
    @SqlUpdate("update issues i set "         
            + "i.labels = :labels, "
            + "i.statusid = (select top 1 id from status where id = :statusid), "
            + "i.description = :description, "
            + "i.version = :version, "
            + "i.projectid = (select top 1 id from project where id = :projectid), "
            + "i.userid = (select top 1 id from users where id = :userid), "
            + "i.noteid = (select top 1 id from notes where id = :noteid), "
            + "i.createddate = :createddate, "
            + "i.closeddate = :closeddate "          
            + "where i.id = :id")
    public int updateIssue(@BindBean Issue i);         

//    @SqlUpdate("insert into issues")
//    public int addIssue(@BindBean Issue i);           

    @SqlUpdate("delete from issues where id = :id")
    public int deleteIssueID(@Bind("id") long id);
}
 
//+ "(id identity, "
//+ "labels varchar(140), "
//+ "statusid bigint, "
//+ "description varchar(max), "
//+ "version varchar(140), "
//+ "projectid bigint, "
////+ "attachments blob, "
//+ "assigneduser varchar(140), "
//+ "noteid bigint, "
//+ "createddate datetime, "
//+ "closeddate datetime, "


//+ "ADD FOREIGN KEY (projectid) "
//+ "REFERENCES public.projects(id)");
//
//
//+ "ADD FOREIGN KEY (statusid) "
//+ "REFERENCES public.status(id)");
//
//
//+ "ADD FOREIGN KEY (noteid) "
//+ "REFERENCES public.issuenotes(id)");
//
//
//+ "ADD FOREIGN KEY (assigneduser) "
//+ "REFERENCES public.users(id)");