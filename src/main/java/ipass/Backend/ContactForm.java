package ipass.Backend;

import ipass.Model.SendEmailTLS;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/add")
public class ContactForm {
    @POST
    @Path("MailText")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setMailText(@FormParam("name") String nm, @FormParam("message") String message, @FormParam("email") String email
                                   ){
        SendEmailTLS manager = new SendEmailTLS();
        JsonObjectBuilder job = Json.createObjectBuilder();
//        SendEmailTLS setmail = new setMailText(nm, len, breed, hoogte, bodem, water,null,null,null,null,null,null);
        String mailText = "Naam: "+ nm +"\n\nBericht: " + message +"\n\nEmail: " + email;
        String mailSubject = "Offerte aanvraag Damsteeg Productions";
        manager.setMailText(mailText);
        manager.setMailSubject("Offerte aanvraag Damsteeg Productions");
        SendEmailTLS.runEmail(mailSubject, mailText);
        return Response.ok(manager).build();


    }



}
