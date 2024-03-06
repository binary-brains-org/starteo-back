package com.starteo.api.service;

import com.starteo.api.repository.UserRepository;
import com.starteo.api.repository.model.User;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {
  private final UserRepository repository;
  private final S3Service s3Service;

  public User uploadUserImage(String userId, MultipartFile file) throws IOException {
    User selected = getUserById(userId);
    String keyName = "user/" + selected.getEmail() + file.getOriginalFilename();
    String image = s3Service.uploadFile(keyName, file);
    selected.setImage(image);
    return repository.save(selected);
  }

  public User getUserById(String userId) {
    return repository
        .findById(userId)
        .orElseThrow(
            () -> {
              throw new RuntimeException("user not found");
            });
  }

  public List<User> saveUsers(List<User> users) {
    return repository.saveAll(users);
  }

  public User getUserByEmail(String email) {
    return repository.findByEmail(email).orElse(null);
  }

  public User getByEmail(String email) {
    return Objects.requireNonNull(repository.findByEmail(email).orElse(null));
  }
}
