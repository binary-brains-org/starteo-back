package com.starteo.api.repository;

import com.starteo.api.repository.model.Fund;
import com.starteo.api.repository.model.Idea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<Fund, String> {
  List<Fund> findAllByIdea(Idea idea);
}
