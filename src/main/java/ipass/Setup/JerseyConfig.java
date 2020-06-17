package ipass.Setup;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ipass.webservices, ipass.security, ipass.model");
        register(RolesAllowedDynamicFeature.class);



        //@TODO koppel hier met je web-resource packages
    }
}
