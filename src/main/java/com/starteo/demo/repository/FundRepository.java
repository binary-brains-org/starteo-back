package com.starteo.demo.repository;

import com.starteo.demo.repository.model.Fund;
import com.starteo.demo.repository.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundRepository extends JpaRepository<Fund, String> {
    List<Fund> findAllByIdea(Idea idea);
}
