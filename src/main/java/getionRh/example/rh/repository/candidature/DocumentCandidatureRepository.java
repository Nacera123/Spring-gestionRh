package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.DocumentCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentCandidatureRepository extends JpaRepository<DocumentCandidature,Integer> {
}
