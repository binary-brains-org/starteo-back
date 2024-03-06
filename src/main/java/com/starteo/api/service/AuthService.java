package com.starteo.api.service;

import com.starteo.api.endpoint.rest.model.LoggedUser;
import com.starteo.api.endpoint.rest.model.SignIn;
import com.starteo.api.endpoint.rest.model.SignUp;
import com.starteo.api.repository.model.User;
import com.starteo.api.repository.model.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
  private final UserService userService;
  private final UserDetailsServiceImpl userDetailsServiceImpl;
  private final JWTService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final String AUTHORIZATION_HEADER = "Authorization";
  private final int BEARER_PREFIX_COUNT = 7;

  public LoggedUser signIn(SignIn toAuthenticate) {
    String email = toAuthenticate.getEmail();
    UserDetails principal = userDetailsServiceImpl.loadUserByUsername(email);
    if (!passwordEncoder.matches(toAuthenticate.getPassword(), principal.getPassword())) {
      throw new UsernameNotFoundException("Wrong Password!");
    }
    return LoggedUser.builder()
        .email(toAuthenticate.getEmail())
        .token(jwtService.generateToken(principal))
        .build();
  }

  @Transactional
  public LoggedUser signUp(SignUp user) {
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
                        .role(Role.USER)
                        .password(hashedPassword)
                        .build()))
            .get(0);
    UserDetails principal = userDetailsServiceImpl.loadUserByUsername(createdUser.getEmail());
    return LoggedUser.builder()
        .email(createdUser.getEmail())
        .token(jwtService.generateToken(principal))
        .build();
  }

  public User whoami(HttpServletRequest request) {
    String authHeader = request.getHeader(AUTHORIZATION_HEADER);
    String token = authHeader.substring(BEARER_PREFIX_COUNT);
    String email = jwtService.extractEmail(token);
    return userService.getUserByEmail(email);
  }
}
