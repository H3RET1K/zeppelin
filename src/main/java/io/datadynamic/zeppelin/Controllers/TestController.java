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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import org.glassfish.jersey.server.mvc.Viewable;

/**
 *
 * @author Admin
 */
@Path("test")
public class TestController {
    @GET
    @Path("currentuser")
    @Produces(MediaType.APPLICATION_XML)
    public User currentuser(@Context SecurityContext secCtx) {        
        return ((User)secCtx.getUserPrincipal());
    }   
    
    @GET
    @Path("a")
    @Produces(MediaType.TEXT_HTML)
    public Viewable a() {
        io.datadynamic.zeppelin.ViewModels.TestViewModel model = 
                new io.datadynamic.zeppelin.ViewModels.TestViewModel();
        model.setSomeProperty("this is from the TestViewModel bean");
        return new Viewable("/AView.mustache", model);
    }
    
    @GET
    @Path("b")
    @Produces(MediaType.TEXT_HTML)
    public Viewable b() {
        final HashMap<String, Object> properties = new HashMap<>();
        ArrayList<String> ar = new ArrayList<>();
        ar.add("test1");
        ar.add("test2");
        ar.add("test3");        
        properties.put("foo", ar.toArray(new String[ar.size()]));
        properties.put("bar", new String("test"));
        return new Viewable("/BView.mustache", properties);
    }    
 
    @GET
    @Path("c")
    @Produces(MediaType.APPLICATION_JSON)
    public io.datadynamic.zeppelin.ViewModels.TestViewModel c() {
        io.datadynamic.zeppelin.ViewModels.TestViewModel model = 
                new io.datadynamic.zeppelin.ViewModels.TestViewModel();
        model.setSomeProperty("this is from the TestViewModel bean");
        return model;
    } 

}
