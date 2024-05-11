package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.EtatCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatCandidatureRepository extends JpaRepository<EtatCandidature, Integer> {
    EtatCandidature findByEtatIgnoreCase(String etat);
}
