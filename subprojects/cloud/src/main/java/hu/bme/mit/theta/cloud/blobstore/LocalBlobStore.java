package hu.bme.mit.theta.cloud.blobstore;

import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

public class LocalBlobStore {

    private final String basePath;

    public LocalBlobStore(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public String saveModelBlob(InputStream inputStream, ModelEntity model) throws Exception {
        FileSystem fs = FileSystems.getDefault();

        Path outputDir = fs.getPath(basePath, "models");
        Files.createDirectories(outputDir);

        Path outputFilePath = fs.getPath(basePath, "models",model.getModelId() + "." + model.getModelType());
        Files.copy(inputStream, outputFilePath);

        return outputFilePath.toString();
    }

    public FileSystemResource getVisualizedBlob(UUID modelId, String fileFormat) {
        Path filePath = Paths.get(basePath + "/visualized/" + modelId + "." + fileFormat);
        System.out.println(filePath);
        return new FileSystemResource(filePath);
    }

    public FileSystemResource getModelBlob(ModelEntity modelEntity) {
        Path filePath = Paths.get(basePath + "/models/" + modelEntity.getModelId() + "." + modelEntity.getModelType());
        return new FileSystemResource(filePath);
    }
}
