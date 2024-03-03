package com.starteo.demo.service;

import com.starteo.demo.endpoint.rest.model.CreateFund;
import com.starteo.demo.repository.FundRepository;
import com.starteo.demo.repository.IdeaRepository;
import com.starteo.demo.repository.model.Fund;
import com.starteo.demo.repository.model.Idea;
import com.starteo.demo.repository.model.User;
import com.starteo.demo.repository.validator.FundValidator;
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
