package hu.pemik.dcs.restserver.database;

import hu.pemik.dcs.restserver.Console;
import hu.pemik.dcs.restserver.models.Admin;
import hu.pemik.dcs.restserver.models.Customer;
import hu.pemik.dcs.restserver.models.Warehouse;
import hu.pemik.dcs.restserver.models.Worker;

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

            db.warehouse = new Warehouse("Earth", 1000);

            db.users.insert(new Customer("customer1", "customer@company.com", "Company1", 100));
            db.users.insert(new Worker("worker1", "worker@warehouse.com"));
            db.users.insert(new Admin("admin1", "admin@warehouse.com"));

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

        db.warehouse = null;
        db.users = new ArrayRepository<>();
        db.products = new ArrayRepository<>();

        db.save();

        Console.info("Database truncated\n");
    }
}
