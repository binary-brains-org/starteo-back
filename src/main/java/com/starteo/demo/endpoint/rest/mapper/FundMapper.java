package com.starteo.demo.endpoint.rest.mapper;

import com.starteo.demo.endpoint.rest.model.Fund;
import com.starteo.demo.endpoint.rest.model.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class FundMapper {
  public Fund toRest(com.starteo.demo.repository.model.Fund domain) {
    UserInfo userInfo = UserInfo.builder()
        .email(domain.getUser().getEmail())
        .firstname(domain.getUser().getFirstname())
        .lastname(domain.getUser().getLastname())
        .id(domain.getUser().getId())
        .build();
    return Fund.builder()
        .id(domain.getId())
        .user(userInfo)
        .value(domain.getValue())
        .build();
  }
}
