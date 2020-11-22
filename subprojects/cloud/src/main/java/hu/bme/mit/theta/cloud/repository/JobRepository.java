package hu.bme.mit.theta.cloud.repository;

import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
