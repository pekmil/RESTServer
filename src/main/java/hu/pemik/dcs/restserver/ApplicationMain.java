package hu.pemik.dcs.restserver;

import hu.pemik.dcs.restserver.database.Database;
import hu.pemik.dcs.restserver.database.Seeder;

public class ApplicationMain {

    public static void main(String[] args) {

        Database db = Database.connect();

        if (args.length > 0 && args[0].equals("--reseed")) {
            Seeder.reseed();
        }

        db.dump();

        ServiceServer server = new ServiceServer();
    }

}
