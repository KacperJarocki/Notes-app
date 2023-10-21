package server.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Notes;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {
    @Transactional
    @Query("SELECT n FROM Notes n JOIN FETCH n.accounts a JOIN FETCH n.category c LEFT JOIN FETCH n.tags WHERE a.nameUser = :name")
    List<Notes> findNotesByAccounts_NameUser(@Param("name") String name);
    
    Notes findByCreationDate(LocalDateTime CreationDate);
    @Transactional
    @Query("SELECT n FROM Notes n WHERE n.urlAddress = :url")
    Notes findByUrl(@Param("url") String url);
    
    @Transactional
    void deleteNotesByAccounts_AccountId(int id);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Notes e WHERE e.urlAddress = :url")
    boolean existsByUrlAddress(@Param("url") String url);
}
