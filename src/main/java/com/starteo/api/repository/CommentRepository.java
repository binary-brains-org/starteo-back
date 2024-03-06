package com.starteo.api.repository;

import com.starteo.api.repository.model.Comment;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

  @Query(value = "select * from comment where idea_id = ?1", nativeQuery = true)
  List<Comment> findByIdeaId(String ideaId, Pageable pageable);
}