package hu.bme.mit.theta.cloud.worker;

import com.google.common.base.Stopwatch;
import hu.bme.mit.theta.analysis.Analysis;
import hu.bme.mit.theta.analysis.Trace;
import hu.bme.mit.theta.analysis.algorithm.SafetyResult;
import hu.bme.mit.theta.analysis.algorithm.cegar.CegarStatistics;
import hu.bme.mit.theta.analysis.expl.ExplState;
import hu.bme.mit.theta.cfa.CFA;
import hu.bme.mit.theta.cfa.analysis.CfaAction;
import hu.bme.mit.theta.cfa.analysis.CfaState;
import hu.bme.mit.theta.cfa.analysis.CfaTraceConcretizer;
import hu.bme.mit.theta.cfa.analysis.config.CfaConfig;
import hu.bme.mit.theta.cfa.analysis.config.CfaConfigBuilder;
import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.cfa.service.ConfigService;
import hu.bme.mit.theta.cloud.cfa.service.ModelService;
import hu.bme.mit.theta.cloud.repository.AnalysisBenchmarkRepository;
import hu.bme.mit.theta.cloud.repository.JobRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.common.logging.FileLogger;
import hu.bme.mit.theta.common.logging.Logger;
import hu.bme.mit.theta.common.logging.NullLogger;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ThetaAnalyser implements Analyser {

    private final LocalBlobStore localBlobStore = new LocalBlobStore();

    private final SolverFactory solverFactory = Z3SolverFactory.getInstance();

    @Autowired
    private ConfigService configService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private AnalysisBenchmarkRepository analysisBenchmarkRepository;

    @Autowired
    private JobRepository jobRepository;

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
        System.out.println("runanalysis");
        try {
            Logger logger = new FileLogger(Logger.Level.valueOf(configurationEntity.getLogLevel()),"/tmp/theta/logs/" + jobEntity.getJobId().toString() + ".txt", true, false);

            progressListener.onBegin();
            final Stopwatch sw = Stopwatch.createStarted();
            CFA cfa = modelService.loadModel(modelEntity.getModelId());

            final CfaConfig<?, ?, ?> configuration = buildConfiguration(cfa, configurationEntity, logger);
            final SafetyResult<?, ?> status = check(configuration);
            sw.stop();
            final CegarStatistics stats = (CegarStatistics) status.getStats().get();

            AnalysisBenchmarkEntity analysisBenchmarkEntity = mapToBenchmarkEntity(status, stats, sw.elapsed(TimeUnit.MILLISECONDS));
            analysisBenchmarkEntity = analysisBenchmarkRepository.saveAndFlush(analysisBenchmarkEntity);

            jobEntity.setSafe(status.isSafe());
            jobEntity.setBenchmark(analysisBenchmarkEntity);

            if (status.isUnsafe() && configurationEntity.getCexFile()) {
                writeCex(status.asUnsafe(), jobEntity);
            }

            jobRepository.save(jobEntity);

            progressListener.onComplete(true);
        } catch (Exception e) {
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

    private AnalysisBenchmarkEntity mapToBenchmarkEntity(SafetyResult<?, ?> status, CegarStatistics stats, long timeElapsed) {
        AnalysisBenchmarkEntity analysisBenchmarkEntity = new AnalysisBenchmarkEntity();

        analysisBenchmarkEntity.setTimeElapsed(timeElapsed);
        analysisBenchmarkEntity.setAlgorithmTimeMs( stats.getAlgorithmTimeMs());
        analysisBenchmarkEntity.setAbstractorTimeMs(stats.getAbstractorTimeMs());
        analysisBenchmarkEntity.setRefinerTimeMs(stats.getRefinerTimeMs());
        analysisBenchmarkEntity.setIterations(stats.getIterations());
        analysisBenchmarkEntity.setArgSize( status.getArg().size());
        analysisBenchmarkEntity.setArgDepth(status.getArg().getDepth());
        analysisBenchmarkEntity.setArgMeanBranchingFactor(status.getArg().getMeanBranchingFactor());

        return analysisBenchmarkEntity;
    }

    private void writeCex(final SafetyResult.Unsafe<?, ?> status, JobEntity jobEntity) throws FileNotFoundException {
        @SuppressWarnings("unchecked") final Trace<CfaState<?>, CfaAction> trace = (Trace<CfaState<?>, CfaAction>) status.getTrace();
        final Trace<CfaState<ExplState>, CfaAction> concrTrace = CfaTraceConcretizer.concretize(trace, Z3SolverFactory.getInstance());
        final File file = new File("/tmp/theta/cexFiles/" + jobEntity.getJobId() + ".txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            printWriter.write(concrTrace.toString());
            jobEntity.setCexFile(true);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
