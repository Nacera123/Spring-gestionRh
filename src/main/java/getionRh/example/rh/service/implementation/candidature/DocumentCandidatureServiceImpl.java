package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.DocumentCandidatureRepository;
import getionRh.example.rh.repository.candidature.PosteVacantRepository;
import getionRh.example.rh.service.candidature.DocumentCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentCandidatureServiceImpl implements DocumentCandidatureService {

    @Value("${file.upload.path}")
    private String pathUploadFile;


    @Autowired
    private DocumentCandidatureRepository documentCandidatureRepository;

    @Autowired
    private PosteVacantRepository posteVacantRepository;

    public DocumentCandidature save(DocumentCandidature document){
        return documentCandidatureRepository.save(document);
    }




    public DocumentCandidature save2(MultipartFile file, Integer postId)throws Exception{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String pathForDate = dateFormat.format(new java.util.Date());
        File directory = new File(this.pathUploadFile+"/"+pathForDate);

        // vérifier si le répertoire n'existe pas on va le créer avec la commande mkdir
        if (!directory.exists()){
            directory.mkdirs(); // pour le créer
        }

        String fileName = file.getOriginalFilename();
        int positionPoint = fileName.lastIndexOf(".");
        String extensions = fileName.substring(positionPoint); // ".png"
        List<String> extAutoriser = List.of(".png",".jpg",".webp",".svg",".jpeg",".pdf",".doc",".docx",".xls",".xlsx",".ppt",".pptx");


        if (!extAutoriser.contains(extensions.toLowerCase())){ // toLowerCase() => miniscule
            throw  new Exception("Extensions non autoriser");
        }

        String uniqueId = UUID.randomUUID().toString();
        fileName = uniqueId + extensions;
        File newFile = new File(directory.getAbsolutePath(), fileName);
        file.transferTo(newFile);

        PosteVacant post = posteVacantRepository.findById(postId).orElseThrow(() -> new Exception("Post not found"));


        DocumentCandidature docCandidature = new DocumentCandidature();


        docCandidature.setNomFichier("/"+pathForDate+fileName);

        docCandidature.getCandidature().setPosteVacant(post);

        return documentCandidatureRepository.save(docCandidature);


    }






    @Override
    public DocumentCandidature save1(MultipartFile file, DocumentCandidature document) throws Exception{

        /******** PDF *******/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String pathForDate = dateFormat.format(new java.util.Date());
        File directory = new File(this.pathUploadFile+pathForDate);
        // vérifier si le répertoire n'existe pas on va le créer avec la commande mkdir
        if (!directory.exists()){
            directory.mkdirs(); // pour le créer
        }

        String fileName = file.getOriginalFilename();
        int positionPoint = fileName.lastIndexOf(".");
        String extensions = fileName.substring(positionPoint); // ".png"
        List<String> extAutoriser = List.of(".png",".jpg",".webp",".svg",".jpeg",".pdf",".doc",".docx",".xls",".xlsx",".ppt",".pptx");
        if (!extAutoriser.contains(extensions.toLowerCase())){ // toLowerCase() => miniscule
            throw  new Exception("Extensions non autoriser");
        }
        String uniqueId = UUID.randomUUID().toString();
        fileName = uniqueId + extensions;
        File newFile = new File(directory.getAbsolutePath(), fileName);
        file.transferTo(newFile);

        document.setPieceJointe(newFile.getAbsolutePath());

        return documentCandidatureRepository.save(document);
    }

    @Override
    public List<DocumentCandidature> getAll()throws WsException{

        List<DocumentCandidature> doc = documentCandidatureRepository.findAll();

        if (doc.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "La liste des documents est vide");
        }else{

        return doc;
        }
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
        document1.setCandidature(document.getCandidature());
        document1.setNomPieceJointe(document.getNomPieceJointe());
        document1.setPieceJointe(document.getPieceJointe());
        return this.save(document1);
    }



}

