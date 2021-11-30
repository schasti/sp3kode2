package api;

import controller.JWTHandler;
import model.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        //Hvis det ikke er login siden udføre vi kontrol af token
        if (!"login".equals(containerRequestContext.getUriInfo().getPath()) && !"aftaler".equals(containerRequestContext.getUriInfo().getPath())) {
            if (containerRequestContext.getHeaderString("Authorization") == null) {
                throw new WebApplicationException("fejl", 401);
            }
            User user = JWTHandler.validate(containerRequestContext.getHeaderString("Authorization"));
        }
    }
}