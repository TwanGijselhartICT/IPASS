package ipass.Model;


import java.security.Principal;
import java.util.ArrayList;

import java.util.List;

public class MyUser implements Principal {

    private static List<MyUser> allMyUsers = new ArrayList<>();
    private String username, plainpassword, role;


    public MyUser(String name, String pwd){
        username = name;
        plainpassword = pwd;
        role = "user";
        if (!allMyUsers.contains(this)) allMyUsers.add(this);
    }



    public void setAdmin(){
        role = "admin";
    }


    @Override
    public String getName() {
        return username;
    }

    public String getRole() {
        return role;
    }



    public static MyUser getUserByName(String uname){
        return allMyUsers.stream()
                .filter(e->e.username.equals(uname))
                .findFirst()
                .orElse(null);
    }

    public static  String validateLogin(String uname, String pwd){
        MyUser found = getUserByName(uname);
        if(found != null) return pwd.equals(found.plainpassword) ? found.getRole(): null;
        return null;
    }



}


