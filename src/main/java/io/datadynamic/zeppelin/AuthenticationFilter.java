package io.datadynamic.zeppelin;

import io.datadynamic.zeppelin.DAO.User;
import io.datadynamic.zeppelin.DAO.UserDAO;
import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.server.ContainerRequest;
import org.skife.jdbi.v2.Handle;

@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

    // Exception thrown if user is unauthorized.
    private final static WebApplicationException unauthorized =
        new WebApplicationException(
            Response.status(Status.UNAUTHORIZED)
                    .header(
                        HttpHeaders.WWW_AUTHENTICATE,
                        "Basic realm=\"realm\""
                    )
                    .entity("Page requires login.").build());    
    
    @Inject
    javax.inject.Provider<UriInfo> uriInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) 
            throws IOException {
        String authentication;
        authentication = 
            ((ContainerRequest) containerRequestContext)
            .getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authentication == null) {
            throw unauthorized;
        }
        if (!authentication.toLowerCase().startsWith("basic ")) {
            throw unauthorized;
            // "Only HTTP Basic authentication is supported"
        }
        authentication = authentication.substring("basic ".length());
        String[] values = Base64.decodeAsString(authentication).split(":");
        if (values.length < 2) {
            // "Invalid syntax for username and password"
            throw unauthorized;
        }
        String username = values[0];
        String password = values[1];
        if ((username == null) || (password == null)) {
            // "Missing username or password"
            throw unauthorized;
        }
        
        User authenticatingUser;
        try {
            Handle connectionHandle = DB.getDataSource();
            UserDAO u = connectionHandle.attach(UserDAO.class);
            authenticatingUser = u.getUserByName(username);
            connectionHandle.close();            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw unauthorized;
        }
        
        if (!BCrypt.checkpw(password, authenticatingUser.getPassword())) {
            System.out.println("Unauthorized.");
            throw unauthorized;
        }
        
        containerRequestContext.setSecurityContext(
            new Authorizer(authenticatingUser)
        );
    }
    
    public class Authorizer implements SecurityContext {        
        final private Principal principal;
        private User user;
        
        public Authorizer(final User user) {
            this.user = user;
            this.principal = new Principal() {
                @Override
                public String getName() {
                    return user.getUsername();
                }
            };
        }

        @Override
        public Principal getUserPrincipal() {
            return this.principal;
        }

        @Override
        public boolean isUserInRole(String role) {            
            return (user.getRole().equals(role));
        }

        @Override
        public boolean isSecure() {
            return "https".equals(uriInfo.get().getRequestUri().getScheme());
        }

        @Override
        public String getAuthenticationScheme() {
            return SecurityContext.BASIC_AUTH;
        }
    }    
}