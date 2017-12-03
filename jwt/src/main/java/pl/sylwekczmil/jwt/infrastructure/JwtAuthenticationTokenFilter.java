package pl.sylwekczmil.jwt.infrastructure;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.sylwekczmil.jwt.shared.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader(jwtProperties.getHeader());
        if (authToken != null && !authToken.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                logger.debug("checking authentication for user " + username);
                UserDetails userDetails = null;
                if (jwtProperties.isDownloadUserDetailsFromDatabaseForEveryRequestActive()) {
                    userDetails = userDetailsService.loadUserByUsername(username);
                } else {
                    userDetails = jwtTokenUtil.getUserDetailsFromToken(authToken);
                }

                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.debug("authenticated user " + username + ", setting security context");
                    //refresh
                    response.setHeader(jwtProperties.getHeader(), jwtTokenUtil.generateToken(userDetails));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (UsernameNotFoundException ex) {
                logger.error("jwt username not found token:" + authToken, ex);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            } catch (MalformedJwtException ex) {
                logger.error("jwt malformed token:" + authToken, ex);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization token malformed, token:" + authToken);
                return;
            } catch (Exception ex) {
                logger.error("jwt unknown exception token:" + authToken, ex);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
