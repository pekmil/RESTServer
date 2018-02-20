package hu.pemik.dcs.restserver.endpoints;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author pekmil
 */
public class UserSecurityContext implements SecurityContext {

    private final UserPrincipal principal;
    
    public UserSecurityContext(String userName){
        this.principal = new UserPrincipal(userName);
    }
    
    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String string) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    class UserPrincipal implements Principal {

        private final String userName;
        
        public UserPrincipal(String userName){
            this.userName = userName;
        }
        
        @Override
        public String getName() {
            return this.userName;
        }
        
    }
    
}
