package hu.pemik.dcs.restserver.models;

import hu.pemik.dcs.restserver.database.Model;

public class Customer extends Model {

    public String company;

    public int capacity;

    public Customer(String company, int capacity) {
        this.company = company;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Customer [ id=" + id + ", company='" + company + "', capacity=" + capacity + "]";
    }

}
