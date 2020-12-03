package hu.bme.mit.theta.cloud.repository.dao;

import hu.bme.mit.theta.cloud.repository.AnalysisBenchmarkRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity;
import io.dropwizard.hibernate.UnitOfWork;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AnalysisBenchmarkDAORepository implements AnalysisBenchmarkRepository {

    AnalysisBenchmarkDAO analysisBenchmarkDAO;

    public AnalysisBenchmarkDAORepository(AnalysisBenchmarkDAO analysisBenchmarkDAO) {
        this.analysisBenchmarkDAO = analysisBenchmarkDAO;
    }

    @UnitOfWork
    @Override
    public List<AnalysisBenchmarkEntity> findAll() {
        return analysisBenchmarkDAO.findAll();
    }

    @Override
    public List<AnalysisBenchmarkEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<AnalysisBenchmarkEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<AnalysisBenchmarkEntity> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(AnalysisBenchmarkEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends AnalysisBenchmarkEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @UnitOfWork
    @Override
    public <S extends AnalysisBenchmarkEntity> S save(S entity) {
        return (S) analysisBenchmarkDAO.update(entity);
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @UnitOfWork
    @Override
    public Optional<AnalysisBenchmarkEntity> findById(UUID uuid) {
        return analysisBenchmarkDAO.findById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends AnalysisBenchmarkEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<AnalysisBenchmarkEntity> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public AnalysisBenchmarkEntity getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends AnalysisBenchmarkEntity> boolean exists(Example<S> example) {
        return false;
    }
}
