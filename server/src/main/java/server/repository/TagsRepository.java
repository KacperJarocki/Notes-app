package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags,Integer> {
    void deleteByNoteId(int note_id);
}
