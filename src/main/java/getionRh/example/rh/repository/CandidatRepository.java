package getionRh.example.rh.repository;


import getionRh.example.rh.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Integer> {
}
