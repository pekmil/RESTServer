package hu.pemik.dcs.restserver.models;

import java.util.List;

public class Admin extends Worker {

    public Admin() {
    }

    public Admin(String name, String email) {
        super(name, email);
        this.setRole(User.ROLE_ADMIN);
    }

    public List<String> getAccessList() {
        List<String> accessList = super.getAccessList();

        // Extend worker accessList...
        // accessList.add(HttpMethod.DELETE + ": products/product");

        return accessList;
    }

}
