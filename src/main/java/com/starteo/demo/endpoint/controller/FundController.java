package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.FundMapper;
import com.starteo.demo.endpoint.rest.model.CreateFund;
import com.starteo.demo.endpoint.rest.model.Fund;
import com.starteo.demo.repository.model.User;
import com.starteo.demo.service.AuthService;
import com.starteo.demo.service.FundService;
import com.starteo.demo.service.IdeaService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class FundController {
  private FundService fundService;
  private FundMapper fundMapper;
  private IdeaService ideaService;
  private AuthService authService;

  @PutMapping("/funds")
  public List<Fund> saveFunds(HttpServletRequest request, @RequestBody List<CreateFund> toCreate) {
    User userConnected = authService.whoami(request);
    List<com.starteo.demo.repository.model.Fund> created =
        fundService.saveFunds(toCreate, userConnected);
    return created.stream().map(fundMapper::toRest).toList();
  }

  @GetMapping("/ideas/{idea_id}/funds")
  public List<Fund> getAllFundSOnIdea(@PathVariable("idea_id") String ideaString) {
    return fundService.getFundsByIdea(ideaService.getById(ideaString)).stream()
        .map(fundMapper::toRest)
        .toList();
  }
}
