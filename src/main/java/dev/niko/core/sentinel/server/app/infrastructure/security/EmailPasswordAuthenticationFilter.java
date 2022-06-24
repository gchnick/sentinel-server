package dev.niko.core.sentinel.server.app.infrastructure.security;

import static dev.niko.core.sentinel.server.app.shared.EnvironmentVariable.SECRET;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.niko.core.sentinel.server.app.application.response.Response;
import dev.niko.core.sentinel.server.app.application.response.TokenReponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Nick Galán
 */
@RequiredArgsConstructor
@Slf4j
public class EmailPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final int ACCESS_TOKEN_EXPIRES_MINUTES = 10;
    private final int REFRESH_TOKEN_EXPIRES_MINUTES = 30;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info("Email is: {} ", email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
        
        Access access = new Access(authentication, request);

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), 
            Response.builder()
                .data(of("tokens", getTokens(access)))
                .message("Tokens created")
                .status(OK)
                .statusCode(OK.value())
            .build()
        );
    }

    private String createJWT(Access access, int minExpires) {
        User user = (User) access.authentication().getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(System.getenv(SECRET.name()).getBytes());
        return JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + minExpires * 60 * 1000))
            .withIssuer(access.request().getRequestURL().toString())
            .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .sign(algorithm);
    }

    private TokenReponse getTokens(Access access) {
        String accessToken = createJWT(access, ACCESS_TOKEN_EXPIRES_MINUTES);
        String refreshToken = createJWT(access, REFRESH_TOKEN_EXPIRES_MINUTES);
        return new TokenReponse(accessToken, refreshToken);
    }

    private record Access(Authentication authentication, HttpServletRequest request) {}

}
