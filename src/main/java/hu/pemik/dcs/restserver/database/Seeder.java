package hu.pemik.dcs.restserver.database;

import hu.pemik.dcs.restserver.Console;
import hu.pemik.dcs.restserver.models.Customer;
import hu.pemik.dcs.restserver.models.Product;
import hu.pemik.dcs.restserver.models.User;
import hu.pemik.dcs.restserver.models.Warehouse;

public class Seeder {

    /**
     * Truncate db, then seed
     */
    public static void reseed() {
        truncate();
        seed();
    }

    /**
     * Seed database with initial data
     */
    public static void seed() {
        Console.info("Seeding database...");
        Database db = Database.connect();

        try {

            db.warehouses.insert(new Warehouse("Earth", 1000));

            db.customers.insert(new Customer("Company1", 100));

            db.users.insert(new User("tbence", "admin@warehouse.com", true));

            db.products.insert(new Product("Tej", "1,5% UHT Tej", 100, true, 1, 1));

        } catch (Exception e) {
            Console.highlight("Failed to seed the database: \n" + e.getMessage());
        }

        // Save changes
        db.save();
        Console.info("Database seeded");
    }

    /**
     * Clear out the whole database
     */
    public static void truncate() {
        Console.info("\nTruncate database...");

        Database db = Database.connect();

        db.warehouses = new ArrayRepository<>();
        db.users = new ArrayRepository<>();
        db.customers = new ArrayRepository<>();
        db.products = new ArrayRepository<>();

        db.save();

        Console.info("Database truncated\n");
    }
}
