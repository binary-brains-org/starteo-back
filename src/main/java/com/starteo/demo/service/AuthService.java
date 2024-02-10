package com.starteo.demo.service;

import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.endpoint.rest.model.*;
import com.starteo.demo.repository.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {
  private final UserService userService;
  private final UserMapper userMapper;
  private final UserDetailsServiceImpl userDetailsServiceImpl;
  private final JWTService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final String AUTHORIZATION_HEADER = "Authorization";
  private final int BEARER_PREFIX_COUNT = 7;

  public LoggedUser signIn(Signin toAuthenticate) {
    String email = toAuthenticate.getEmail();
    UserDetails principal = userDetailsServiceImpl.loadUserByUsername(email);
    if (!passwordEncoder.matches(toAuthenticate.getPassword(), principal.getPassword())) {
      throw new UsernameNotFoundException("Wrong Password!");
    }
    return LoggedUser.builder()
            .id(userService.getUserByEmail(toAuthenticate.getEmail()).getId())
            .email(toAuthenticate.getEmail())
            .token(jwtService.generateToken(principal))
            .build();
  }

  @Transactional
  public User signUp(SignUp user) {
    String email = user.getEmail();
    User existingUser = userService.getUserByEmail(email);
    if (Objects.nonNull(existingUser)) {
      throw new DuplicateKeyException("User with the email address: " + email + " already exists.");
    }
    String hashedPassword = passwordEncoder.encode(user.getPassword());
    User createdUser =
        userService
            .saveUsers(
                List.of(
                    User.builder()
                        .username(user.getUsername())
                            .firstname(user.getFirstname())
                            .lastname(user.getLastname())
                        .email(user.getEmail())
                        .password(hashedPassword)
                        .build()))
            .get(0);

    return createdUser;
  }

  public User whoami(HttpServletRequest request) {
    String authHeader = request.getHeader(AUTHORIZATION_HEADER);
    String token = authHeader.substring(BEARER_PREFIX_COUNT);
    String email = jwtService.extractEmail(token);
    return userService.getUserByEmail(email);
  }
}
