package br.com.emendes.jornadamilhasapi.config.security;

import br.com.emendes.jornadamilhasapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configurações de beans relacionados a security.
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityBeansConfig {

  public static final String USER_NOT_FOUND_MESSAGE = "User %s not found";
  private final UserRepository userRepository;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      log.info("attempt to find user with username: {}", username);
      return userRepository.findByEmail(username).orElseThrow(() -> {
        log.info("user not found with username: {}", username);
        return new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE.formatted(username));
      });
    };
  }

}
