package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.NomDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomDocumentRepository extends JpaRepository<NomDocument, Integer> {
}
