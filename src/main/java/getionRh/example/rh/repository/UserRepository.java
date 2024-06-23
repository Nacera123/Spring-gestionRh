package getionRh.example.rh.repository;

import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    long countByTokenLike(String token);

    List<User> findByToken(String token);

    User findByEmailIgnoreCase(String email);

    boolean existsByEmailLikeIgnoreCase(String email);

    User findByRoles_NomIgnoreCase(String nom);

    User findByIndividu_Id(Integer id);

}
