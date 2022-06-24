package dev.niko.core.sentinel.server.app.application.auth;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static dev.niko.core.sentinel.server.app.shared.EnvironmentVariable.SECRET;
import static java.util.Map.of;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.idforideas.pizzeria.user.User;
import com.idforideas.pizzeria.user.UserService;

import dev.niko.core.sentinel.server.app.application.response.Response;
import dev.niko.core.sentinel.server.app.application.response.TokenReponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Nick Galán
 */
@RestController
@RequestMapping("/api/v1/auth/token")
@RequiredArgsConstructor
public class AuthResource {
    private final UserService userService;

    /**
     * Renueva el token de acceso sin necesidad de iniciar session de nuevo. Necesitas enviar el <code>refresh token</code> en la cabecera de la solicitud.
     */
    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken  = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(System.getenv(SECRET.name()).getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = userService.get(email);
                String accessToken = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(OK.value());

                new ObjectMapper().writeValue(response.getOutputStream(), 
                    Response.builder()
                        .data(of("tokens", new TokenReponse(accessToken, refreshToken)))
                        .message("Token created")
                        .status(OK)
                        .statusCode(OK.value())
                    .build());
               
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), 
                    Response.builder()
                        .message("Error trying to refresh token")
                        .errors(of("exception", e.getMessage()))
                        .status(FORBIDDEN)
                        .statusCode(FORBIDDEN.value())
                    .build());
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
    
}
