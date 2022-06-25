package dev.niko.core.sentinel.server.application.security.auth;

import static dev.niko.core.sentinel.server.shared.EnvironmentVariable.SECRET;
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

import dev.niko.core.sentinel.server.application.security.response.TokenResponse;
import dev.niko.core.sentinel.server.application.shared.Response;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final int ACCESS_TOKEN_EXPIRES_MINUTES = 480;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
        
        Access access = new Access(authentication, request);

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), 
            Response.builder()
                .data(of("tokens", getToken(access)))
                .message("Tokens created")
                .status(OK)
                .statusCode(OK.value())
            .build()
        );
    }

    private TokenResponse getToken(Access access) {
        String accessToken = createJWT(access, ACCESS_TOKEN_EXPIRES_MINUTES);
        return new TokenResponse(accessToken);
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

    private record Access(Authentication authentication, HttpServletRequest request) {}

}
