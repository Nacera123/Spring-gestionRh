package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Role;
import getionRh.example.rh.repository.RoleRepository;
import getionRh.example.rh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role save(Role role)throws Exception{

        if (roleRepository.existsByNom(role.getNom())){
            throw new Exception("Le role existe deja");
        }
        return roleRepository.save(role);
    }
    private Optional<Role> findByName(String roleNom){
       return roleRepository.findByNom(roleNom);
    }


}
