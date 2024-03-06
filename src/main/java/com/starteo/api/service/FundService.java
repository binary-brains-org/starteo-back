package com.starteo.api.service;

import com.starteo.api.endpoint.rest.model.CreateFund;
import com.starteo.api.repository.FundRepository;
import com.starteo.api.repository.IdeaRepository;
import com.starteo.api.repository.model.Fund;
import com.starteo.api.repository.model.Idea;
import com.starteo.api.repository.model.User;
import com.starteo.api.repository.validator.FundValidator;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
// TODO : add validator for data integrity
public class FundService {
  private FundRepository fundRepository;
  private IdeaRepository ideaRepository;
  private FundValidator fundValidator;

  public List<Fund> saveFunds(List<CreateFund> createFund, User user) {
    List<Fund> funds = new ArrayList<>();
    fundValidator.accept(createFund);
    for (CreateFund fund : createFund) {
      Idea idea =
          ideaRepository
              .findById(fund.getIdeaId())
              .orElseThrow(
                  () -> {
                    throw new RuntimeException("idea not found");
                  });
      Fund ideaFund = Fund.builder().idea(idea).value(fund.getValue()).user(user).build();
      fundRepository.save(ideaFund);
      funds.add(ideaFund);
    }
    return funds;
  }

  public List<Fund> getFundsByIdea(Idea idea) {
    return fundRepository.findAllByIdea(idea);
  }
}
