package hu.bme.mit.theta.cloud.worker;

import com.google.common.base.Stopwatch;
import hu.bme.mit.theta.analysis.algorithm.SafetyResult;
import hu.bme.mit.theta.analysis.algorithm.cegar.CegarStatistics;
import hu.bme.mit.theta.cfa.CFA;
import hu.bme.mit.theta.cfa.analysis.config.CfaConfig;
import hu.bme.mit.theta.cfa.analysis.config.CfaConfigBuilder;
import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.cfa.service.ConfigService;
import hu.bme.mit.theta.cloud.cfa.service.ModelService;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.common.logging.FileLogger;
import hu.bme.mit.theta.common.logging.Logger;
import hu.bme.mit.theta.common.logging.NullLogger;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;

public class ThetaAnalyser implements Analyser {

    private final LocalBlobStore localBlobStore = new LocalBlobStore();

    private final SolverFactory solverFactory = Z3SolverFactory.getInstance();

    @Autowired
    private ConfigService configService;

    @Autowired
    private ModelService modelService;

    @Override
    public void runAnalysis(JobEntity jobEntity, AnalysisProgressListener progressListener) {

        switch (jobEntity.getModel().getModelType()) {
            case "CFA":
                System.out.println("CFA model");
                runCfaAnalysis(jobEntity, progressListener);
                break;
            case "SYSTEM":
                System.out.println("system model");
                // runStsAnalysis();
                break;
            case "XTA":
                System.out.println("xta model");
                //runXtaAnalysis();
                break;
            case "XSTS":
                System.out.println("xts model");
                //runXstsAnalysis();
                break;
            default:
                return;
        }
    }

    private void runCfaAnalysis(JobEntity jobEntity, AnalysisProgressListener progressListener) {
        ModelEntity modelEntity = jobEntity.getModel();
        ConfigurationEntity configurationEntity = jobEntity.getConfig();

        try {
            Logger logger = new FileLogger(Logger.Level.valueOf(configurationEntity.getLogLevel()),"/tmp/theta/logs/" + jobEntity.getJobId().toString() + ".txt", false, false);

            progressListener.onBegin();
            final Stopwatch sw = Stopwatch.createStarted();
            CFA cfa = modelService.loadModel(modelEntity.getModelId());

            final CfaConfig<?, ?, ?> configuration = buildConfiguration(cfa, configurationEntity, logger);
            final SafetyResult<?, ?> status = check(configuration);
            sw.stop();
            progressListener.onComplete(true);
            final CegarStatistics stats = (CegarStatistics) status.getStats().get();
        } catch (Exception e) {
            e.printStackTrace();
            progressListener.onComplete(false);
        }
    }

    private void runStsAnalysis() {
    }

    private void runXtaAnalysis() {
    }

    private void runXstsAnalysis() {
    }

    private CfaConfig<?, ?, ?> buildConfiguration(final CFA cfa, ConfigurationEntity configuration, Logger logger) {
        return new CfaConfigBuilder(
                CfaConfigBuilder.Domain.valueOf(configuration.getDomainName()),
                CfaConfigBuilder.Refinement.valueOf(configuration.getRefinement()), this.solverFactory)
                .precGranularity(CfaConfigBuilder.PrecGranularity.valueOf(configuration.getPrecGranularity()))
                .search(CfaConfigBuilder.Search.valueOf(configuration.getSearch()))
                .predSplit(CfaConfigBuilder.PredSplit.valueOf(configuration.getPredSplit()))
                .encoding(CfaConfigBuilder.Encoding.valueOf(configuration.getEncoding()))
                .maxEnum(configuration.getMaxEnum())
                .initPrec(CfaConfigBuilder.InitPrec.valueOf(configuration.getInitPrec()))
                .logger(logger)
                .build(cfa, null);
    }

    private SafetyResult<?, ?> check(CfaConfig<?, ?, ?> configuration) throws Exception {
        try {
            return configuration.check();
        } catch (final Exception ex) {
            throw new Exception("Error while running algorithm: " + ex.getMessage(), ex);
        }
    }
}
