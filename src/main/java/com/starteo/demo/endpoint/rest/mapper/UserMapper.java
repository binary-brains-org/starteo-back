package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

  @Value("${aws.s3.bucket}")
  private String bucketName;

  public User toDto(com.starteo.demo.repository.model.User entity) {
    return User.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .username(entity.getUsername())
        .role(entity.getRole())
        .image(
            entity.getImage() != null
                ? "https://" + bucketName + ".s3.amazonaws.com/" + entity.getImage()
                : null)
        .build();
  }

  public User toCheckPassword(com.starteo.demo.repository.model.User entity) {
    return User.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .password(entity.getPassword())
        .username(entity.getUsername())
        .role(entity.getRole())
        .image(
            entity.getImage() != null
                ? "https://" + bucketName + ".s3.amazonaws.com/" + entity.getImage()
                : null)
        .build();
  }
}
