package com.starteo.demo.repository;

import com.starteo.demo.repository.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String>{

    List<Comment> findCommentsByIdea_Id(String ideaId, Pageable pageable);
}
