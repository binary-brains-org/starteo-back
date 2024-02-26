package com.starteo.demo.repository.model;

import com.starteo.demo.repository.model.enums.IdeaStatus;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "\"idea\"")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Idea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private IdeaStatus status;

  @Column(nullable = false)
  private String description;

  private String image;

  @Column(name = "creation_datetime", nullable = false)
  private Instant creationDatetime;

  @Column(name = "updated_datetime", nullable = false)
  private Instant updatedDatetime;

  @ManyToOne()
  @JoinColumn(name = "founder")
  private User founder;
}
