package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.model.Authority;
import br.com.emendes.jornadamilhasapi.repository.AuthorityRepository;
import br.com.emendes.jornadamilhasapi.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link AuthorityService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorityServiceImpl implements AuthorityService {

  private final AuthorityRepository authorityRepository;

  @Override
  public Authority findByName(String authorityName) {
    log.info("attempt to find authority with name: {}", authorityName);

    return authorityRepository.findByName(authorityName).orElseThrow(() -> {
      log.info("Authority not found with name: {}", authorityName);
      return new ResourceNotFoundException("Authority not found");
    });
  }

}
