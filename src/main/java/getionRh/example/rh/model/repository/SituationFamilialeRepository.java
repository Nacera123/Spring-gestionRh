package getionRh.example.rh.model.repository;


import getionRh.example.rh.model.entity.SituationFamiliale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationFamilialeRepository extends JpaRepository<SituationFamiliale,Integer> {
}
