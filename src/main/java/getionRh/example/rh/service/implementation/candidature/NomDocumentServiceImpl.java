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
    public NomDocument save(NomDocument poste)throws Exception{

        if (poste.getNom() == "" || poste.getNom().isEmpty()){
            throw new Exception("Veuillez remplir un nom de poste valide  ");
        }

        return nomDocumentRepository.save(poste);
    }


    @Override
    public List<NomDocument> getAll()throws WsException {
        List<NomDocument> posteDeTravails = nomDocumentRepository.findAll();
        if(posteDeTravails.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "la liste des poste est vide");
        }else {

            return posteDeTravails;
        }

    }

    @Override
    public NomDocument getById(Integer id)throws WsException {
        Optional<NomDocument> optional = nomDocumentRepository.findById(id);
        NomDocument poste;
        if (optional.isPresent()){
            poste = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return poste;
    }


    @Override
    public void deletePoste(NomDocument poste){
        nomDocumentRepository.delete(poste);
    }

    @Override
    public void  delete(Integer id){
        nomDocumentRepository.deleteById(id);
    }


    @Override
    public NomDocument update(Integer id, NomDocument poste)throws Exception{
        NomDocument poste1 = this.getById(id);
        poste1.setNom(poste.getNom());
        return this.save(poste1);
    }

    public NomDocument getByNom(String nom)throws Exception{
        Optional<NomDocument> optional = Optional.ofNullable(nomDocumentRepository.findByNomIgnoreCase(nom));
        NomDocument poste;
        if (optional.isPresent()){
            poste = optional.get();
        }else {
            throw new Exception("Le poste " + nom + " n'existe pas");
        }
        return poste;
    }
}
