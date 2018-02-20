package hu.pemik.dcs.restserver.endpoints;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pekmil
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if(crc.getMethod().equals(HttpMethod.OPTIONS)) return;
        try{
            List<String> headerValues = crc.getHeaders().get(HttpHeaders.AUTHORIZATION);
            String headerValue = headerValues.get(0);
            if(headerValue.toLowerCase().matches("token \\w+")){
                String userName = headerValue.split(" ")[1];
                crc.setSecurityContext(new UserSecurityContext(userName));
            }
            else{
                throw new IllegalStateException("No authorization header present!");
            }
        }
        catch(Exception ex){
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    
}
