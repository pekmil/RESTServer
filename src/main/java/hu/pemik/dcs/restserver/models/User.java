package hu.pemik.dcs.restserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.pemik.dcs.restserver.database.Model;

import java.util.List;

abstract public class User extends Model {

    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_WORKER = "worker";

    public static final String ROLE_CUSTOMER = "customer";

    @JsonIgnore
    public String[] uniqueKeys = {"id", "email"};

    public String name;

    public String email;

    public String role = "guest";

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract List<String> getAccessList();

    public final boolean isAuthorized(String method, String path) {
        return this.getAccessList().contains(method + ": " + path);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User [ id=" + id + ", name='" + name + "', email='" + email + "', role=" + role + "]";
    }

}
