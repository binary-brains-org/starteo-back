package com.starteo.api.endpoint.controller;

import com.starteo.api.endpoint.rest.mapper.CommentMapper;
import com.starteo.api.endpoint.rest.mapper.IdeaMapper;
import com.starteo.api.endpoint.rest.model.Comment;
import com.starteo.api.endpoint.rest.model.CreateComment;
import com.starteo.api.endpoint.rest.model.CreateIdea;
import com.starteo.api.endpoint.rest.model.Idea;
import com.starteo.api.service.CommentService;
import com.starteo.api.service.IdeaService;
import com.starteo.api.service.utils.InstanceTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class IdeaController {
  private IdeaMapper ideaMapper;
  private CommentMapper commentMapper;
  private IdeaService ideaService;
  private CommentService commentService;

  @GetMapping("/ideas")
  public List<Idea> getIdeasByTime(
      @RequestParam("page") Integer page,
      @RequestParam("page_size") Integer pageSize,
      @RequestParam(value = "idea_name", required = false) String ideaName) {
    return ideaService.getIdeasByDate(page, pageSize, ideaName).stream()
        .map(ideaMapper::toRest)
        .toList();
  }

  @GetMapping("/ideas/{idea_id}")
  public Idea getById(@PathVariable(name = "idea_id") String idIdea) {
    return ideaMapper.toRest(ideaService.getById(idIdea));
  }

  @GetMapping("/lastIdeas")
  public List<Idea> getIdeasByLastWeek(
      @RequestParam("page") Integer page, @RequestParam("page_size") Integer pageSize) {
    return ideaService
        .getIdeasByWeek(
            InstanceTime.getCurrentMondayOfTheWeek(),
            InstanceTime.getCurrentSundayOfTheWeek(),
            page,
            pageSize)
        .stream()
        .map(ideaMapper::toRest)
        .toList();
  }

  @PutMapping("/ideas")
  public List<Idea> crupdateIdeas(@RequestBody List<CreateIdea> ideas) {
    return ideaService.saveIdeas(ideas.stream().map(ideaMapper::toDomain).toList()).stream()
        .map(ideaMapper::toRest)
        .toList();
  }

  @GetMapping("/ideas/{idea_id}/comments")
  public List<Comment> getCommentsOnIdea(
      @RequestParam("page") Integer page,
      @RequestParam("page_size") Integer pageSize,
      @PathVariable(value = "idea_id") String ideaId) {
    return commentService.getCommentsByIdea(ideaId, page, pageSize).stream()
        .map(commentMapper::toRest)
        .toList();
  }

  @PutMapping("/ideas/{idea_id}/comments")
  public List<Comment> saveCommentsOnIdea(
      @PathVariable(name = "idea_id") String idIdea,
      @RequestBody List<CreateComment> createComment) {
    return commentService.saveCommentsOnIdea(idIdea, createComment).stream()
        .map(commentMapper::toRest)
        .toList();
  }
}
