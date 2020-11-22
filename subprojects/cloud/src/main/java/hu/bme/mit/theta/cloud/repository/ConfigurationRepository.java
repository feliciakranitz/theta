package hu.bme.mit.theta.cloud.repository;

import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, UUID> {

}