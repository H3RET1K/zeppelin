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
    @SqlQuery(
        "SELECT is.id, is.userid, is.note, is.dateCreated, u.username as userName "
        + "FROM issuenotes is inner join users u on is.userID = u.id where id = :id")
    public List<IssueNote> getIssueNotesByID(@Bind("id") long id);
        
    @SqlUpdate(
        "update issuenotes set note = :note, datecreated = :dateCreated, userid = :userID where id = :id")
    public int updateIssueNote(@BindBean IssueNote p);          

    @SqlUpdate("insert into issuenotes(note, datecreated, userid) values (:note, :dateCreated, :userid)")
    public int addIssueNote(@BindBean IssueNote p);

    @SqlUpdate("delete from issuenotes where id = :id")
    public int deleteIssueNoteByID(@Bind("id") long id);   
}
