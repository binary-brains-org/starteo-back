package com.starteo.demo.endpoint.rest.model;

import com.starteo.demo.endpoint.rest.model.enums.IdeaEnum;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Idea {
  private String id;
  private String name;
  private IdeaEnum status;
  private String description;
  private String founder;
  private String image;
  private Instant creationDatetime;
  private Instant updatedDatetime;
  private int currentFunds;
}
