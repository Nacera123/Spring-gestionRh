package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.TypeDeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDeContratRepository extends JpaRepository<TypeDeContrat, Integer> {
}
