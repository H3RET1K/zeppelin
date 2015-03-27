/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.Controllers;

import io.datadynamic.zeppelin.DAO.IssueDAO;
import io.datadynamic.zeppelin.DAO.Issue;
import io.datadynamic.zeppelin.DB;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.mvc.Viewable;
import org.skife.jdbi.v2.Handle;

/**
 *
 * @author H3RET1K
 */
@Path("issues")
public class IssuesController {
//    NO DOA FOR ISSUE BY NAME; SHOULD WE CALL THEM BY NAME SINCE THERE COULD BE MANY? 
//    @GET
//    @Path("{issueName}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Issue getIssueByName() {
//       throw new UnsupportedOperationException();
//    }
    
    @GET
    @Path("ID/{issueID}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Issue getIssueByID(@PathParam("issueID") long issueID) {
        try {
            Handle connectionHandle = DB.getDataSource();
            IssueDAO i = connectionHandle.attach(IssueDAO.class);
            Issue issue = i.getIssueByID(issueID);
            connectionHandle.close();
            return issue;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
     
    @GET
    @Path("*")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Issue> getAllIssues() {
        try {
            Handle connectionHandle = DB.getDataSource();
            IssueDAO i = connectionHandle.attach(IssueDAO.class);
            List<Issue> issue = i.getAllIssues();
            connectionHandle.close();
            return issue;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }            
    }
         
    @GET
    @Path("ID/{id}/Update")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean updateIssue(
        @PathParam("id") long id,
        @QueryParam("labels") String labels,
        @QueryParam("status") String status,
        @QueryParam("description") String description,
        @QueryParam("version") String version,
        @QueryParam("project") String project,
        @QueryParam("assignedUser") String assignedUser,
        @QueryParam("createdDate") String createdDate,
        @QueryParam("closedDate") String closedDate    
    ) {
        try {
            Handle connectionHandle = DB.getDataSource();
            IssueDAO i = connectionHandle.attach(IssueDAO.class);
            i.updateIssue(new Issue(id, labels, status, description, version, project, assignedUser, createdDate, closedDate));
            connectionHandle.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }   
    }
        
    @GET
    @Path("{issueName}/Add")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean addIssue(
        @PathParam("issueName") String description,
        @QueryParam("labels") String labels,
        @QueryParam("status") String status,
        @QueryParam("version") String version,
        @QueryParam("project") String project,
        @QueryParam("assignedUser") String assignedUser,
        @QueryParam("createdDate") String createdDate,
        @QueryParam("closedDate") String closedDate          
    ) {
        try {
            Handle connectionHandle = DB.getDataSource();
            IssueDAO i = connectionHandle.attach(IssueDAO.class);
            i.addIssue(new Issue(0, labels, status, description, version, project, assignedUser, createdDate, closedDate));
            connectionHandle.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        
        
    }    
    
    @GET
    @Path("ID/{id}/Delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteIssue(
        @PathParam("id") long id,
        @DefaultValue("false") @QueryParam("ui") Boolean ui
    ) {
       try {
           Handle connectionHandle = DB.getDataSource();
           IssueDAO i = connectionHandle.attach(IssueDAO.class);
           i.deleteIssueID(id);
           connectionHandle.close();
           if(ui == true) {
               //return Response.status(200).entity(new Viewable("/index", "FOO")).build();               
           }
           return true;
       } catch (Exception ex) {
           System.out.println(ex);
           return false;
       }
    } 
}
