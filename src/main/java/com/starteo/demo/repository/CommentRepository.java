package com.starteo.demo.repository;

import com.starteo.demo.repository.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String>{

    @Query(value = "select * from comment where idea_id = ?1",nativeQuery = true)
    List<Comment> findByIdeaId(String ideaId, Pageable pageable);
}
