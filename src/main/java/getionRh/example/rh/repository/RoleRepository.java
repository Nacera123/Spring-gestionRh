package getionRh.example.rh.repository;


import getionRh.example.rh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByNom(String nom);

    Optional<Role> findByNom(String nom);

    long countByNom(String nom);
}
