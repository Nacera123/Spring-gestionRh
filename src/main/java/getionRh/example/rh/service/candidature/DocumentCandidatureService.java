package getionRh.example.rh.service.candidature;

import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.exception.WsException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DocumentCandidatureService {


    String uploadFile(MultipartFile file)throws Exception;

    Map<String, String> saveCandidature(MultipartFile fileCv, MultipartFile fileLm, String nomFileCV, String nomFileLM, IndividuDto individuDto, Integer postId) throws Exception;
}
