package com.starteo.demo.endpoint.rest.model;

import com.starteo.demo.repository.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
  private String id;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private Role role;
  private String image;
}
