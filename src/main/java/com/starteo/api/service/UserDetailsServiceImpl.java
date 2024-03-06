package com.starteo.api.service;

import com.starteo.api.endpoint.rest.mapper.UserMapper;
import com.starteo.api.endpoint.rest.model.Principal;
import com.starteo.api.repository.model.User;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    return Principal.builder().user(userMapper.toUserDetails(user)).build();
  }
}
