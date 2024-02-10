package com.starteo.demo.endpoint.rest.model;

import com.starteo.demo.repository.model.enums.IdeaStatus;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Idea {
  private String id;
  private String name;
  private IdeaStatus status;
  private String description;
  private String founder;
  private String image;
  private Instant creationDatetime;
  private Instant updatedDatetime;
  private int currentFunds;
}
