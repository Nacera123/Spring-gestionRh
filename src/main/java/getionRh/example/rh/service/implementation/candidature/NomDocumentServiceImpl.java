package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.candidature.NomDocument;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.NomDocumentRepository;
import getionRh.example.rh.service.candidature.NomDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NomDocumentServiceImpl implements NomDocumentService {

    @Autowired
    private NomDocumentRepository nomDocumentRepository;

    @Override
    public NomDocument save(NomDocument nom){
        return nomDocumentRepository.save(nom);
    }

    @Override
    public List<NomDocument> getAll()throws WsException{
        List<NomDocument> nomDocuments = nomDocumentRepository.findAll();
        if(nomDocuments.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "le document n'a pas de nom");
        }else{

        return nomDocuments;
        }

    }

    @Override
    public NomDocument getById(Integer id)throws WsException {
        Optional<NomDocument> optional = nomDocumentRepository.findById(id);
        NomDocument nom;
        if (optional.isPresent()){
            nom = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return nom;
    }

    @Override
    public void delete(NomDocument etat){
        nomDocumentRepository.delete(etat);
    }

    @Override
    public NomDocument update(Integer id, NomDocument nom){
        NomDocument nom1 = this.getById(id);
        nom1.setNom(nom.getNom());
        return this.save(nom1);
    }
}
