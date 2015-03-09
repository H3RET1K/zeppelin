/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.datadynamic.zeppelin.Controllers;

import io.datadynamic.zeppelin.DAO.User;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import org.glassfish.jersey.server.mvc.Viewable;

/**
 *
 * @author Admin
 */
@Path("/")
public class ProjectsController {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Viewable a() {
        return new Viewable("/index.mustache");
    }
    
    @GET
    @Path("/project/{projectName}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable showProjectFilter(@PathParam("projectName") String projectName) {
        return new Viewable("/filter.mustache");
    }   
}