package com.starteo.demo.endpoint.rest.model;

import com.starteo.demo.repository.model.enums.IdeaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateIdea {
  private String id;
  private String name;
  private IdeaStatus status;
  private String description;
  private String founder;
  private String image;
}
