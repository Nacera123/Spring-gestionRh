package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.DocumentCandidatureRepository;
import getionRh.example.rh.service.candidature.DocumentCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentCandidatureServiceImpl implements DocumentCandidatureService {


    @Autowired
    private DocumentCandidatureRepository documentCandidatureRepository;

    @Override
    public DocumentCandidature save(DocumentCandidature document){

        return documentCandidatureRepository.save(document);
    }

    @Override
    public List<DocumentCandidature> getAll(){
        return documentCandidatureRepository.findAll();
    }

    @Override
    public DocumentCandidature getById(Integer id)throws WsException {
        Optional<DocumentCandidature> optional = documentCandidatureRepository.findById(id);
        DocumentCandidature document;
        if (optional.isPresent()){
            document = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return document;
    }

    @Override
    public void delete(DocumentCandidature document){
        documentCandidatureRepository.delete(document);
    }


    @Override
    public DocumentCandidature update(Integer id, DocumentCandidature document){
        DocumentCandidature document1 = this.getById(id);
        document1.setId(document.getId());
        return documentCandidatureRepository.save(document1);
    }
}
