package hu.bme.mit.theta.cloud.repository.dao;

import hu.bme.mit.theta.cloud.repository.JobRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import io.dropwizard.hibernate.UnitOfWork;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JobDAORepository implements JobRepository {

    JobDAO jobDAO;

    public JobDAORepository(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }

    @UnitOfWork
    @Override
    public List<JobEntity> findAll() {
        return jobDAO.findAll();
    }

    @Override
    public List<JobEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<JobEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<JobEntity> findAllById(Iterable<UUID> uuids) {
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
    public void delete(JobEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends JobEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @UnitOfWork
    @Override
    public <S extends JobEntity> S save(S entity) {
        return (S) jobDAO.update(entity);
    }

    @Override
    public <S extends JobEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @UnitOfWork
    @Override
    public Optional<JobEntity> findById(UUID uuid) {
        return jobDAO.findById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends JobEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<JobEntity> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public JobEntity getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends JobEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends JobEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends JobEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends JobEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends JobEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends JobEntity> boolean exists(Example<S> example) {
        return false;
    }

}
