package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.exception.EmailAlreadyInUseException;
import br.com.emendes.jornadamilhasapi.exception.PasswordsDoNotMatchException;
import br.com.emendes.jornadamilhasapi.mapper.UserMapper;
import br.com.emendes.jornadamilhasapi.model.Authority;
import br.com.emendes.jornadamilhasapi.model.User;
import br.com.emendes.jornadamilhasapi.repository.UserRepository;
import br.com.emendes.jornadamilhasapi.service.AuthorityService;
import br.com.emendes.jornadamilhasapi.service.ImageService;
import br.com.emendes.jornadamilhasapi.service.UserService;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementação de {@link UserService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private static final String AUTHORITY_USER = "USER";
  private static final String EMAIL_IN_USER_MESSAGE = "E-mail {%s} is already in use";

  private final AuthorityService authorityService;
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final ImageService imageService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserResponse save(CreateUserRequest createUserRequest, MultipartFile image) {
    if (!createUserRequest.password().equals(createUserRequest.confirmPassword())) {
      throw new PasswordsDoNotMatchException("password and confirmPassword do not match");
    }
    if (userRepository.existsByEmail(createUserRequest.email())) {
      throw new EmailAlreadyInUseException(EMAIL_IN_USER_MESSAGE.formatted(createUserRequest.email()));
    }

    User user = userMapper.toUser(createUserRequest);
    user.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    Authority authority = authorityService.findByName(AUTHORITY_USER);
    user.addAuthority(authority);

    URI uri = imageService.save(image);
    user.setImage(uri);

    user.setPassword(passwordEncoder.encode(createUserRequest.password()));

    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }

}
