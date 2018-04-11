package hu.pemik.dcs.restserver.database;

import hu.pemik.dcs.restserver.Config;
import hu.pemik.dcs.restserver.Console;
import hu.pemik.dcs.restserver.models.Product;
import hu.pemik.dcs.restserver.models.User;
import hu.pemik.dcs.restserver.models.Warehouse;

import java.io.*;

public class Database implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * Singleton static instance variable
     */
    private static Database db = null;

    /**
     * List of available repositories
     * These rows are like "table" definitions in a database
     */
    public Warehouse warehouse; // Singleton...

    public Repository<User> users = new ArrayRepository<>();

    public Repository<Product> products = new ArrayRepository<>();

    /**
     * Private constructor (part of the singleton pattern)
     */
    private Database() {
    }

    /**
     * "connect" to the db equals get singleton instance
     *
     * @return Database
     */
    public static Database connect() {
        if (db == null) {
            db = new Database();
            Database.load();
        }
        return db;
    }

    /**
     * "connect" to the db equals get singleton instance
     *
     * @return Database
     */
    public static Database query() {
        return Database.connect();
    }

    /**
     * Deserialize the Database object located at Database.filePath
     * and assign it to the singleton instance variable.
     */
    private static void load() {
        try {
            FileInputStream inputFile = new FileInputStream(Config.DATABASE_FILE);
            ObjectInputStream stream = new ObjectInputStream(inputFile);
            db = (Database) stream.readObject();
            stream.close();
        } catch (Exception e) {
            Seeder.seed();
            Console.info("An Exception was thrown while loading the Database:\n " + e.getMessage());
        }
    }

    /**
     * Serialize the database instance variable and
     * save the serialized object to Database.filePath
     */
    public void save() {
        try {
            FileOutputStream outputFile = new FileOutputStream(Config.DATABASE_FILE);
            ObjectOutputStream stream = new ObjectOutputStream(outputFile);
            stream.writeObject(db);
            stream.close();
        } catch (Exception e) {
            System.out.println("An Exception was thrown while saving the Database:\n " + e.getMessage());
        }
    }

    public void dump() {
        System.out.print(db);
    }

    /**
     * Override toString to show User object attributes
     *
     * @return String
     */
    @Override
    public String toString() {
        return Console.infoString("DATABASE:\n") +
                Console.titleString(warehouse.toString()) + "\n" +
                Console.titleString("Users") + users + "\n" +
                Console.titleString("Products") + products + "\n";
    }

}