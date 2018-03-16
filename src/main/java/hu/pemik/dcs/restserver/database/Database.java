package hu.pemik.dcs.restserver.database;

import hu.pemik.dcs.restserver.model.entity.Todo;

import java.io.*;

public class Database implements Serializable {

    /**
     * Location of the database file
     */
    private static final String filePath = "/rendsz/RESTServer/db.ser";

    /**
     * List of available repositories
     * These rows are like "table" definitions in a database
     */
    public Repository<Todo> todos = new ArrayRepository<>();

    private static final long serialVersionUID = 1;

    /**
     * Singleton static instance variable
     */
    private static Database db = null;

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
            FileInputStream inputFile = new FileInputStream(Database.filePath);
            ObjectInputStream stream = new ObjectInputStream(inputFile);
            db = (Database) stream.readObject();
            stream.close();
        } catch (Exception e) {
            db = new Database();
            db.save();
            System.out.println("An Exception was thrown while loading the Database:\n " + e.getMessage());
        }
    }

    /**
     * Serialize the database instance variable and
     * save the seriazlized object to Database.filePath
     */
    public void save() {
        try {
            FileOutputStream outputFile = new FileOutputStream(Database.filePath);
            ObjectOutputStream stream = new ObjectOutputStream(outputFile);
            stream.writeObject(db);
            stream.close();
        } catch (Exception e) {
            System.out.println("An Exception was thrown while saving the Database:\n " + e.getMessage());
        }
    }

    /**
     * Override toString to show User object atributes
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Database toString...";
    }

}