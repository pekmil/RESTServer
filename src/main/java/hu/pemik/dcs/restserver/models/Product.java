package hu.pemik.dcs.restserver.models;

import hu.pemik.dcs.restserver.database.Model;

public class Product extends Model {

    public String name;

    public String description;

    public int quantity;

    public boolean cooled = false;

    public int customerId;

    public int locationId;

    public Product(String name, String description, int quantity, boolean cooled, int customerId, int locationId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.cooled = cooled;
        this.customerId = customerId;
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Product [ id=" + id + ", name='" + name + "', description='" + description + "', quantity=" + quantity + " cooled=" + cooled + ", customerId=" + customerId + ", locationId=" + locationId + " ]";
    }

}
