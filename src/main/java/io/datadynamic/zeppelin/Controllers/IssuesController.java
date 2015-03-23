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
 * @author vgoff
 */
@Path("issues")
public class IssuesController {
    @GET
    @Path("{issueName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Issue getIssueByName() {
       throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("ID/{issueID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Issue getIssueByID() {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("*")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Issue> getAllIssues() {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("ID/{id}/Update")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean updateIssue() {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("{issueName}/Add")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean addIssue() {
        throw new UnsupportedOperationException();
    }    
    
    @GET
    @Path("ID/{id}/Delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteIssue() {
       throw new UnsupportedOperationException();
    }       
}
