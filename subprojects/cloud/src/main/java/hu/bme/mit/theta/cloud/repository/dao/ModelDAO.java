package hu.bme.mit.theta.cloud.repository.dao;

import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ModelDAO extends AbstractDAO<ModelEntity> {

    public ModelDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<ModelEntity> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }

    public ModelEntity update(ModelEntity modelEntity) {
        return persist(modelEntity);
    }

    @SuppressWarnings("unchecked")
    public List<ModelEntity> findAll() {
        return list((Query<ModelEntity>) namedQuery("hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity.findAll"));
    }
}