package hu.pemik.dcs.restserver.models;

import hu.pemik.dcs.restserver.database.Model;

public class Customer extends Model {

    public String name;

    public String company;

    public int capacity;

    public Customer() {
    }

    public Customer(String company, int capacity) {
        this.company = company;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Customer [ id=" + id + ", company='" + company + "', capacity=" + capacity + "]";
    }

}
