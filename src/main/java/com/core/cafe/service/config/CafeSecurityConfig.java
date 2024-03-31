package com.core.cafe.service.config;


import com.core.cafe.service.service.CustomUserDetailsService;
import com.core.cafe.service.util.CustomAccessDeniedHandler;
import com.core.cafe.service.util.CustomAuthenticationEntryPoint;
import com.core.cafe.service.util.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CafeSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SKIP_AUTH_URIS = new String[]{"/*.html", "/**/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.xlsx", "/v1/authenticate", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/swagger-ui/**", "/configuration/security", "/webjars/**", "/health", "/loggers", "/loggers/Service_Application", "/info", "/auditevents", "/metrics", "/beans", "/caches", "/conditions", "/configprops", "/env", "/flyway", "/httptrace", "/integrationgraph", "/liquibase", "/mappings", "/scheduledtasks", "/sessions", "/shutdown", "/threaddump", "/heapdump", "/prometheus", "/logfile", "/jolokia", "/metrics/**", "/loggers/**", "/v3/api-docs/**", "/error"};

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(SKIP_AUTH_URIS).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(customAccessDeniedHandler);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
