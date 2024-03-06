package com.starteo.api.service;

import com.starteo.api.repository.IdeaRepository;
import com.starteo.api.repository.model.Idea;
import com.starteo.api.service.utils.InstanceTime;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class IdeaService {
  private final IdeaRepository ideaRepository;
  private final S3Service s3Service;

  private InstanceTime instanceTime;

  public List<Idea> saveIdeas(List<Idea> ideas) {
    return ideaRepository.saveAll(ideas);
  }

  public List<Idea> getIdeasByWeek(Instant monday, Instant sunday, Integer page, Integer pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return ideaRepository.getIdeasOnWeek(monday, sunday, pageable);
  }

  public List<Idea> getIdeasByDate(Integer page, Integer pageSize, String ideaName) {
    Pageable pageable = PageRequest.of(page, pageSize);
    if (ideaName != null) {
      return ideaRepository.findIdeasByNameOrderByUpdatedDatetime(ideaName, pageable);
    }
    return ideaRepository.getIdeasOrderByUpdate(pageable);
  }

  public Idea getById(String ideaId) {
    return ideaRepository
        .findById(ideaId)
        .orElseThrow(
            () -> {
              throw new RuntimeException("Not found");
            });
  }

  public Idea uploadIdeaImage(String ideaId, MultipartFile file) throws IOException {
    Idea selected = getById(ideaId);
    String keyName = "idea/" + selected.getId() + file.getOriginalFilename();
    String image = s3Service.uploadFile(keyName, file);
    selected.setImage(image);
    return ideaRepository.save(selected);
  }
}
