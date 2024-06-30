package getionRh.example.rh.repository;

import getionRh.example.rh.entity.Candidats;
import getionRh.example.rh.entity.Individu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatsReository extends JpaRepository<Candidats, Integer > {
    Candidats findByIndividu(Individu individu);
}
