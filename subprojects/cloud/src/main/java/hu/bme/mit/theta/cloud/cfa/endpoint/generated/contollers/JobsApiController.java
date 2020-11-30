package hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.AllJobsResponse;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.CexFileResponse;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.InlineResponse200;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
                return new ResponseEntity<AllJobsResponse>(objectMapper.readValue("[ {\n  \"jobId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"configId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}, {\n  \"jobId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"configId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n} ]", AllJobsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AllJobsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AllJobsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<InlineResponse200> getAnalysisResult(@Parameter(in = ParameterIn.PATH, description = "The analysis job id", required=true, schema=@Schema()) @PathVariable("jobId") UUID jobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<InlineResponse200>(objectMapper.readValue("\"\"", InlineResponse200.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<InlineResponse200>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<InlineResponse200>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CexFileResponse> getCexFile(@Parameter(in = ParameterIn.PATH, description = "The analysis job id", required=true, schema=@Schema()) @PathVariable("jobId") UUID jobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CexFileResponse>(objectMapper.readValue("{\n  \"cex\" : \"\"\n}", CexFileResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CexFileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CexFileResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
