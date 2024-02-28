package com.starteo.demo.repository.validator;

import com.starteo.demo.endpoint.rest.model.CreateFund;
import com.starteo.demo.repository.IdeaRepository;
import com.starteo.demo.repository.UserRepository;
import com.starteo.demo.repository.model.exception.BadRequestException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FundValidator implements Consumer<CreateFund> {
  private final IdeaRepository ideaRepository;
  private final UserRepository userRepository;

  @Override
  public void accept(CreateFund fund) {
    Set<String> violationMessages = new HashSet<>();
    if (ideaRepository.findById(fund.getIdeaId()) == null) {
      violationMessages.add("Idea not found");
    }
    if (userRepository.findById(fund.getUserId()) == null) {
      violationMessages.add("User not found");
    }
    if (fund.getValue() >= 0) {
      violationMessages.add("Fund should not be under or equal 0");
    }
    if (!violationMessages.isEmpty()) {
      String formattedViolationMessages =
          violationMessages.stream().map(String::toString).collect(Collectors.joining("\n"));
      throw new BadRequestException(formattedViolationMessages);
    }
  }

  public void accept(List<CreateFund> funds) {
    funds.forEach(this::accept);
  }
}
