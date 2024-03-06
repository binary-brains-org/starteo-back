package com.starteo.api.endpoint.rest.mapper;

import com.starteo.api.endpoint.rest.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentMapper {

  public Comment toRest(com.starteo.api.repository.model.Comment comment) {
    return Comment.builder()
        .id(comment.getId())
        .content(comment.getContent())
        .idea_id(comment.getIdea().getId())
        .user_id(comment.getUser().getId())
        .build();
  }
}
