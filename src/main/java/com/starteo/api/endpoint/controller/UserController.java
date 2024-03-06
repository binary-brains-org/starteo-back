package com.starteo.api.endpoint.controller;

import com.starteo.api.endpoint.rest.mapper.UserMapper;
import com.starteo.api.endpoint.rest.model.LoggedUser;
import com.starteo.api.endpoint.rest.model.SignIn;
import com.starteo.api.endpoint.rest.model.SignUp;
import com.starteo.api.endpoint.rest.model.User;
import com.starteo.api.service.AuthService;
import com.starteo.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
  private final UserMapper mapper;
  private final UserService userService;
  private final AuthService service;

  @PostMapping("/auth/signup")
  public LoggedUser signUp(@RequestBody SignUp auth) {
    return service.signUp(auth);
  }

  @PostMapping("/auth/login")
  public LoggedUser signIn(@RequestBody SignIn toAuthenticate) {
    return service.signIn(toAuthenticate);
  }

  @GetMapping("/auth/whoami")
  public User whoami(HttpServletRequest request) {
    return mapper.toDto(service.whoami(request));
  }

  @GetMapping("/user/{user_id}")
  public User getUserById(@PathVariable("user_id") String userId) {
    return mapper.toDto(userService.getUserById(userId));
  }
}
