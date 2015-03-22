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
 * @author H3RET1K
 */
public class IssueNoteMapper implements ResultSetMapper<IssueNote> 
{
     public IssueNote map(int index, ResultSet r, StatementContext ctx) throws SQLException
     {
         return new IssueNote(r.getLong("id"), r.getLong("userid"), r.getString("note"), r.getString("datecreated"));
     }    
}
