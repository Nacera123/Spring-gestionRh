package getionRh.example.rh.repository.candidature;


import getionRh.example.rh.entity.candidature.DocumentCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentCandidatureRepository extends JpaRepository<DocumentCandidature,Integer> {

    List<DocumentCandidature> findAllByCandidatureId(int id);
    @Query(value = "SELECT " +
            "candidature_id, " +
            "GROUP_CONCAT(nom_fichier ORDER BY nom_fichier SEPARATOR '  ') AS liste_nom_fichiers, " +
            "GROUP_CONCAT(nom_piece_jointe_id ORDER BY nom_piece_jointe_id SEPARATOR '  ') AS liste_nom_piece_jointe_ids, " +
            "GROUP_CONCAT(id ORDER BY id SEPARATOR '  ') AS liste_id " +
            "FROM document_candidature " +
            "GROUP BY candidature_id", nativeQuery = true)
    List<Object[]> findDocumentsGroupedByCandidatureId();



}
