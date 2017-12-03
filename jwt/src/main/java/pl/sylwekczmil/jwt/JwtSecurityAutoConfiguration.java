package pl.sylwekczmil.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pl.sylwekczmil.jwt.infrastructure.JwtAuthenticationEntryPoint;
import pl.sylwekczmil.jwt.infrastructure.JwtAuthenticationTokenFilter;
import pl.sylwekczmil.jwt.infrastructure.JwtProperties;

import java.util.Arrays;
import java.util.List;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize
@ComponentScan("pl.sylwekczmil.jwt")
public class JwtSecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(JwtSecurityAutoConfiguration.class);


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtSecurityAutoConfiguration() {
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // allow anonymous resource requests
        List<String> notCheckedLinks = this.jwtProperties.getNotCheckedLinks();
        notCheckedLinks.addAll(Arrays.asList("/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.woff",
                "/**/*.woff2",
                "/**/*.eot",
                "/**/*.ttf",
                "/**/*.map",
                "/**/*.scss"));
        web.ignoring().antMatchers(notCheckedLinks.stream().filter(l -> !l.isEmpty()).toArray(String[]::new));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        List<String> notAuthenticatedLinks = jwtProperties.getNotAuthenticatedLinks();
        if (jwtProperties.getAuthLink() != null) {
            notAuthenticatedLinks.add(jwtProperties.getAuthLink());
        }
        httpSecurity
                // since you are not relying on cookies, you don't need to protect against cross site requests
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and()
                // don't create session (on app) 
                //               never – the framework will never create a session itself but it will use one if it already exists
                //               stateless – no session will be created or used by Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //              specify custom requirements for URLs
                .authorizeRequests()
                .antMatchers(notAuthenticatedLinks.stream().filter(l -> !l.isEmpty()).toArray(String[]::new)).permitAll()
                .anyRequest().authenticated();
        // Custom JWT based security filter
        httpSecurity // 5 of 11 filters
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching headers:
//      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
//      Pragma: no-cache
//      Expires: 0
        httpSecurity.headers().cacheControl();
    }
}
