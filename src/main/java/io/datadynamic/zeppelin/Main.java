package io.datadynamic.zeppelin;

import java.io.FileInputStream;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.Properties;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;

/**
 * Main class.
 *
 */
public class Main {    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @param webURI
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(URI webURI) {      
        // create a resource config that scans for JAX-RS resources and providers
        // in io.datadynamic.zeppelin package
        final ResourceConfig rc = new ResourceConfig()
                .packages("io.datadynamic.zeppelin");        
        rc.property(
                MustacheMvcFeature.TEMPLATE_BASE_PATH, "Views"
        ).register(
                MustacheMvcFeature.class
        );
     
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(webURI, rc);     
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Properties prop = new Properties();
        
        try {               
            prop.load(new FileInputStream("app.properties"));   
        } catch (Exception ex) {
            System.out.println("Cannot read app.properties file");
            System.exit(1); 
        }
        
        String port = prop.getProperty("Port");     
        String uri = prop.getProperty("URI");
        URI webURI = UriBuilder
            .fromUri(uri).port(Integer.parseInt(port)).build();
        
        ClassLoader cl = ClassLoader.getSystemClassLoader(); 
        URL[] urls = ((URLClassLoader)cl).getURLs();
        for(URL url: urls){
        	System.out.println(url.getFile());
        }        
        
        DatabaseBuilder.createDatabase();
        final HttpServer server = startServer(webURI);
        // add in static file handler for js, css, html, etc...
        server.getServerConfiguration().addHttpHandler(
            new StaticHttpHandler("static"),
            "/static"
        );
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", webURI));
        System.in.read();
        server.stop();
    }
}

