package api;

import java.util.ArrayList;

public class Registration {

    private final String name, lastName, username, password;

    public Registration()
    {
        name="";
        lastName="";
        username="";
        password="";
    }

    public void registerUser(String name, String lastName, String username, String password)
    {
        User user = new User(name, lastName, username, password);
    }
}


