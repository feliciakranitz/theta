package hu.bme.mit.theta.cloud.worker;

import hu.bme.mit.theta.cloud.workQueue.RabbitWorkQueue;
import hu.bme.mit.theta.cloud.workQueue.WorkQueue;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jersey.errors.LoggingExceptionMapper;
import io.dropwizard.setup.Environment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AnalysisWorkerApplication extends Application<AnalysisWorkerConfiguration> {

    public static void main(String[] args) throws Exception {
        new AnalysisWorkerApplication().run(args);
    }

    @Override
    public void run(AnalysisWorkerConfiguration configuration, Environment environment) throws Exception {
        int cpuCoreCount = Runtime.getRuntime().availableProcessors();
        Executor workExecutor = Executors.newFixedThreadPool(cpuCoreCount);

        WorkQueue workQueue = new RabbitWorkQueue("user", "user", "localhost", 5672);

        AnalysisManager analysisManager = new AnalysisManager(workQueue, workExecutor);

        environment.lifecycle().manage(analysisManager);
        environment.jersey().register(LoggingExceptionMapper.class);
    }
}
