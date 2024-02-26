package com.starteo.demo.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUp {
  private String username;
  private String email;
  private String id;
  private String password;
  private String firstname;
  private String lastname;
}
