package getionRh.example.rh.model.repository;

import getionRh.example.rh.model.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {
}
