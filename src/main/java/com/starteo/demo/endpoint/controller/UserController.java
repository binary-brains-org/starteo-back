package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.endpoint.rest.model.LoggedUser;
import com.starteo.demo.endpoint.rest.model.SignUp;
import com.starteo.demo.endpoint.rest.model.Signin;
import com.starteo.demo.endpoint.rest.model.UserInfo;
import com.starteo.demo.service.AuthService;
import com.starteo.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
  private final UserMapper mapper;
  private final UserService userService;
  private final AuthService service;

  @PostMapping("/auth/signup")
  public UserInfo signUp(@RequestBody SignUp auth) {
    return mapper.toDto(service.signUp(auth));
  }

  @PostMapping("/auth/login")
  public LoggedUser signIn(@RequestBody Signin toAuthenticate) {
    return service.signIn(toAuthenticate);
  }

  @GetMapping("/auth/whoami")
  public UserInfo whoami(HttpServletRequest request) {
    return mapper.toDto(service.whoami(request));
  }

  @GetMapping("/user/{user_id}")
  public UserInfo getUserById(@PathVariable("user_id") String userId){
    return mapper.toDto(userService.getUserById(userId));
  }
}
