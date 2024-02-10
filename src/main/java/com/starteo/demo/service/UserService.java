package com.starteo.demo.service;

import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.repository.UserRepository;
import com.starteo.demo.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {
  private final UserRepository repository;
  private final UserMapper userMapper;
  public List<User> saveUsers(List<User> users) {
    return repository.saveAll(users);
  }
  public User getUserByEmail(String email) {
    return repository.findByEmail(email).orElse(null);
  }

  public com.starteo.demo.endpoint.rest.model.User getByEmail(String email){
    return userMapper.toUser(repository.findByEmail(email).orElse(null));
  }

}
