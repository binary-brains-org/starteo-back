package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentMapper {

    public Comment toRest(com.starteo.demo.repository.model.Comment comment){
        return Comment.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .idea_id(comment.getIdea().getId())
                .user_id(comment.getUser().getId())
                .build();
    }
}
