package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.Fund;
import com.starteo.demo.endpoint.rest.model.Idea;
import com.starteo.demo.endpoint.rest.model.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FundMapper {
  private IdeaMapper ideaMapper;

  public Fund toRest(com.starteo.demo.repository.model.Fund domain) {
    UserInfo userInfo = UserInfo.builder()
        .email(domain.getUser().getEmail())
        .firstname(domain.getUser().getFirstname())
        .lastname(domain.getUser().getLastname())
        .id(domain.getUser().getId())
        .build();
    Idea idea = ideaMapper.toRest(domain.getIdea());
    return Fund.builder()
        .id(domain.getId())
        .user(userInfo)
        .value(domain.getValue())
        .idea(idea)
        .build();
  }
}
