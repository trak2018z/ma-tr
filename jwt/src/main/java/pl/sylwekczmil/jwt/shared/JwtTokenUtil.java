package pl.sylwekczmil.jwt.shared;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.sylwekczmil.jwt.domain.user.JwtUserDetails;
import pl.sylwekczmil.jwt.infrastructure.JwtProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "authorities";

    @Autowired
    private JwtProperties jwtProperties;


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof JwtUserDetails) {
            claims = ((JwtUserDetails) userDetails).getAdditionalClaims();
        }
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ROLES, getAuthorities(userDetails.getAuthorities()));
        return generateToken(claims);
    }


    public String refreshToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.get(CLAIM_KEY_USERNAME, String.class);
    }

    public UserDetails getUserDetailsFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        final String username = claims.get(CLAIM_KEY_USERNAME, String.class);
        final List<String> authorities = claims.get(CLAIM_KEY_ROLES, List.class);
        final List<GrantedAuthority> grantedAuthorities;
        grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(username, "", grantedAuthorities);
    }


    private Date getCreatedDateFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return new Date((Long) claims.get(CLAIM_KEY_CREATED));
    }

    private Date getExpirationDateFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date exp = getExpirationDateFromToken(token);
        return exp.before(new Date());
    }

    private Boolean canTokenBeRefreshed(String token, LocalDateTime lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, LocalDateTime lastPasswordReset) {
        LocalDateTime c = created.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return lastPasswordReset != null && c.isBefore(lastPasswordReset);
    }

    private List<String> getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

}
