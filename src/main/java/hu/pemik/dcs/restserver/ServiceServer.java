package hu.pemik.dcs.restserver;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class ServiceServer {

    private final HttpServer server;

    public ServiceServer() {
        final ResourceConfig rc = new ResourceConfig().packages("hu.pemik.dcs.restserver.endpoints");

        rc.register(new ApplicationBinder());
        rc.register(JacksonFeature.class);

        this.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(Config.REST_SERVER_URL), rc);

        Console.info(String.format("\n\nServer started at: %s", Config.REST_SERVER_URL));
    }

}
