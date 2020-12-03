package hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers;

import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.AllJobsResponse;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.JobResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.io.IOException;

@RestController
public class JobsApiController implements JobsApi {

    private static final Logger log = LoggerFactory.getLogger(JobsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public JobsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<AllJobsResponse> getAllJobs() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AllJobsResponse>(objectMapper.readValue("[ {\n  \"jobId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"fileName\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"hasCex\" : true,\n  \"analysisBenchmark\" : {\n    \"timeElapsed\" : 0.8008281904610115,\n    \"argSize\" : 2.3021358869347655,\n    \"abstractorTimeMs\" : 1.4658129805029452,\n    \"argDepth\" : 7.061401241503109,\n    \"argMeanBranchingFactor\" : 9.301444243932576,\n    \"refinerTimeMs\" : 5.962133916683182,\n    \"algorithmTimeMs\" : 6.027456183070403,\n    \"iterations\" : 5.637376656633329\n  },\n  \"isSafe\" : true,\n  \"status\" : \"status\"\n}, {\n  \"jobId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"fileName\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"hasCex\" : true,\n  \"analysisBenchmark\" : {\n    \"timeElapsed\" : 0.8008281904610115,\n    \"argSize\" : 2.3021358869347655,\n    \"abstractorTimeMs\" : 1.4658129805029452,\n    \"argDepth\" : 7.061401241503109,\n    \"argMeanBranchingFactor\" : 9.301444243932576,\n    \"refinerTimeMs\" : 5.962133916683182,\n    \"algorithmTimeMs\" : 6.027456183070403,\n    \"iterations\" : 5.637376656633329\n  },\n  \"isSafe\" : true,\n  \"status\" : \"status\"\n} ]", AllJobsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AllJobsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AllJobsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<JobResponse> getAnalysisResult(@Parameter(in = ParameterIn.PATH, description = "The analysis job id", required=true, schema=@Schema()) @PathVariable("jobId") UUID jobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<JobResponse>(objectMapper.readValue("{\n  \"jobId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"fileName\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"hasCex\" : true,\n  \"analysisBenchmark\" : {\n    \"timeElapsed\" : 0.8008281904610115,\n    \"argSize\" : 2.3021358869347655,\n    \"abstractorTimeMs\" : 1.4658129805029452,\n    \"argDepth\" : 7.061401241503109,\n    \"argMeanBranchingFactor\" : 9.301444243932576,\n    \"refinerTimeMs\" : 5.962133916683182,\n    \"algorithmTimeMs\" : 6.027456183070403,\n    \"iterations\" : 5.637376656633329\n  },\n  \"isSafe\" : true,\n  \"status\" : \"status\"\n}", JobResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<JobResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<JobResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Resource> getCexFile(@Parameter(in = ParameterIn.PATH, description = "The analysis job id", required=true, schema=@Schema()) @PathVariable("jobId") UUID jobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Resource>(objectMapper.readValue("\"\"", Resource.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Resource>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<byte[]> getLogFile(@Parameter(in = ParameterIn.PATH, description = "The analysis job id", required=true, schema=@Schema()) @PathVariable("jobId") UUID jobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<byte[]>(objectMapper.readValue("\"\"", byte[].class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<byte[]>(HttpStatus.NOT_IMPLEMENTED);
    }

}
