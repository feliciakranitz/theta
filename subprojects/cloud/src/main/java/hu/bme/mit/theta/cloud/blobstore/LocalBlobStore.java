package hu.bme.mit.theta.cloud.blobstore;

import hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity;
import hu.bme.mit.theta.common.visualization.LineStyle;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocalBlobStore {

    private final String basePath = "/tmp/theta";
    private final FileSystem fs = FileSystems.getDefault();
    private final String[] folders = {"models", "visualized", "logs", "cexFiles"};

    public LocalBlobStore(){
        for (String folder: folders) {
            Path outputDir = fs.getPath(basePath, folder);
            try {
                Files.createDirectories(outputDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBasePath() {
        return basePath;
    }

    public String saveModelBlob(InputStream inputStream, ModelEntity model) throws Exception {

        Path outputFilePath = fs.getPath(basePath, "models", model.getModelId() + "." + model.getModelType());
        Files.copy(inputStream, outputFilePath);

        return outputFilePath.toString();
    }

    public FileSystemResource getModelBlob(ModelEntity modelEntity) {
        Path filePath = Paths.get(basePath + "/models/" + modelEntity.getModelId() + "." + modelEntity.getModelType());
        return new FileSystemResource(filePath);
    }

    public FileSystemResource getVisualizedBlob(UUID modelId, String fileFormat) {
        Path filePath = Paths.get(basePath + "/visualized/" + modelId + "." + fileFormat);
        System.out.println(filePath);
        return new FileSystemResource(filePath);
    }
}
