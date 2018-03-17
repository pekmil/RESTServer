package hu.pemik.dcs.restserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.pemik.dcs.restserver.database.Model;

public class User extends Model {

    @JsonIgnore
    public String[] uniqueKeys = {"id", "email"};

    public String name;

    public String email;

    public boolean isAdmin = false;

    public User() {
    }

    public User(String name, String email, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String[] getUniqueKeys() {
        return uniqueKeys;
    }

    public void setUniqueKeys(String[] uniqueKeys) {
        this.uniqueKeys = uniqueKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User [ id=" + id + ", name='" + name + "', email='" + email + "', isAdmin=" + isAdmin + "]";
    }

}
