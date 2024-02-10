package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.Idea;
import org.springframework.stereotype.Component;

@Component
public class IdeaMapper {
  public Idea toRest(com.starteo.demo.repository.model.Idea domain) {
    return Idea.builder()
        .id(domain.getId())
        .build();
  }
}
