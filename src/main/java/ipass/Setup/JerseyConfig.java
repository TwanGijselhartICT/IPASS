package ipass.Setup;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ipass.setup.webservices, ipass.setup.security, ipass.setup.model");
        register(RolesAllowedDynamicFeature.class);
        System.out.println("restservices - JerseyConfig.java");


        //@TODO koppel hier met je web-resource packages
    }
}
