package hu.bme.mit.theta.cloud.blobstore;

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

    public String saveBlob(InputStream inputStream, UUID jobId, String blobName) throws Exception {
        FileSystem fs = FileSystems.getDefault();

        Path outputDir = fs.getPath(basePath, jobId.toString());
        Files.createDirectories(outputDir);

        Path outputFilePath = fs.getPath(basePath, jobId.toString(), blobName);
        Files.copy(inputStream, outputFilePath);

        return outputFilePath.toString();
    }

    public InputStream getBlob(UUID Id) {
        Path filePath = Paths.get(basePath + Id);
        File file = filePath.toFile();
        try {
            InputStream fileStream = new FileInputStream(file);
            return fileStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
