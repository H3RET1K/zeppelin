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
@RegisterMapper(IssueNoteMapper.class)
public interface IssueNoteDAO 
{
    @SqlQuery("SELECT top 1 is.id, is.userid, is.note, is.createddate, u.username FROM issuenotes is inner join users u on is.userid = u.id where id = :id")
    public Project getIssueNoteByID(@Bind("name") long id);
        
    @SqlQuery("SELECT is.id, is.userid, is.note, is.createddate, u.username FROM issuenotes is inner join users u on is.userid = u.id order by is.id")
    public List<IssueNote> getAllIssueNotes();
        
    @SqlUpdate("update issuenotes is set is.note = :note, is.createddate = :createddate, is.userid = (select top 1 id from users where id = :userid) where is.id = :id")
    public int updateIssueNotes(@BindBean Project p);          

    @SqlUpdate("insert into issuenotes(note, createddate, userid) values (:note, :createddate, (select top 1 id from users where id = :userid))")
    public int addIssueNote(@BindBean Project p);   

    @SqlUpdate("delete from issuenotes where id = :id")
    public int deleteIssueNoteByID(@Bind("id") long id);
    
//    @SqlUpdate("update projects p set p.projectstatusid = (select top 1 id from projectstatus ps where ps.description = :projectstatus) where p.id = :projectid")
//    public int setProjectStatusByProjectStatusName(@Bind("projectid") long projectid, @Bind("projectstatus") String projectstatus);
//    
//    @SqlUpdate("update projects p set p.projectstatusid = :projectstatusid where p.id = :projectid")
//    public int setProjectStatusByProjectStatusID(@Bind("projectid") long projectid, @Bind("projectstatusid") long projectstatusid);    
}
