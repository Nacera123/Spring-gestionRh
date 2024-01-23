package getionRh.example.rh.model.repository;


import getionRh.example.rh.model.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays,Integer> {
}
