package pl.sylwekczmil.jwt.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.sylwekczmil.jwt.web.dto.JwtAuthenticationRequest;
import pl.sylwekczmil.jwt.web.dto.JwtAuthenticationResponse;

/**
 * Created by sczmil on 6/6/2017.
 */
@Service
public class JwtService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    public JwtAuthenticationResponse createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }
}
