package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.NomDocument;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface NomDocumentService {
    NomDocument save(NomDocument nom);

    List<NomDocument> getAll();

    NomDocument getById(Integer id)throws WsException;

    void delete(NomDocument etat);

    NomDocument update(Integer id, NomDocument nom);
}
