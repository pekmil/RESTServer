package hu.pemik.dcs.restserver;

import hu.pemik.dcs.restserver.endpoints.WebSocketNotificationEndpoint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.tyrus.server.Server;

/**
 *
 * @author pekmil
 */
public class ApplicationMain {

    public static void main(String[] args) {
        try{
            ServiceServer server = new ServiceServer();
            runWebSocketServer();
        }
        catch(Throwable t) {
            System.err.println("Unhandled runtime exception:\n" + ExceptionUtils.getStackTrace(t));
        }
    }
    
    public static void runWebSocketServer() {
        Server server = new Server("localhost", 8025, "/ws", null, WebSocketNotificationEndpoint.class);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
