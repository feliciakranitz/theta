package hu.bme.mit.theta.cloud.worker;

import com.google.common.base.Stopwatch;
import hu.bme.mit.theta.analysis.Trace;
import hu.bme.mit.theta.analysis.algorithm.SafetyResult;
import hu.bme.mit.theta.analysis.algorithm.cegar.CegarStatistics;
import hu.bme.mit.theta.analysis.expl.ExplState;
import hu.bme.mit.theta.analysis.expr.ExprState;
import hu.bme.mit.theta.analysis.expr.refinement.PruneStrategy;
import hu.bme.mit.theta.cfa.CFA;
import hu.bme.mit.theta.cfa.analysis.CfaAction;
import hu.bme.mit.theta.cfa.analysis.CfaState;
import hu.bme.mit.theta.cfa.analysis.CfaTraceConcretizer;
import hu.bme.mit.theta.cfa.analysis.config.CfaConfig;
import hu.bme.mit.theta.cfa.analysis.config.CfaConfigBuilder;
import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.rest.service.ModelService;
import hu.bme.mit.theta.cloud.repository.dao.*;
import hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.common.logging.FileLogger;
import hu.bme.mit.theta.common.logging.Logger;
import hu.bme.mit.theta.core.model.Valuation;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import hu.bme.mit.theta.sts.STS;
import hu.bme.mit.theta.sts.analysis.StsAction;
import hu.bme.mit.theta.sts.analysis.StsTraceConcretizer;
import hu.bme.mit.theta.sts.analysis.config.StsConfig;
import hu.bme.mit.theta.sts.analysis.config.StsConfigBuilder;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

public class ThetaAnalyser implements Analyser {

    private Logger logger;

    private final LocalBlobStore localBlobStore = new LocalBlobStore();

    private final SolverFactory solverFactory = Z3SolverFactory.getInstance();

    private ModelService modelService = new ModelService();

    private JobDAORepository jobRepository;
    private ModelDAORepository modelRepository;
    private  AnalysisBenchmarkDAORepository analysisBenchmarkRepository;

    public ThetaAnalyser(JobDAORepository jobDAORepository, ModelDAORepository modelDAORepository, AnalysisBenchmarkDAORepository analysisBenchmarkDAORepository) throws IOException {
        this.jobRepository = jobDAORepository;
        this.modelRepository = modelDAORepository;
        this.analysisBenchmarkRepository = analysisBenchmarkDAORepository;
    }

    @Override
    public void runAnalysis(JobEntity jobEntity, AnalysisProgressListener progressListener) {

        switch (jobEntity.getModel().getModelType()) {
            case "CFA":
                try {
                    runCfaAnalysis(jobEntity, progressListener);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "SYSTEM":
                try {
                    runStsAnalysis(jobEntity, progressListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void runCfaAnalysis(JobEntity jobEntity, AnalysisProgressListener progressListener) throws FileNotFoundException {
        ModelEntity modelEntity = jobEntity.getModel();
        ConfigurationEntity configurationEntity = jobEntity.getConfig();
        logger = new FileLogger(Logger.Level.valueOf(configurationEntity.getLogLevel()),"/tmp/theta/logs/" + jobEntity.getJobId().toString() + ".txt", true, true);
        try {
            progressListener.onBegin();
            final Stopwatch sw = Stopwatch.createStarted();
            CFA cfa = modelService.loadCfaModel(modelEntity);

            CFA.Loc errLoc = null;
            if (cfa.getErrorLoc().isPresent()) {
                errLoc = cfa.getErrorLoc().get();
            }

            if (configurationEntity.getErrorLoc() != null) {
                errLoc = null;
                for (CFA.Loc running : cfa.getLocs()) {
                    if (running.getName().equals(configurationEntity.getErrorLoc())) {
                        errLoc = running;
                    }
                }
                checkNotNull(errLoc, "Location '" + configurationEntity.getErrorLoc() + "' not found in CFA");
            }

            checkNotNull(errLoc, "Error location must be specified in CFA or as argument");

            final CfaConfig<?, ?, ?> configuration = buildCfaConfiguration(cfa, errLoc, configurationEntity);
            final SafetyResult<?, ?> status = cfaCheck(configuration);
            sw.stop();

            AnalysisBenchmarkEntity analysisBenchmarkEntity = mapToBenchmarkEntity(status, sw.elapsed(TimeUnit.MILLISECONDS));
            analysisBenchmarkEntity = analysisBenchmarkRepository.save(analysisBenchmarkEntity);

            jobEntity.setSafe(status.isSafe());
            jobEntity.setBenchmark(analysisBenchmarkEntity);

            if (status.isUnsafe() && configurationEntity.getCexFile()) {
                writeCfaCex(status.asUnsafe(), jobEntity);
            }

            jobRepository.save(jobEntity);

            progressListener.onComplete(true);
        } catch (Exception e) {
            logError(configurationEntity, e);
            progressListener.onComplete(false);
        }
    }

    private void runStsAnalysis(JobEntity jobEntity, AnalysisProgressListener progressListener) throws Exception {
        ModelEntity modelEntity = jobEntity.getModel();
        ConfigurationEntity configurationEntity = jobEntity.getConfig();
        logger = new FileLogger(Logger.Level.valueOf(configurationEntity.getLogLevel()),"/tmp/theta/logs/" + jobEntity.getJobId().toString() + ".txt", true, true);

        final Stopwatch sw = Stopwatch.createStarted();
        final STS sts = modelService.loadStsModel(modelEntity);
        final StsConfig<?, ?, ?> configuration = buildStsConfiguration(sts, configurationEntity);
        final SafetyResult<?, ?> status = stsCheck(configuration);
        sw.stop();

        AnalysisBenchmarkEntity analysisBenchmarkEntity = mapToBenchmarkEntity(status, sw.elapsed(TimeUnit.MILLISECONDS));
        analysisBenchmarkEntity = analysisBenchmarkRepository.save(analysisBenchmarkEntity);

        jobEntity.setSafe(status.isSafe());
        jobEntity.setBenchmark(analysisBenchmarkEntity);

        if (status.isUnsafe() && configurationEntity.getCexFile()) {
            writeStsCex(sts, status.asUnsafe(), jobEntity);
        }
    }

    private void runXtaAnalysis() {
    }

    private void runXstsAnalysis() {
    }

    private CfaConfig<?, ?, ?> buildCfaConfiguration(final CFA cfa, CFA.Loc errorLoc, ConfigurationEntity configuration) {
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
                .build(cfa, errorLoc);
    }

    private StsConfig<?, ?, ?> buildStsConfiguration(final STS sts, ConfigurationEntity configuration) throws Exception {
        try {
            return new StsConfigBuilder(StsConfigBuilder.Domain.valueOf(configuration.getDomainName()),
                    StsConfigBuilder.Refinement.valueOf(configuration.getRefinement()),
                    Z3SolverFactory.getInstance())
                    .initPrec(StsConfigBuilder.InitPrec.valueOf(configuration.getInitPrec()))
                    .search(StsConfigBuilder.Search.valueOf(configuration.getSearch()))
                    .predSplit(StsConfigBuilder.PredSplit.valueOf(configuration.getPredSplit()))
                    .pruneStrategy(PruneStrategy.valueOf(configuration.getPruneStrategy()))
                    .logger(logger).build(sts);
        } catch (final Exception ex) {
            throw new Exception("Could not create configuration: " + ex.getMessage(), ex);
        }
    }

    private SafetyResult<?, ?> cfaCheck(CfaConfig<?, ?, ?> configuration) throws Exception {
        try {
            return configuration.check();
        } catch (final Exception ex) {
            throw new Exception("Error while running algorithm: " + ex.getMessage(), ex);
        }
    }

    private SafetyResult<?, ?> stsCheck(StsConfig<?, ?, ?> configuration) throws Exception {
        try {
            return configuration.check();
        } catch (final Exception ex) {
            throw new Exception("Error while running algorithm: " + ex.getMessage(), ex);
        }
    }

    private AnalysisBenchmarkEntity mapToBenchmarkEntity(SafetyResult<?, ?> status, long timeElapsed) {

        final CegarStatistics stats = (CegarStatistics) status.getStats().get();
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

    private void writeCfaCex(final SafetyResult.Unsafe<?, ?> status, JobEntity jobEntity) throws FileNotFoundException {
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

    private void writeStsCex(final STS sts, final SafetyResult.Unsafe<?, ?> status, JobEntity jobEntity) throws FileNotFoundException {
        @SuppressWarnings("unchecked") final Trace<ExprState, StsAction> trace = (Trace<ExprState, StsAction>) status.getTrace();
        final Trace<Valuation, StsAction> concrTrace = StsTraceConcretizer.concretize(sts, trace, Z3SolverFactory.getInstance());
        final File file = new File("/tmp/theta/cexFiles/" + jobEntity.getJobId() + ".txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            for (Valuation state : concrTrace.getStates()) {
                printWriter.println(state.toString());
            }
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private void logError(ConfigurationEntity configurationEntity, final Throwable ex) {
        final String message = ex.getMessage() == null ? "" : ex.getMessage();
        logger.write(Logger.Level.RESULT, "%s occurred, message: %s%n", ex.getClass().getSimpleName(), message);
        if (configurationEntity.getStacktrace()) {
            final StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            logger.write(Logger.Level.RESULT, "Trace:%n%s%n", errors.toString());
        } else {
            logger.write(Logger.Level.RESULT, "Set stacktrace true for stack trace%n");
        }
    }
}
