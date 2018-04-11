package hu.pemik.dcs.restserver.endpoints;

import hu.pemik.dcs.restserver.models.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class UserSecurityContext implements SecurityContext {

    private final UserPrincipal principal;

    public UserSecurityContext(User user) {
        this.principal = new UserPrincipal(user);
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return this.principal.user.role.equals(User.ROLE_ADMIN) || this.principal.user.role.equals(role);
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    class UserPrincipal implements Principal {

        private final User user;

        public UserPrincipal(User user) {
            this.user = user;
        }

        public User getUser() {
            return this.user;
        }

        @Override
        public String getName() {
            return this.user.email;
        }

    }

}
