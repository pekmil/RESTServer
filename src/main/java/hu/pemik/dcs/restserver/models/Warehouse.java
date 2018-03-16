package hu.pemik.dcs.restserver.models;

import hu.pemik.dcs.restserver.database.Model;

public class Warehouse extends Model {

    public String address;

    // Capacity (e.g.: max 1000)
    public int capacity;

    // derived attribute: numberOfShelves = capacity / 100

    public Warehouse(String address, int capacity) {
        this.address = address;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Customer [ id=" + id + ", address='" + address + "', capacity=" + capacity + "]";
    }

}
