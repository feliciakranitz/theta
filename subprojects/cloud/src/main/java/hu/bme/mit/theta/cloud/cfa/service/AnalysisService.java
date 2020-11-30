package hu.bme.mit.theta.cloud.cfa.service;

import hu.bme.mit.theta.cloud.blobstore.LocalBlobStore;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.NotFoundException;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.ModelConfig;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.CreateModelResponse;
import hu.bme.mit.theta.cloud.repository.ModelRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.solver.SolverFactory;
import hu.bme.mit.theta.solver.z3.Z3SolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnalysisService {

    private final LocalBlobStore localBlobStore;
    private final SolverFactory solverFactory =  Z3SolverFactory.getInstance();

    @Autowired
    private ModelRepository modelRepository;

    public AnalysisService() {
        this.localBlobStore = new LocalBlobStore("/tmp/theta");
    }

    public CreateModelResponse createModel(MultipartFile modelFile) {
        String[] splitFileName = StringUtils.split(modelFile.getOriginalFilename(), ".");
        if(splitFileName == null) {
            return null;
        }
        String modelType = splitFileName[splitFileName.length-1];

        //save model in DB
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setFileName(modelFile.getOriginalFilename());
        modelEntity.setFileSize(modelFile.getSize());
        modelEntity.setModelType(modelType.toUpperCase());

        // creationDate field (with the CreationTimestamp annotation) is only calculated on flush
        modelEntity = modelRepository.saveAndFlush(modelEntity);

        //save in blob store
        try {
            localBlobStore.saveModelBlob(modelFile.getInputStream(), modelEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CreateModelResponse createModelResponse = new CreateModelResponse();
        createModelResponse.setModelId(modelEntity.getModelId());
        createModelResponse.setFileName(modelEntity.getFileName());
        return createModelResponse;
   }

   public ModelEntity getModelMetadata(UUID modelId) throws NotFoundException {
        return modelRepository.findById(modelId).orElseThrow(this::modelNotFoundException);
   }

   public FileSystemResource getModelFileContent(UUID modelId) throws NotFoundException {
        return localBlobStore.getModelBlob(getModelMetadata(modelId));
   }

   public void createConfiguration(ModelConfig modelConfig) {
       ConfigurationEntity configurationEntity = new ConfigurationEntity();
       configurationEntity.setDomainName(modelConfig.getDomain().toString());

   }

   public void startAnalysis(UUID modelId, ArrayList<ModelConfig> modelConfigs) throws NotFoundException {
        ModelEntity modelEntity = modelRepository.findById(modelId).orElseThrow(this::modelNotFoundException);

        switch (modelEntity.getModelType()) {
            case "CFA":
                runCfaAnalysis();
                break;
            case "SYSTEM":
                runStsAnalysis();
                break;
            case "XTA":
                runXtaAnalysis();
                break;
            case "XSTS":
                runXstsAnalysis();
                break;
            default:
                return;
        }
   }

   private void runCfaAnalysis() {}
   private void runStsAnalysis() {}
   private void runXtaAnalysis() {}
   private void runXstsAnalysis() {}

   private NotFoundException modelNotFoundException() {
        return new NotFoundException(0, "Model not found by id");
   }
}
