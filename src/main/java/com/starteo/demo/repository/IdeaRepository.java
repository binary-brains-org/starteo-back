package com.starteo.demo.repository;


import com.starteo.demo.repository.model.Idea;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, String> {
    @Query(value = "Select * from idea order by updated_datetime;",nativeQuery = true)
    List<Idea> getIdeasOrderByUpdate(Pageable pageable);

    List<Idea> findIdeasByNameOrderByUpdatedDatetime(String name, Pageable pageable);
}
