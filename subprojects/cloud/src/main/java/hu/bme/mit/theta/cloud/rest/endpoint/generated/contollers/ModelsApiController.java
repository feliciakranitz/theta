package hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.AllModelMetadataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.io.IOException;

@RestController
public class ModelsApiController implements ModelsApi {

    private static final Logger log = LoggerFactory.getLogger(ModelsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ModelsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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

    public ResponseEntity<AllModelMetadataResponse> getAllModelMetadata() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AllModelMetadataResponse>(objectMapper.readValue("{\n  \"modelList\" : [ {\n    \"fileName\" : \"fileName\",\n    \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"modelType\" : \"modelType\",\n    \"creationDate\" : \"creationDate\"\n  }, {\n    \"fileName\" : \"fileName\",\n    \"modelId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"modelType\" : \"modelType\",\n    \"creationDate\" : \"creationDate\"\n  } ]\n}", AllModelMetadataResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AllModelMetadataResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AllModelMetadataResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
