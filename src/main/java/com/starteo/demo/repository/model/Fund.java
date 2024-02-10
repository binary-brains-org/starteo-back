package com.starteo.demo.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"fund\"")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Fund {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private int value;

  @ManyToOne
  private Idea idea;

  @ManyToOne
  private User user;
}
