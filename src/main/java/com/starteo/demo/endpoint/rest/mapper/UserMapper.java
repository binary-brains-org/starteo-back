package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

  public UserInfo toDto(com.starteo.demo.repository.model.User entity) {
    return UserInfo.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .build();
  }
  public LoggedUser toDto(com.starteo.demo.repository.model.User entity, String token){
    return LoggedUser.builder()
            .email(entity.getEmail())
            .token(token)
            .build();
  }
  public User toUser(com.starteo.demo.repository.model.User entity){
    return User.builder()
            .id(entity.getId())
            .firstname(entity.getFirstname())
            .lastname(entity.getLastname())
            .email(entity.getEmail())
            .username(entity.getUsername())
            .password(entity.getPassword())
            .image(entity.getImage())
            .build();
  }

  public com.starteo.demo.repository.model.User toDomain(SignUp register){
    return com.starteo.demo.repository.model.User.builder()
            .id(register.getId())
            .email(register.getEmail())
            .firstname(register.getFirstname())
            .lastname(register.getLastname())
            .username(register.getUsername())
            .password(register.getPassword())
            .build();
  }
}
