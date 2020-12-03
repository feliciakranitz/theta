package hu.bme.mit.theta.cloud.repository.dao;

import hu.bme.mit.theta.cloud.repository.ModelRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import io.swagger.models.Model;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ModelDAORepository implements ModelRepository {

    ModelDAO modelDAO;

    public ModelDAORepository(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    @Override
    public List<ModelEntity> findAll() {
        return modelDAO.findAll();
    }

    @Override
    public List<ModelEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ModelEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ModelEntity> findAllById(Iterable<UUID> uuids) {
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
    public void delete(ModelEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends ModelEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ModelEntity> S save(S entity) {
        return (S) modelDAO.update(entity);
    }

    @Override
    public <S extends ModelEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ModelEntity> findById(UUID uuid) {
        return modelDAO.findById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ModelEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<ModelEntity> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ModelEntity getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends ModelEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ModelEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ModelEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ModelEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ModelEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ModelEntity> boolean exists(Example<S> example) {
        return false;
    }
}
