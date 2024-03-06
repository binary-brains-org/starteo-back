package com.starteo.api.endpoint.rest.mapper;

import com.starteo.api.endpoint.rest.model.CreateIdea;
import com.starteo.api.endpoint.rest.model.Idea;
import com.starteo.api.repository.model.Fund;
import com.starteo.api.service.FundService;
import com.starteo.api.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdeaMapper {
  private FundService fundService;
  private UserService userService;

  @Value("${aws.s3.bucket}")
  private String bucketName;

  public Idea toRest(com.starteo.api.repository.model.Idea domain) {
    int currentValue = 0;
    for (Fund fund : fundService.getFundsByIdea(domain)) {
      currentValue += fund.getValue();
    }
    return Idea.builder()
        .id(domain.getId())
        .name(domain.getName())
        .status(domain.getStatus())
        .description(domain.getDescription())
        .founder(domain.getFounder().getEmail())
        .image(
            domain.getImage() != null
                ? "https://" + bucketName + ".s3.amazonaws.com/" + domain.getImage()
                : null)
        .creationDatetime(domain.getCreationDatetime())
        .updatedDatetime(domain.getUpdatedDatetime())
        .currentFunds(currentValue)
        .build();
  }

  public com.starteo.api.repository.model.Idea toDomain(CreateIdea idea) {
    return com.starteo.api.repository.model.Idea.builder()
        .id(idea.getId())
        .name(idea.getName())
        .status(idea.getStatus())
        .description(idea.getDescription())
        .founder(userService.getUserByEmail(idea.getFounder()))
        .image(idea.getImage())
        .creationDatetime(new Date().toInstant())
        .updatedDatetime(new Date().toInstant())
        .build();
  }
}
