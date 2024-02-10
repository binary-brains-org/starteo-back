package com.starteo.demo.repository.model;

import com.starteo.demo.endpoint.rest.model.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String username;

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastname;

  @Column(unique = true)
  private String email;

  private String password;
}
