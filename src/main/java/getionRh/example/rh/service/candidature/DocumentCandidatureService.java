package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.exception.WsException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentCandidatureService {


    DocumentCandidature save1(MultipartFile file, DocumentCandidature document) throws Exception;

    List<DocumentCandidature> getAll();

    DocumentCandidature getById(Integer id)throws WsException;

    void delete(DocumentCandidature document);

    DocumentCandidature update(Integer id, DocumentCandidature document);
}
