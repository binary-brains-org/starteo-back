package com.starteo.demo.service;

import com.starteo.demo.repository.IdeaRepository;
import com.starteo.demo.repository.model.Idea;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IdeaService {
    private final IdeaRepository ideaRepository;

    public List<Idea> saveIdeas(List<Idea> ideas){
        return ideaRepository.saveAll(ideas);
    }

    public List<Idea> getIdeasByDate(Integer page, Integer pageSize,String ideaName){
        Pageable pageable = PageRequest.of(page, pageSize);
        if(ideaName != null){
            return ideaRepository.findIdeasByNameOrderByUpdatedDatetime(ideaName,pageable);
        }
        return ideaRepository.getIdeasOrderByUpdate(pageable);
    }

    public Idea getById(String ideaId) {
        return ideaRepository.findById(ideaId).orElseThrow(() -> {throw new RuntimeException("Not found");});
    }

}
