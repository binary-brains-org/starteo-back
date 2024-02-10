package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.IdeaMapper;
import com.starteo.demo.endpoint.rest.model.CreateIdea;
import com.starteo.demo.endpoint.rest.model.Idea;
import com.starteo.demo.service.IdeaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class IdeaController {
    private IdeaMapper ideaMapper;
    private IdeaService ideaService;
    private IdeaMapper ideaMapper;

    @GetMapping()
    public List<Idea> getIdeasByTime(
            @RequestParam("page") Integer page,
            @RequestParam("page_size") Integer pageSize,
            @RequestParam(value = "idea_name",required = false) String ideaName) {
        return ideaService.getIdeasByDate(page,pageSize,ideaName).stream().map(ideaMapper::toRest).toList();
    }

<<<<<<< HEAD
    @GetMapping("/ideas/{idea_id}")
    public Idea getById(@PathVariable(name = "idea_id")String idIdea) {
        return ideaMapper.toRest(ideaService.getById(idIdea));
=======
    @PutMapping
    public List<Idea> crupdateIdeas(@RequestBody List<CreateIdea> ideas) {
        return ideaService.saveIdeas(ideas.stream().map(ideaMapper::toDomain).toList()).stream().map(ideaMapper::toRest).toList();
>>>>>>> a34902c (chore: get Ideas)
    }

    @PutMapping("/ideas")
    public List<Idea> crupdateIdeas(@RequestBody List<CreateIdea> ideas) {
        return ideaService.saveIdeas(ideas.stream().map(ideaMapper::toDomain).toList()).stream().map(ideaMapper::toRest).toList();
    }
}
