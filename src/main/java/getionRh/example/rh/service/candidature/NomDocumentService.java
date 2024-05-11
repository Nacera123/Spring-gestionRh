package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.NomDocument;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface NomDocumentService {


    NomDocument save(NomDocument poste)throws Exception;

    List<NomDocument> getAll();

    NomDocument getById(Integer id)throws WsException;


    void deletePoste(NomDocument poste);

    void delete(Integer id);

    NomDocument update(Integer id, NomDocument nom)throws Exception;
}
