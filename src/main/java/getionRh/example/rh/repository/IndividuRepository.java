package getionRh.example.rh.repository;

import getionRh.example.rh.entity.Individu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividuRepository extends JpaRepository<Individu,Integer> {

    public Optional<Individu> findIndividusByNom(String nom);

    public Optional<Individu> getIndividusByNomStartingWith(String n);

    Individu findByEmailIgnoreCase(String email);

    Individu findByEmail(String email);
}
