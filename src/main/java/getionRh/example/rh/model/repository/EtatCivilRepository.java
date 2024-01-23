package getionRh.example.rh.model.repository;


import getionRh.example.rh.model.entity.EtatCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatCivilRepository extends JpaRepository<EtatCivil,Integer> {
}
