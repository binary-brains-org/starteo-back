package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.Fund;
import com.starteo.demo.endpoint.rest.model.Idea;
import com.starteo.demo.endpoint.rest.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FundMapper {
  private IdeaMapper ideaMapper;
  private UserMapper userMapper;

  public Fund toRest(com.starteo.demo.repository.model.Fund domain) {
    User user = userMapper.toDto(domain.getUser());
    Idea idea = ideaMapper.toRest(domain.getIdea());
    return Fund.builder().id(domain.getId()).user(user).value(domain.getValue()).idea(idea).build();
  }
}
