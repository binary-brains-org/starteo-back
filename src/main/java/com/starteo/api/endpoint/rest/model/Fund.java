package com.starteo.api.endpoint.rest.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Fund {
  private String id;
  private Idea idea;
  private User user;
  private int value;
}
