package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.PosteDeTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteDeTravailRepository extends JpaRepository<PosteDeTravail, Integer> {
}
