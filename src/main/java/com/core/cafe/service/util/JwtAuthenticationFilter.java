package com.core.cafe.service.util;

import com.core.cafe.service.beans.CafeUserDetails;
import com.core.cafe.service.beans.TokenValidationResponse;
import com.core.cafe.service.model.User;
import com.core.cafe.service.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final List<String> SKIP_AUTH_URIS = Arrays.asList(new String[]{"/*.html", "/**/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.xlsx", "/v1/authenticate", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/swagger-ui/**", "/configuration/security", "/webjars/**", "/health", "/loggers", "/loggers/Service_Application", "/info", "/auditevents", "/metrics", "/beans", "/caches", "/conditions", "/configprops", "/env", "/flyway", "/httptrace", "/integrationgraph", "/liquibase", "/mappings", "/scheduledtasks", "/sessions", "/shutdown", "/threaddump", "/heapdump", "/prometheus", "/logfile", "/jolokia", "/metrics/**", "/loggers/**", "/v3/api-docs/**", "/error"});


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            if(SKIP_AUTH_URIS.contains(httpServletRequest.getRequestURI())){
                return;
            }
            String authtoken = httpServletRequest.getHeader("authtoken");

            if(!StringUtils.isEmpty(authtoken)){
                throw new CafeServiceException("You are not authorized",HttpStatus.UNAUTHORIZED);
            }
                HttpRequest validateTokenRequest = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:9090/validate"))
                        .header("authtoken", authtoken)
                        .POST(HttpRequest.BodyPublishers.ofString(""))
                        .build();

                HttpResponse<String> httpResponse = HttpClient.newBuilder().build().send(validateTokenRequest, HttpResponse.BodyHandlers.ofString());
                String responseBody = httpResponse.body();
                ObjectMapper mapper = new ObjectMapper();
                TokenValidationResponse tokenValidationResponse = mapper.readValue(responseBody, TokenValidationResponse.class);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getUsernamePasswordAuthenticationToken(tokenValidationResponse);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        catch (Exception ex){
            SecurityContextHolder.getContext().setAuthentication((Authentication) null);
            throw new CafeServiceException("You are not authorized",HttpStatus.UNAUTHORIZED);
        }
    }

    private static UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(TokenValidationResponse tokenValidationResponse) {
        String status = tokenValidationResponse.getStatus();
        if(StringUtils.isEmpty(status) || !status.equals("VALID")){
            throw new CafeServiceException("You are not authorized",HttpStatus.UNAUTHORIZED);

        }
        User user = new User();
        user.setEmail(tokenValidationResponse.getUserName());
        user.setUserrole(tokenValidationResponse.getRole());
        CafeUserDetails cafeUserDetails = new CafeUserDetails(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(cafeUserDetails,null,cafeUserDetails.getAuthorities());
        return usernamePasswordAuthenticationToken;
    }
}