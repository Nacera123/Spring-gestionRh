package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature,Integer> {
}
