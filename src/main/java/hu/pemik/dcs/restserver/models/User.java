package hu.pemik.dcs.restserver.models;

import hu.pemik.dcs.restserver.database.Model;

public class User extends Model {

    public String[] uniqueKeys = {"id", "email"};

    public String name;

    public String email;

    public boolean isAdmin = false;

    public User(String name, String email, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Customer [ id=" + id + ", name='" + name + "', email='" + email + "', isAdmin=" + isAdmin + "]";
    }

}
