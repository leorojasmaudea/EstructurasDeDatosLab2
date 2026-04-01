package org.example.storage;

/**
 * @author Leon Andres Rojas Martínez - leon.rojasm@udea.edu.co
 * @author Ulises Orozco Villegas - ulises.orozco@udea.edu.co
 */
public class User {
    long cc;
    String name;
    String email;

    public User(long cc, String name, String email) {
        this.cc = cc;
        this.name = name;
        this.email = email;
    }

    public long getCc() {
        return cc;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
