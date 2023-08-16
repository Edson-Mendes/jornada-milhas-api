package br.com.emendes.jornadamilhasapi.config.security.filter;

import br.com.emendes.jornadamilhasapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter responsável pela autenticação via jwt.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && isTypeBearer(authorizationHeader)) {
      String token = extractToken(authorizationHeader);

      if (jwtService.isTokenValid(token)) {
        String username = jwtService.extractSubject(token);

        try {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);

          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (UsernameNotFoundException exception) {
          log.info("user {} not found", username);
        }
      }
    }

    filterChain.doFilter(request, response);
  }

  private boolean isTypeBearer(String authorizationHeader) {
    return authorizationHeader.startsWith("Bearer ");
  }

  private String extractToken(String authorizationHeader) {
    return authorizationHeader.substring(7);
  }

}
