package com.starteo.demo.service;

import com.starteo.demo.endpoint.rest.model.CreateFund;
import com.starteo.demo.repository.FundRepository;
import com.starteo.demo.repository.IdeaRepository;
import com.starteo.demo.repository.UserRepository;
import com.starteo.demo.repository.model.Fund;
import com.starteo.demo.repository.model.Idea;
import com.starteo.demo.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FundService {
  private FundRepository fundRepository;
  private IdeaRepository ideaRepository;
  private UserRepository userRepository;

  public Fund saveFundByIdeaId(CreateFund createFund, String ideaId, String userId) {
    Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> {throw new RuntimeException("idea not found");});
    User user = userRepository.findById(userId).orElseThrow(() -> {throw new RuntimeException("idea not found");});
    Fund fund = Fund.builder()
        .idea(idea)
        .value(createFund.getValue())
        .user(user)
        .build();
    return fundRepository.save(fund);
  }
}
