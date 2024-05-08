package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.SessionCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCandidatureRepository extends JpaRepository<SessionCandidature, Integer> {
    @Query("select (count(s) > 0) from SessionCandidature s where upper(s.reference) = upper(?1)")
    boolean existsByReferenceIgnoreCase(String reference);

    SessionCandidature findByReference(String reference);

    SessionCandidature findByReferenceIgnoreCase(String reference);
}
