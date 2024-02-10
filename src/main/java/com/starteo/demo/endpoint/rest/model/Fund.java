package com.starteo.demo.endpoint.rest.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Fund {
  private String id;
  private Idea idea;
  private UserInfo user;
  private int value;
}
