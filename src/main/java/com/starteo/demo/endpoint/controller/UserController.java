package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.endpoint.rest.model.LoggedUser;
import com.starteo.demo.endpoint.rest.model.SignUp;
import com.starteo.demo.endpoint.rest.model.Signin;
import com.starteo.demo.endpoint.rest.model.UserInfo;
import com.starteo.demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
  private final UserMapper mapper;
  private final AuthService service;

  @PostMapping("/signup")
  public UserInfo signUp(@RequestBody SignUp auth) {
    return mapper.toDto(service.signUp(auth));
  }

  @PostMapping("/login")
  public LoggedUser signIn(@RequestBody Signin toAuthenticate) {
    return service.signIn(toAuthenticate);
  }

  @GetMapping("/whoami")
  public UserInfo whoami(HttpServletRequest request) {
    return mapper.toDto(service.whoami(request));
  }
}
