package com.starteo.demo.endpoint.controller;

import com.starteo.demo.endpoint.rest.mapper.FileMapper;
import com.starteo.demo.endpoint.rest.mapper.IdeaMapper;
import com.starteo.demo.endpoint.rest.mapper.UserMapper;
import com.starteo.demo.endpoint.rest.model.Idea;
import com.starteo.demo.endpoint.rest.model.User;
import com.starteo.demo.service.IdeaService;
import com.starteo.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FileController {
  private FileMapper fileMapper;
  private IdeaMapper ideaMapper;
  private IdeaService ideaService;
  private UserService userService;
  private UserMapper userMapper;

  @PostMapping("/users/{user_id}/picture/raw")
  public User uploadUserImage(@RequestBody byte[] image, @PathVariable(name = "user_id")String userId) {
    String base64 = fileMapper.encoodeToBase64(image);
    return userMapper.toUser(userService.uploadUserImage(userId, base64));
  }

  @PostMapping("/ideas/{idea_id}/picture/raw")
  public Idea uploadIdeaImage(@RequestBody byte[] image, @PathVariable(name = "idea_id")String ideaId) {
    String base64 = fileMapper.encoodeToBase64(image);
    return ideaMapper.toRest(ideaService.uploadIdeaImage(ideaId, base64));
  }
}
