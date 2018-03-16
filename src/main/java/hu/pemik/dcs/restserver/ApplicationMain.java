package hu.pemik.dcs.restserver;

import hu.pemik.dcs.restserver.database.Database;

public class ApplicationMain {

    public static void main(String[] args) {
        Database db = Database.connect();
        db.dump();

        ServiceServer server = new ServiceServer();
    }

}
