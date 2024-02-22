package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface DocumentCandidatureService {
    DocumentCandidature save(DocumentCandidature document);

    List<DocumentCandidature> getAll();

    DocumentCandidature getById(Integer id)throws WsException;

    void delete(DocumentCandidature document);

    DocumentCandidature update(Integer id, DocumentCandidature document);
}
