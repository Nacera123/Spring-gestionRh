package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.PosteDeTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteDeTravailRepository extends JpaRepository<PosteDeTravail, Integer> {
    @Query("select (count(p) > 0) from PosteDeTravail p where upper(p.reference) = upper(?1)")
    boolean existsByReferenceIgnoreCase(String reference);

    PosteDeTravail findByReferenceIgnoreCase(String reference);

    PosteDeTravail findByNomIgnoreCase(String nom);
}
