package getionRh.example.rh.model.repository;

import getionRh.example.rh.model.entity.Individu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividuRepository extends JpaRepository<Individu,Integer> {
}
