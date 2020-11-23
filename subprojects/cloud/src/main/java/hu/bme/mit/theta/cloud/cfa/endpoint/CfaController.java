package hu.bme.mit.theta.cloud.cfa.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.CfaApi;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.*;
import hu.bme.mit.theta.cloud.cfa.service.CfaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.MulticastSocket;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CfaController implements CfaApi {

    @Autowired
    private CfaService cfaService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<GetModelFileResponse> getCfaModel(UUID modelId) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse200> getProcessResult(UUID modelId, UUID processId) {
        return null;
    }

    @Override
    public ResponseEntity<List<StartProcessResponse>> startCfaAnalysis(UUID modelId, List<ModelConfig> body) {
        return null;
    }

    @Override
    public ResponseEntity<CreateModelResponse> uploadCfa(MultipartFile model) {
        CreateModelResponse createModelResponse = new CreateModelResponse();
        createModelResponse.setFileName("filename");
        createModelResponse.setModelId(UUID.randomUUID());
        return ResponseEntity.ok(cfaService.createModel(model));
    }

    public ResponseEntity<?> getModel (@Parameter(in = ParameterIn.PATH, description = "The model id", required=true, schema=@Schema()) @PathVariable("modelId") String fileName) {
        return ResponseEntity.ok().body(new FileSystemResource("tmp/models/model.cfa"));
    }
}
