package com.starteo.demo.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"comment\"")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String content;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Idea idea;

    @ManyToOne()
    @JoinColumn(name = "idea_id")
    private User user;

}
