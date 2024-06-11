package getionRh.example.rh.repository.candidature;

import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.SessionCandidature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

    @Query("select c from Candidature c where c.posteVacant.session = ?1")
    public List<Candidature> findCandidatureBySession(SessionCandidature sessionCandidature);

}
