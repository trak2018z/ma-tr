package pl.sylwekczmil.jwt.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sczmil on 7/14/2017.
 */
public class JwtUser extends User implements JwtUserDetails, Authentication {

    private Map<String, Object> additionalClaims = new HashMap<>();

    public JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Map<String, Object> additionalClaims) {
        super(username, password, authorities);
        this.additionalClaims = additionalClaims;
    }

    @Override
    public Map<String, Object> getAdditionalClaims() {
        return additionalClaims;
    }

    @Override
    public Object getCredentials() {
        return super.getPassword();
    }

    @Override
    public Object getDetails() {
        return this;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return super.isEnabled();
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getName() {
        return super.getUsername();
    }
}
