package hu.pemik.dcs.restserver.models;

import javax.ws.rs.HttpMethod;
import java.util.ArrayList;
import java.util.List;

public class Worker extends User {

    public Worker() {
    }

    public Worker(String name, String email) {
        super(name, email);
        this.setRole(User.ROLE_WORKER);
    }


    public List<String> getAccessList() {
        return new ArrayList<String>() {{
            add(HttpMethod.GET + ": products/all");
            add(HttpMethod.POST + ": products/product");
            add(HttpMethod.DELETE + ": products/product");
        }};
    }
}
