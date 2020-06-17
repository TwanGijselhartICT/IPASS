package ipass.security;


import ipass.Model.MyUser;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private MyUser user;
    private String schene;

    public MySecurityContext(MyUser user, String schene){
        this.user = user;
        this.schene = schene;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String role) {
        if(user.getRole()!=null){
            return role.equals(user.getRole());
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.schene);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
