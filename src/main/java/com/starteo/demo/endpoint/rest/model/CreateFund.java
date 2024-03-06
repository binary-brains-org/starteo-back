package com.starteo.demo.endpoint.rest.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateFund {
  private String ideaId;
  private String userId;
  private int value;
}
