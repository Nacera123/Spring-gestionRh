package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.SessionCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCandidatureRepository extends JpaRepository<SessionCandidature, Integer> {
}
