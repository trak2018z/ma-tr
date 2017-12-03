package pl.sylwekczmil.jwt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sylwekczmil.jwt.shared.JwtService;
import pl.sylwekczmil.jwt.web.dto.JwtAuthenticationRequest;

/**
 * Created by sczmil on 6/7/2017.
 */

@ConditionalOnProperty(prefix = "jwt", name = "authLink")
@RestController
@RequestMapping("${jwt.authLink}")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(jwtService.createAuthenticationToken(authenticationRequest));
    }
}
