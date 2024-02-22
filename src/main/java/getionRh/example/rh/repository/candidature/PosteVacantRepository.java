package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.PosteVacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteVacantRepository extends JpaRepository<PosteVacant, Integer> {
}
