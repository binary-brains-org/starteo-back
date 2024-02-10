package com.starteo.demo.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
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
    private String status;
    @Column(nullable = false)
    private String description;
    private String image;
    @Column(name = "creation_datetime",nullable = false)
    private Instant creationDatetime;
    @Column(name = "updated_datetime",nullable = false)
    private Instant updatedDatetime;
    @ManyToOne()
    @JoinColumn(name = "founder")
    private User founder;

}
