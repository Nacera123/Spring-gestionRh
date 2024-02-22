package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.TypeDeContrat;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface TypeDeContratService {
    TypeDeContrat save(TypeDeContrat contrat);

    List<TypeDeContrat> getAll();

    TypeDeContrat getById(Integer id)throws WsException;

    void delete(TypeDeContrat contrat);

    TypeDeContrat update(Integer id, TypeDeContrat contrat);

    long count();
}
