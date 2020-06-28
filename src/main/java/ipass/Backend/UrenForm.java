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
import java.util.AbstractMap;

@Path("/uren")
public class UrenForm {
    @POST
    @Path("MailText")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setMailText(@FormParam("uren") int aantal, @FormParam("werkGever") String nm, @FormParam("message") String message, @FormParam("date") String date
    ){
        if (aantal > 0 && !nm.equals("") && !message.equals("")) {
            SendEmailTLS manager = new SendEmailTLS();
            JsonObjectBuilder job = Json.createObjectBuilder();
//        SendEmailTLS setmail = new setMailText(nm, len, breed, hoogte, bodem, water,null,null,null,null,null,null);
            String mailText = "Werkgever: " + nm + "\n\nDatum: " + date+ "\n\nAantal gewerkte uren: " + aantal +"\n\nBericht: " + message;
            String mailSubject = "Urenregistratie\n\n";
            manager.setMailText(mailText);
            manager.setMailSubject("Urenregistratie");
            SendEmailTLS.runEmail(mailSubject, mailText);
            return Response.ok(manager).build();
        }else {
            return Response.status(Response.Status.CONFLICT).entity(
                    new AbstractMap.SimpleEntry<String, String>("result", "Vul alle velden in!")
            ).build();
        }

    }



}

