package hu.bme.mit.theta.cloud.cfa.endpoint.generated;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CfaApiController implements CfaApi {

    private static final Logger log = LoggerFactory.getLogger(CfaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CfaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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

    public ResponseEntity<GetModelFileResponse> getCfaModel(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetModelFileResponse>(objectMapper.readValue("{\n  \"fileName\" : \"fileName\",\n  \"content\" : \"\"\n}", GetModelFileResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetModelFileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetModelFileResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<InlineResponse200> getProcessResult(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId,@Parameter(in = ParameterIn.PATH, description = "The analysis process id", required=true, schema=@Schema()) @PathVariable("processId") UUID processId) {
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

    public ResponseEntity<List<StartProcessResponse>> startCfaAnalysis(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema())  @RequestBody List<ModelConfig> body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<StartProcessResponse>>(objectMapper.readValue("[ {\n  \"processId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}, {\n  \"processId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<StartProcessResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<StartProcessResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CreateModelResponse> uploadCfa(MultipartFile model) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateModelResponse>(objectMapper.readValue("{\n  \"fileName\" : \"fileName\",\n  \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", CreateModelResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateModelResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateModelResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
