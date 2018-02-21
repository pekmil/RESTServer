package hu.pemik.dcs.restserver.endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author pekmil
 */
@ServerEndpoint(value = "/notification")
public class WebSocketNotificationEndpoint {
    
    private static final List<Session> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected: " + session.getId());
        SESSIONS.add(session);
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
        SESSIONS.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        System.err.println(error.getMessage());
    }
    
    public static void sendNotification(String msg){
        SESSIONS.stream().forEach(s -> {
            try{
                s.getBasicRemote().sendText(msg);
            }
            catch(IOException ioe){
                System.err.println(ExceptionUtils.getStackTrace(ioe));
            }
        });
        
    }
    
}
