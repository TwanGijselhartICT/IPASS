package ipass.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import ipass.Model.MyUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;
import java.util.AbstractMap.SimpleEntry;


@Path("/authentication")
public class AuthenticationResource {
//    AquariumManager manager = AquariumManager.getManager();


    final static public Key key = MacProvider.generateKey();

    private String createToken(String username,  String role) throws JwtException{
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);
//        manager.setUser(username);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUserByPassword(@FormParam("username") String username, @FormParam("password") String password){
        try{
            String role = MyUser.validateLogin(username, password);


            if(role==null) throw new IllegalArgumentException("No user found!");
            String token = createToken(username, role);

            SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token);
            return Response.ok(JWT).build();

        }catch(JwtException | IllegalArgumentException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}

