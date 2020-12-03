package hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers;


import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.core.io.Resource;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.io.IOException;
import java.util.List;

public class ModelApiController implements ModelApi {

    private static final Logger log = LoggerFactory.getLogger(ModelApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ModelApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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

    public ResponseEntity<Resource> getModelFile(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId) {
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

    public ResponseEntity<GetModelMetadataResponse> getModelMetadata(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetModelMetadataResponse>(objectMapper.readValue("{\n  \"fileName\" : \"fileName\",\n  \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"modelType\" : \"modelType\",\n  \"creationDate\" : \"creationDate\"\n}", GetModelMetadataResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetModelMetadataResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetModelMetadataResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetModelMetricsResponse> getModelMetrics(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetModelMetricsResponse>(objectMapper.readValue("{\n  \"locs\" : 2.3021358869347655,\n  \"assignments\" : 9.301444243932576,\n  \"havocs\" : 2.027123023002322,\n  \"intVars\" : 1.4658129805029452,\n  \"arrayVars\" : 5.637376656633329,\n  \"edges\" : 7.061401241503109,\n  \"vars\" : 0.8008281904610115,\n  \"bitvectorVars\" : 5.962133916683182,\n  \"boolVars\" : 6.027456183070403,\n  \"cyclomaticComplexity\" : 4.145608029883936,\n  \"assumptions\" : 3.616076749251911\n}", GetModelMetricsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetModelMetricsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetModelMetricsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<StartProcessResponse>> startAnalysis(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema())  @RequestBody List<AnalysisConfig> body) {
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

    public ResponseEntity<CreateModelResponse> uploadModel(@RequestParam(value = "model") MultipartFile model) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateModelResponse>(objectMapper.readValue("{\n  \"fileName\" : \"fileName\",\n  \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"modelType\" : \"modelType\"\n}", CreateModelResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateModelResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateModelResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Resource> visualizeModel(@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") UUID modelId) {
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

}
