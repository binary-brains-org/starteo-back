package com.starteo.demo.service;

import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.endpoint.rest.model.Principal;
import com.starteo.demo.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserService userService;
  private final UserMapper userMapper;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getByEmail(username);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("User of email: " + username + " not found");
    }
    return Principal.builder().user(userMapper.toCheckPassword(user)).build();
  }
}
