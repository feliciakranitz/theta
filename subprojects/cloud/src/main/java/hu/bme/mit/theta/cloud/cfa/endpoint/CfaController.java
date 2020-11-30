package hu.bme.mit.theta.cloud.cfa.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.CfaApi;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.NotFoundException;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.*;
import hu.bme.mit.theta.cloud.cfa.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CfaController implements CfaApi {

    @Autowired
    private AnalysisService analysisService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
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
        return ResponseEntity.ok(analysisService.createModel(model));
    }

    @Override
    public ResponseEntity<?> getCfaModel(UUID modelId) {
        try {
            return ResponseEntity.ok().body(analysisService.getModelFileContent(modelId));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}
