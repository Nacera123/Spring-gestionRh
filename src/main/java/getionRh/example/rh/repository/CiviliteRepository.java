package getionRh.example.rh.repository;


import getionRh.example.rh.entity.Civilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiviliteRepository extends JpaRepository<Civilite, Integer> {
    Civilite findByDesignationIgnoreCase(String designation);
}
