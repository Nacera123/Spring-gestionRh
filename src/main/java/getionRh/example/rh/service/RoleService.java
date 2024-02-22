package getionRh.example.rh.service;

import getionRh.example.rh.entity.Role;

import java.util.Optional;

public interface RoleService {
    Role save(Role role)throws Exception;

    Optional<Role> findByName(String roleNom);

    long count();
}
