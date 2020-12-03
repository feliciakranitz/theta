package hu.bme.mit.theta.cloud.repository.dao;

import hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AnalysisBenchmarkDAO extends AbstractDAO<AnalysisBenchmarkEntity> {

    public AnalysisBenchmarkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<AnalysisBenchmarkEntity> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }

    public AnalysisBenchmarkEntity update(AnalysisBenchmarkEntity analysisBenchmarkEntity) {
        return persist(analysisBenchmarkEntity);
    }

    @SuppressWarnings("unchecked")
    public List<AnalysisBenchmarkEntity> findAll() {
        return list((Query<AnalysisBenchmarkEntity>) namedQuery("hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity.findAll"));
    }
}
