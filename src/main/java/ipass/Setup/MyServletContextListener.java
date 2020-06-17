package ipass.Setup;

import ipass.Model.MyUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Applicatie is aan het opstarten!");
        System.out.println("Initialiseer hier objecten, of laad alvast data");


        new MyUser("twan", "1234");  //les9
        new MyUser("twannus", "1234").setAdmin();  //les9



    }


    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Applicatie gaat afsluiten");
        System.out.println("Ruim objecten op, of sla data op");
    }
}
