package hu.pemik.dcs.restserver.models;

import javax.ws.rs.HttpMethod;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    public String company;

    public int capacity;

    public Customer() {
    }

    public Customer(String name, String email, String company, int capacity) {
        super(name, email);
        this.setRole(User.ROLE_CUSTOMER);

        this.company = company;
        this.capacity = capacity;
    }

    public List<String> getAccessList() {
        return new ArrayList<String>() {{
            add(HttpMethod.GET + ": products/all");
        }};
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "User [ id=" + id + ", name='" + name + "', email='" + email + "', role='" + role + "', company='" + company + "', capacity='" + capacity + "']";
    }

}
