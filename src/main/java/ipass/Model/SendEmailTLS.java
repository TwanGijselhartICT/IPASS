package ipass.Model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailTLS {
    private String installatienaam;
    private String mailsubject;
    private String mailtext;


    public void setMailSubject(String input){
        mailsubject = input;
        System.out.println("setMailSubject: "+ mailsubject);
    }

    public String getMailSubject(){
        System.out.println("getMailSubject: "+ mailsubject);
        return mailsubject;
    }

    public void setMailText(String input){
        mailtext= input;
        System.out.println("setMailText: "+ mailtext);
    }

    public String getMailText(){
        System.out.println("getMailText: "+ mailtext);
        return mailtext;
    }

    public static void runEmail(String subject, String text) {
        SendEmailTLS manager = new SendEmailTLS();
        final String username = "damsteegwebsite@gmail.com";
        final String password = "damsteegproductions";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("damsteegwebsite@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("damsteegwebsite@gmail.com")
            );
            message.setSubject(subject);
            message.setText(subject + text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}