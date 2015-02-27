package io.datadynamic.zeppelin;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    private static final URI BASE_URI = UriBuilder
            .fromUri("http://127.0.0.1/").port(8080).build();    
    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {      
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
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);     
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        DatabaseBuilder.createDatabase();
        final HttpServer server = startServer();
        // add in static file handler for js, css, html, etc...
        server.getServerConfiguration().addHttpHandler(
            new StaticHttpHandler("static"),
            "/static"
        );
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

