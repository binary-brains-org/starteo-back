package com.starteo.demo.repository;

import com.starteo.demo.repository.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, String> {
}
