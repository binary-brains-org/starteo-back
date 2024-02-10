package com.starteo.demo.endpoint.controller;

import com.starteo.demo.repository.model.Idea;
import com.starteo.demo.service.IdeaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ideas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class IdeaController {

    private IdeaService ideaService;

    @GetMapping()
    public Idea getIdeasByTime(
            @RequestParam("page") Integer page,
            @RequestParam("page_size") Integer pageSize,
            @RequestParam(value = "idea_name",required = false) String ideaName) {
        return ;
    }

    @PutMapping
    public List<Idea> crupdateIdeas(@RequestBody List<Idea> ideas) {

    }

}
