package progi.dugonogiprogi.radnovrijeme.backend.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import progi.dugonogiprogi.radnovrijeme.backend.rest.security.filter.CustomAuthenticationFilter;
import progi.dugonogiprogi.radnovrijeme.backend.rest.security.filter.CustomAuthorizationFilter;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    EmployeeDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/").permitAll();
       /* http.authorizeRequests().antMatchers("/login","/jobs").permitAll();
        http.authorizeRequests().antMatchers(POST, "/jobs/**").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(DELETE, "/jobs/**").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(POST, "/register").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(GET, "/groups").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(POST, "/groups/**").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(GET, "/occupancy").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(POST, "/occupancy").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(GET, "/map").hasAuthority("ROLE_OWNER");
        http.authorizeRequests().antMatchers(GET, "/myData").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_LEADER");
        http.authorizeRequests().antMatchers(GET, "/workhoursinput").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_LEADER");
        http.authorizeRequests().antMatchers(POST, "/workhoursinput").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_LEADER");
        http.authorizeRequests().antMatchers(GET, "/tasks", "/tasks/add").hasAuthority("ROLE_LEADER");
        http.authorizeRequests().antMatchers(POST, "/tasks/add").hasAuthority("ROLE_LEADER");
        http.authorizeRequests().anyRequest().authenticated();*/

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name="corsConfigurationSource")
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/", "https://radno-vrijeme-fe.herokuapp.com/"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "content-type", "x-requested-with",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "x-auth-token", "x-app-id", "Authorization",
                "Origin","Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setExposedHeaders(Arrays.asList("accessToken", "roles"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
