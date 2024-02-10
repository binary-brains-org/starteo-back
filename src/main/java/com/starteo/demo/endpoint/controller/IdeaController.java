package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.CommentMapper;
import com.starteo.demo.endpoint.rest.mapper.IdeaMapper;
import com.starteo.demo.endpoint.rest.model.Comment;
import com.starteo.demo.endpoint.rest.model.CreateComment;
import com.starteo.demo.endpoint.rest.model.CreateIdea;
import com.starteo.demo.endpoint.rest.model.Idea;
import com.starteo.demo.service.CommentService;
import com.starteo.demo.service.IdeaService;
import com.starteo.demo.service.utils.InstanceTime;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

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
            @RequestParam(value = "idea_name",required = false) String ideaName) {
        return ideaService.getIdeasByDate(page,pageSize,ideaName).stream().map(ideaMapper::toRest).toList();
    }
    @GetMapping("/ideas/{idea_id}")
    public Idea getById(@PathVariable(name = "idea_id")String idIdea) {
        return ideaMapper.toRest(ideaService.getById(idIdea));
    }

    @GetMapping("/lastIdeas")
    public List<Idea> getIdeasByLastWeek(
            @RequestParam("page") Integer page,
            @RequestParam("page_size") Integer pageSize) {
        return ideaService.getIdeasByWeek(InstanceTime.getCurrentMondayOfTheWeek(),InstanceTime.getCurrentSundayOfTheWeek(),page,pageSize)
                .stream().map(ideaMapper::toRest).toList();

    }

    @PutMapping("/ideas")
    public List<Idea> crupdateIdeas(@RequestBody List<CreateIdea> ideas) {
        return ideaService
                .saveIdeas(ideas.stream().map(ideaMapper::toDomain).toList())
                .stream().map(ideaMapper::toRest).toList();
    }

    @GetMapping("/ideas/{idea_id}/comments")
    public List<Comment> getCommentsOnIdea(
            @RequestParam("page") Integer page,
            @RequestParam("page_size") Integer pageSize,
            @PathVariable(value = "idea_id") String ideaId){
        return commentService.getCommentsByIdea(ideaId,page,pageSize).stream().map(commentMapper::toRest).toList();
    }

    @PutMapping("/ideas/{idea_id}/comments")
    public List<Comment> saveCommentsOnIdea(@PathVariable(name = "idea_id")String idIdea, @RequestBody List<CreateComment> createComment){
        return commentService.saveCommentsOnIdea(idIdea,createComment).stream().map(commentMapper::toRest).toList();
    }
}
