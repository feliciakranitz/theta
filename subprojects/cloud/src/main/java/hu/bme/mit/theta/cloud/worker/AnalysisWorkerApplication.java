package hu.bme.mit.theta.cloud.worker;

import hu.bme.mit.theta.cloud.mail.MailService;
import hu.bme.mit.theta.cloud.repository.AnalysisBenchmarkRepository;
import hu.bme.mit.theta.cloud.repository.JobRepository;
import hu.bme.mit.theta.cloud.repository.dao.*;
import hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.cloud.workQueue.RabbitWorkQueue;
import hu.bme.mit.theta.cloud.workQueue.WorkQueue;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jersey.errors.LoggingExceptionMapper;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AnalysisWorkerApplication extends Application<AnalysisWorkerConfiguration> {

    private final HibernateBundle<AnalysisWorkerConfiguration> hibernateBundle =
            new HibernateBundle<>(JobEntity.class, AnalysisBenchmarkEntity.class, ConfigurationEntity.class, ModelEntity.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(AnalysisWorkerConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(String[] args) throws Exception {
        new AnalysisWorkerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AnalysisWorkerConfiguration> bootstrap) {
        super.initialize(bootstrap);

        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(AnalysisWorkerConfiguration configuration, Environment environment) throws Exception {
       // MailService mailService = new MailService();
        //mailService.sendMail();

        int cpuCoreCount = Runtime.getRuntime().availableProcessors();
        Executor workExecutor = Executors.newFixedThreadPool(cpuCoreCount);

        var sessionFactory = hibernateBundle.getSessionFactory();

        JobDAORepository jobRepositoryProxy = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(JobDAORepository.class, JobDAO.class, new JobDAO(sessionFactory));
        AnalysisBenchmarkDAORepository analysisBenchmarkRepositoryProxy = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(AnalysisBenchmarkDAORepository.class, AnalysisBenchmarkDAO.class, new AnalysisBenchmarkDAO(sessionFactory));
        ModelDAORepository modelRepositoryProxy = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(ModelDAORepository.class, ModelDAO.class, new ModelDAO(sessionFactory));

        WorkQueue workQueue = new RabbitWorkQueue("user", "user", "localhost", 5672);

        AnalysisManager analysisManager = new AnalysisManager(workQueue, workExecutor, jobRepositoryProxy, analysisBenchmarkRepositoryProxy, modelRepositoryProxy);

        environment.lifecycle().manage(analysisManager);
        environment.jersey().register(LoggingExceptionMapper.class);
    }
}
