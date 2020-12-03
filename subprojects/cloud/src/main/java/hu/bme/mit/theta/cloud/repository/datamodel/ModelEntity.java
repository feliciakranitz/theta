package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "model")
@NamedQueries({
        @NamedQuery(name = "hu.bme.mit.theta.cloud.repository.datamodel.ModelEntity.findAll",
                query = "select c from ModelEntity c")
})
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "model_id")
    private UUID modelId;

    @Column(nullable = false, name = "model_type")
    private String modelType;

    @Column(nullable = false, name = "file_name")
    private String fileName;

    @Column(nullable = false, name = "file_size")
    private Long fileSize;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "creation_date")
    private OffsetDateTime creationDate;

    public UUID getModelId() {
        return modelId;
    }

    public void setModelId(UUID modelId) {
        this.modelId = modelId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}