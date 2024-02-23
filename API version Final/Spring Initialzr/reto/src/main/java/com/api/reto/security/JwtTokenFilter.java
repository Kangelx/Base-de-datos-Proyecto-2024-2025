package com.api.reto.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {
    private final String secretKey = "ahadeguerraisotermavolatilcharcobarcocabezaapiverdeazulrojanegraverdeabuelomiguelhindurainunicornioaccesoadatossistemasdegestionempresarial";
    private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            log.debug("Procesando token JWT: {}", token); // Añadido log de depuración
            try {
                Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
                Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                Claims claims = claimsJws.getBody();
                String username = claims.getSubject();
                log.debug("Username extraído del token JWT: {}", username); // Añadido log de depuración

                List<String> roles = claims.get("roles", List.class);
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList());
                log.debug("Autoridades extraídas del token JWT: {}", authorities); // Añadido log de depuración

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.debug("Contexto de seguridad establecido con éxito para el usuario: {}", username); // Añadido log de depuración
            } catch (JwtException e) {
                log.error("Error al procesar el token JWT: {}", e.getMessage()); // Mejorado manejo de excepciones
                SecurityContextHolder.clearContext();
            }
        } else {
            log.debug("No se encontró token de autenticación en la solicitud."); // Añadido log de depuración
        }
        filterChain.doFilter(request, response);
    }
}