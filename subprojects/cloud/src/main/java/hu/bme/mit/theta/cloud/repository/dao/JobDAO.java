package hu.bme.mit.theta.cloud.repository.dao;

import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JobDAO extends AbstractDAO<JobEntity> {

    public JobDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<JobEntity> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }

    public JobEntity update(JobEntity jobEntity) {
        return persist(jobEntity);
    }

    @SuppressWarnings("unchecked")
    public List<JobEntity> findAll() {
        return list((Query<JobEntity>) namedQuery("hu.bme.mit.theta.cloud.repository.datamodel.JobEntity.findAll"));
    }
}
