package ipass.Backend;

import ipass.Model.SendEmailTLS;
import ipass.Persistence.DataStore;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.AbstractMap;

@Path("/add")
public class ContactForm {
    @POST
    @Path("MailText")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setMailText(@FormParam("name") String nm, @FormParam("message") String message, @FormParam("email") String email
                                   ) throws IOException{
        if (!nm.equals("") && !message.equals("") && !email.equals("")) {
            SendEmailTLS manager = new SendEmailTLS();
            JsonObjectBuilder job = Json.createObjectBuilder();
//        SendEmailTLS setmail = new setMailText(nm, len, breed, hoogte, bodem, water,null,null,null,null,null,null);
            String mailText = "Naam: " + nm + "\n\nBericht: " + message + "\n\nEmail: " + email;
            String mailSubject = "Contact Formulier\n\n";
            manager.setMailText(mailText);
            manager.setMailSubject("Contact Formulier");
            SendEmailTLS.runEmail(mailSubject, mailText);
            DataStore.runData();
            return Response.ok(manager).build();
        }else {

            return Response.status(Response.Status.CONFLICT).entity(
                    new AbstractMap.SimpleEntry<String, String>("result", "Vul alle velden in!")
            ).build();
        }

    }



}
