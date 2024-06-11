package getionRh.example.rh.repository.implementation;

import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.repository.CandidatRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CandidatRepositoryImpl implements CandidatRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends Candidat> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Candidat> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Candidat> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Candidat getOne(Integer integer) {
        return null;
    }

    @Override
    public Candidat getById(Integer integer) {
        return null;
    }

    @Override
    public Candidat getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Candidat> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Candidat> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Candidat> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Candidat> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Candidat> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Candidat> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Candidat, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Candidat> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Candidat> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Candidat> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Candidat> findAll() {
        return null;
    }

    @Override
    public List<Candidat> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Candidat entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Candidat> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Candidat> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Candidat> findAll(Pageable pageable) {
        return null;
    }
}
