package hu.bme.mit.theta.cloud.blobstore;

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

    public String saveModelBlob(InputStream inputStream, String blobName) throws Exception {
        FileSystem fs = FileSystems.getDefault();

        Path outputDir = fs.getPath(basePath, "models");
        Files.createDirectories(outputDir);

        Path outputFilePath = fs.getPath(basePath, "models", blobName);
        Files.copy(inputStream, outputFilePath);

        return outputFilePath.toString();
    }

    public InputStream getBlob(UUID Id) {
        Path filePath = Paths.get(basePath + Id);
        System.out.println(filePath);
        File file = filePath.toFile();
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
