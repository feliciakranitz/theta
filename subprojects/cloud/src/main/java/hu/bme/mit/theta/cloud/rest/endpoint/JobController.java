package hu.bme.mit.theta.cloud.rest.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers.JobsApi;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers.NotFoundException;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.AllJobsResponse;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.AnalysisBenchmark;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.JobResponse;
import hu.bme.mit.theta.cloud.rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class JobController implements JobsApi {

    @Autowired
    private JobService jobService;

    private final LocalBlobStore localBlobStore = new LocalBlobStore();

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        List<JobResponse> jobResponses = jobService.getAllJob();
        return ResponseEntity.ok(jobResponses);
    }

    @Override
    public ResponseEntity<JobResponse> getAnalysisResult(UUID jobId) {
        try {
            JobEntity jobEntity = jobService.getJob(jobId);
            JobResponse jobResponse = new JobResponse();
            jobResponse.setJobId(jobEntity.getJobId());
            jobResponse.setStatus(jobEntity.getStatus());
            jobResponse.setFileName(jobEntity.getModel().getFileName());
            jobResponse.setHasCex(jobEntity.isCexFile());
            jobResponse.setIsSafe(jobEntity.isSafe());

            if (jobEntity.getBenchmark() != null) {

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
            return ResponseEntity.ok(jobResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Resource> getCexFile(UUID jobId) {
        try {
            FileSystemResource cexFile = jobService.getCexFile(jobId);
            return ResponseEntity.ok().body(cexFile);
        } catch (NotFoundException | IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Resource> getLogFile(UUID jobId) {
        FileSystemResource logFile = localBlobStore.getLogBlob(jobId);
        return ResponseEntity.ok().body(logFile);
    }
}