package hu.bme.mit.theta.cloud.worker;

import hu.bme.mit.theta.cloud.rest.service.JobStatus;
import hu.bme.mit.theta.cloud.mail.MailService;
import hu.bme.mit.theta.cloud.repository.dao.*;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.workQueue.WorkQueue;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Executor;

public class AnalysisManager implements Managed {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisManager.class);
    private final Integer workPreFetchCount = 1;

    private final WorkQueue workQueue;
    private final Executor workExecutor;

    private final JobDAORepository jobRepository;
    private final AnalysisBenchmarkDAORepository analysisBenchmarkRepository;
    private final ModelDAORepository modelRepository;

    private final MailService mailService = new MailService();

    private final Analyser thetaAnalyser;

    public AnalysisManager(WorkQueue workQueue, Executor workExecutor, JobDAORepository jobRepository,
                           AnalysisBenchmarkDAORepository analysisBenchmarkRepository, ModelDAORepository modelDAORepository) throws IOException {
        this.workQueue = workQueue;
        this.workExecutor = workExecutor;
        this.jobRepository = jobRepository;
        this.analysisBenchmarkRepository = analysisBenchmarkRepository;
        this.modelRepository = modelDAORepository;

        thetaAnalyser = new ThetaAnalyser(jobRepository, modelRepository, analysisBenchmarkRepository);
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("Starting to consume from the Work Queue");

        workQueue.startConsumingWork(1, (jobId, deliveryTag) -> {
            LOGGER.info("Starting to process model analysis: " + jobId);
            final UUID id = UUID.fromString(jobId);
            final JobEntity jobEntity = jobRepository.findById(id).orElseThrow();

            if (jobEntity == null) {
                LOGGER.error("Could not find job in DB by id: " + id);
                ackWork(jobId, deliveryTag);
                return;
            }

            Analyser.AnalysisProgressListener progressListener = new Analyser.AnalysisProgressListener() {
                @Override
                public void onBegin() {
                    reportAnalysisStarted(jobEntity);
                }

                @Override
                public void onProgress(int progressPercent) {
                    reportAnalysisProgress(jobEntity, progressPercent);
                }

                @Override
                public void onComplete(boolean successful) {
                    reportAnalysisCompleted(jobEntity, successful);
                    ackWork(jobId, deliveryTag);
                }
            };
            thetaAnalyser.runAnalysis(jobEntity, progressListener);

        });
    }

    @Override
    public void stop() throws Exception {

    }

    private void ackWork(String jobId, long deliveryTag) {
        try {
            LOGGER.info("Ack'ing job: " + jobId);
            workQueue.ackWork(deliveryTag);
        } catch (Exception e) {
            LOGGER.error("Unexpected error while ACK'ing message with tag: " + deliveryTag, e);
        }
    }

    private void reportAnalysisStarted(JobEntity job) {
        job.setStatus(JobStatus.IN_PROGRESS.toString());
        jobRepository.save(job);
    }

    private void reportAnalysisProgress(JobEntity job, int newProgress) {
        job.setProgress(newProgress);
        jobRepository.save(job);
    }

    private void reportAnalysisCompleted(JobEntity job, boolean successful) {
        job.setProgress(100);
        job.setStatus(successful ? JobStatus.COMPLETED.toString() : JobStatus.FAILED.toString());

        jobRepository.save(job);
    }
}
