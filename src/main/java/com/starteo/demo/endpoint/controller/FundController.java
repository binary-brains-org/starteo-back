package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.FundMapper;
import com.starteo.demo.endpoint.rest.model.CreateFund;
import com.starteo.demo.endpoint.rest.model.Fund;
import com.starteo.demo.repository.model.User;
import com.starteo.demo.service.AuthService;
import com.starteo.demo.service.FundService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class FundController {
  private FundService fundService;
  private FundMapper fundMapper;
  private AuthService authService;

  @PutMapping("/funds/{idea_id}")
  public List<Fund> saveFundByIdea(@PathVariable(name = "idea_id")String ideaString, HttpServletRequest request, @RequestBody CreateFund toCreate) {
    User userConnected = authService.whoami(request);
    com.starteo.demo.repository.model.Fund created = fundService.saveFundByIdeaId(toCreate, ideaString, userConnected);
    return List.of(fundMapper.toRest(created));
  }
}