package com.starteo.demo.service;

import com.starteo.demo.endpoint.rest.model.CreateComment;
import com.starteo.demo.repository.CommentRepository;
import com.starteo.demo.repository.model.Comment;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final IdeaService ideaService;
  private final UserService userService;

  public List<Comment> getCommentsByIdea(String ideaId, Integer page, Integer pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return commentRepository.findByIdeaId(ideaId, pageable);
  }

  public List<Comment> saveCommentsOnIdea(String ideaId, List<CreateComment> comments) {
    List<Comment> commentDomain = new ArrayList<>();
    for (CreateComment comment : comments) {
      commentDomain.add(
          commentRepository.save(
              Comment.builder()
                  .id(comment.getId())
                  .content(comment.getContent())
                  .idea(ideaService.getById(ideaId))
                  .user(userService.getUserById(comment.getUserId()))
                  .build()));
    }
    return commentDomain;
  }
}
