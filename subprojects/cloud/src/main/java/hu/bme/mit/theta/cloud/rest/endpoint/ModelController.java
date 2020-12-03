package hu.bme.mit.theta.cloud.rest.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers.ModelApi;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.contollers.NotFoundException;
import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.*;
import hu.bme.mit.theta.cloud.rest.service.JobService;
import hu.bme.mit.theta.cloud.rest.service.ModelService;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ModelController implements ModelApi {

    @Autowired
    private ModelService modelService;

    @Autowired
    private JobService jobService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Resource> getModelFile(UUID modelId) {
        FileSystemResource modelFile = null;
        try {
            modelFile = modelService.getModelFile(modelId);
        } catch (NotFoundException | IOException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(modelFile);
    }

    @Override
    public ResponseEntity<GetModelMetadataResponse> getModelMetadata(UUID modelId) {
        GetModelMetadataResponse getModelMetadataResponse = new GetModelMetadataResponse();
        try {
            ModelEntity modelEntity = modelService.getModelMetadata(modelId);
            getModelMetadataResponse.setModelId(modelId);
            getModelMetadataResponse.setFileName(modelEntity.getFileName());
            getModelMetadataResponse.setModelType(modelEntity.getModelType());
            getModelMetadataResponse.setCreationDate(modelEntity.getCreationDate().toString());

            return ResponseEntity.ok(getModelMetadataResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<GetModelMetricsResponse> getModelMetrics(UUID modelId) {
        return ResponseEntity.ok(modelService.getModelMetrics(modelId));
    }

    @Override
    public ResponseEntity<List<StartProcessResponse>> startAnalysis(UUID modelId, List<AnalysisConfig> body) {
        try {
            jobService.startAnalysis(modelId, body);
            return ResponseEntity.accepted().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<CreateModelResponse> uploadModel(MultipartFile model) {
        CreateModelResponse createModelResponse = new CreateModelResponse();
        createModelResponse.setFileName("filename");
        createModelResponse.setModelId(UUID.randomUUID());
        return ResponseEntity.ok(modelService.createModel(model));
    }

    @Override
    public ResponseEntity<Resource> visualizeModel(UUID modelId) {
        try {
            return ResponseEntity.ok().body(modelService.visualizeModel(modelId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
