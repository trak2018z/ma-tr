package pl.sylwekczmil.jwt.domain.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Created by sczmil on 7/14/2017.
 */
public interface JwtUserDetails extends UserDetails {
    Map<String, Object> getAdditionalClaims();
}
