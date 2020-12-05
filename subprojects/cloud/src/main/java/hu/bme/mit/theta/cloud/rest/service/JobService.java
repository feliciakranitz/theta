package hu.bme.mit.theta.cloud.rest.service;

import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers.NotFoundException;
import hu.bme.mit.theta.cloud.repository.JobRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.*;
import hu.bme.mit.theta.cloud.workQueue.RabbitWorkQueue;
import hu.bme.mit.theta.cloud.workQueue.WorkQueue;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    private final LocalBlobStore localBlobStore = new LocalBlobStore();

    private final SolverFactory solverFactory = Z3SolverFactory.getInstance();

    @Autowired
    private ConfigService configService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private JobRepository jobRepository;

    private WorkQueue workQueue = new RabbitWorkQueue("user", "user", "localhost", 5672);

    public JobService() throws IOException {
    }

    public void startCfaAnalysis(UUID modelId, StartCfaProcessRequest body) throws Exception {

        List<ConfigurationEntity> configurationEntities = configService.createCfaConfiguration(body.getConfigs());
        ModelEntity modelEntity = modelService.getModelMetadata(modelId);

        createJob(modelEntity, configurationEntities, body.getNotificationAddress());

    }

    public void startStsAnalysis(UUID modelId, StartStsProcessRequest body) throws Exception {

        List<ConfigurationEntity> configurationEntities = configService.createStsConfiguration(body.getConfigs());
        ModelEntity modelEntity = modelService.getModelMetadata(modelId);

        createJob(modelEntity, configurationEntities, body.getNotificationAddress());

    }

    public void startXtaAnalysis(UUID modelId, StartXtaProcessRequest body) throws Exception {

        List<ConfigurationEntity> configurationEntities = configService.createXtaConfiguration(body.getConfigs());
        ModelEntity modelEntity = modelService.getModelMetadata(modelId);

        createJob(modelEntity, configurationEntities, body.getNotificationAddress());

    }

    public void startXstsAnalysis(UUID modelId, StartXstsProcessRequest body) throws Exception {

        List<ConfigurationEntity> configurationEntities = configService.createXstsConfiguration(body.getConfigs());
        ModelEntity modelEntity = modelService.getModelMetadata(modelId);

        createJob(modelEntity, configurationEntities, body.getNotificationAddress());

    }

    private void createJob(ModelEntity modelEntity, List<ConfigurationEntity> configurationEntities, String notificationAddress) throws Exception {
        for (ConfigurationEntity configurationEntity : configurationEntities) {
            JobEntity jobEntity = new JobEntity();

            jobEntity.setModel(modelEntity);
            jobEntity.setNotificationAddress(notificationAddress);
            jobEntity.setConfig(configurationEntity);
            jobEntity.setStatus(JobStatus.WAITING.toString());
            jobEntity.setProgress(0);

            jobEntity = jobRepository.saveAndFlush(jobEntity);

            try {
                workQueue.pushWork(jobEntity.getJobId().toString());
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    public JobEntity getJob(UUID jobId) throws NotFoundException {
        return jobRepository.findById(jobId).orElseThrow(this::jobNotFoundException);
    }

    public List<JobResponse> getAllJob() {
        List<JobResponse> jobResponses = new ArrayList<>();
        List<JobEntity> jobEntities = jobRepository.findAll();
        for (JobEntity jobEntity: jobEntities) {
            JobResponse jobResponse = new JobResponse();

            jobResponse.setJobId(jobEntity.getJobId());
            jobResponse.setStatus(jobEntity.getStatus());
            jobResponse.setFileName(jobEntity.getModel().getFileName());
            jobResponse.setHasCex(jobEntity.isCexFile());
            jobResponse.setIsSafe(jobEntity.isSafe());

            if(jobEntity.getBenchmark() != null) {
                AnalysisBenchmark analysisBenchmark = new AnalysisBenchmark();

                analysisBenchmark.setTimeElapsed(jobEntity.getBenchmark().getTimeElapsed());
                analysisBenchmark.setAlgorithmTimeMs(jobEntity.getBenchmark().getAlgorithmTimeMs());
                analysisBenchmark.setAbstractorTimeMs(jobEntity.getBenchmark().getAbstractorTimeMs());
                analysisBenchmark.setRefinerTimeMs(jobEntity.getBenchmark().getRefinerTimeMs());
                analysisBenchmark.setIterations(jobEntity.getBenchmark().getIterations());
                analysisBenchmark.setArgSize(jobEntity.getBenchmark().getArgSize());
                analysisBenchmark.setArgDepth(jobEntity.getBenchmark().getArgDepth());
                analysisBenchmark.setArgMeanBranchingFactor((long) jobEntity.getBenchmark().getArgMeanBranchingFactor());

                jobResponse.setAnalysisBenchmark(analysisBenchmark);
            }

            jobResponses.add(jobResponse);
        }
        return jobResponses;
    }

    public FileSystemResource getCexFile(UUID jobId) throws NotFoundException, IOException {
        String format = getJob(jobId).getConfig().getConfigType() == "XTA" ? "dot" : "txt";
        return localBlobStore.getCexBlob(jobId, format);
    }


    private NotFoundException jobNotFoundException() {
        return new NotFoundException(0, "Job not found by id");
    }


}
