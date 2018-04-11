package hu.pemik.dcs.restserver.endpoints;

import hu.pemik.dcs.restserver.Console;
import hu.pemik.dcs.restserver.database.Database;
import hu.pemik.dcs.restserver.models.User;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if (crc.getMethod().equals(HttpMethod.OPTIONS)) return;
        try {
            List<String> headerValues = crc.getHeaders().get(HttpHeaders.AUTHORIZATION);
            String headerValue = headerValues.get(0);
            if (headerValue.toLowerCase().matches("token \\w+@\\w+.\\w+")) {
                String email = headerValue.split(" ")[1];

                if (!Database.query().users.where("email", email).exists()) {
                    Console.alert("User not exists: " + email);
                    throw new RuntimeException("User not found!");
                }

                User user = Database.query().users.where("email", email).get();
                crc.setSecurityContext(new UserSecurityContext(user));

                String path = crc.getUriInfo().getPath();
                String[] parts = path.split("/");

                path = parts[0] + "/" + parts[1];
                String method = crc.getMethod();

                if (method.equals(HttpMethod.GET) && path.equals("auth/login")) {
                    return;
                }

                if (!user.isAuthorized(method, path)) {
                    throw new RuntimeException("Unauthorized to access: " + method + ": " + path);
                }

            } else {
                throw new IllegalStateException("No authorization header present!");
            }
        } catch (Exception ex) {
            Console.alert("AUTH WARNING: " + ex.getMessage());
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}
