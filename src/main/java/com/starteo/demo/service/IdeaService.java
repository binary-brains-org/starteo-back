package com.starteo.demo.service;

import com.starteo.demo.repository.IdeaRepository;
import com.starteo.demo.repository.model.Idea;
import com.starteo.demo.service.utils.InstanceTime;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IdeaService {
  private final IdeaRepository ideaRepository;

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

  public Idea uploadIdeaImage(String ideaId, String image) {
    Idea selected = getById(ideaId);
    selected.setImage(image);
    return ideaRepository.save(selected);
  }
}
