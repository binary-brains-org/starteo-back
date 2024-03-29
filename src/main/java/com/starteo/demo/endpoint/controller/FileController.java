package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.IdeaMapper;
import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.endpoint.rest.model.Idea;
import com.starteo.demo.endpoint.rest.model.User;
import com.starteo.demo.service.IdeaService;
import com.starteo.demo.service.UserService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class FileController {
  private IdeaMapper ideaMapper;
  private IdeaService ideaService;
  private UserService userService;
  private UserMapper userMapper;

  @PostMapping(
      value = "/users/{user_id}/picture",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public User uploadUserImage(
      @PathVariable("user_id") String userId, @RequestParam("file") MultipartFile file)
      throws IOException {
    return userMapper.toDto(userService.uploadUserImage(userId, file));
  }

  @PostMapping(
      value = "/ideas/{idea_id}/picture",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public Idea uploadIdeaImage(
      @PathVariable(name = "idea_id") String ideaId, @RequestParam("file") MultipartFile file)
      throws IOException {
    return ideaMapper.toRest(ideaService.uploadIdeaImage(ideaId, file));
  }
}
