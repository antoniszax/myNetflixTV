package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Υλοποιεί έναν άνθρωπο (είτε χρήστη είτε πάροχο)
 */
public class Person implements Serializable {
    private final String name, lastName, username, password;

    /**
     * Κατασκευαστής που δέχεται τα απαραίτητα για έναν άνθρωπο
     * @param name όνομα
     * @param lastName επίθετο
     * @param username όνομα χρήστη
     * @param password κωδικός χρήστη
     */
    public Person(String name, String lastName, String username, String password)
    {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }



}

