/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.Controllers;

import io.datadynamic.zeppelin.DAO.ProjectDAO;
import io.datadynamic.zeppelin.DAO.Project;
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
 * @author Admin
    http://127.0.0.1:8081/projects/ID/1/Update?name=test&description=test&projectstatus=open
    http://127.0.0.1:8081/projects/test/Add?description=test&projectstatus=open
 */
@Path("projects")
public class ProjectsController {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Viewable index() {
        try {
            Handle connectionHandle = DB.getDataSource();        
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            List<Project> projects = u.getAllProjects();
            connectionHandle.close();
            return new Viewable("/index.mustache", projects);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }    
    
    @GET
    @Path("{projectName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectByName(@PathParam("projectName") String projectName) {
        try {
            Handle connectionHandle = DB.getDataSource();        
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            Project project = u.getProjectByName(projectName);
            connectionHandle.close();
            return project;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }        
    }
    
    @GET
    @Path("ID/{projectID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectByID(@PathParam("projectID") long projectID) {
        try {
            Handle connectionHandle = DB.getDataSource();        
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            Project project = u.getProjectByID(projectID);
            connectionHandle.close();
            return project;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }        
    }
    
    @GET
    @Path("*")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProjects() {
        try {
            Handle connectionHandle = DB.getDataSource();        
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            List<Project> projects = u.getAllProjects();
            connectionHandle.close();
            return projects;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }        
    }
    
    @GET
    @Path("ID/{id}/Update")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean updateProject(
        @PathParam("id") long id, 
        @QueryParam("name") String name, 
        @QueryParam("description") String description, 
        @QueryParam("projectstatus") String projectstatus
    ) {
        try {
            Handle connectionHandle = DB.getDataSource();
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            u.updateProject(new Project(id, name, description, projectstatus));
            connectionHandle.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }        
    }
    
    @GET
    @Path("{projectName}/Add")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean addProject(        
        @PathParam("projectName") String name, 
        @QueryParam("description") String description, 
        @QueryParam("projectstatus") String projectstatus
    ) {
        try {
            Handle connectionHandle = DB.getDataSource();
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            u.addProject(new Project(0, name, description, projectstatus));
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
    public Boolean deleteProject(
        @PathParam("id") long id,
        @DefaultValue("false") @QueryParam("ui") Boolean ui
    ) {
        try {
            Handle connectionHandle = DB.getDataSource();
            ProjectDAO u = connectionHandle.attach(ProjectDAO.class);
            u.deleteProjectByID(id);
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