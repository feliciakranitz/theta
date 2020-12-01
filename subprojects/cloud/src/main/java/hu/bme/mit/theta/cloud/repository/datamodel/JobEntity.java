package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "jobId")
    private UUID jobId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "modelId", updatable = false, nullable = false)
    private ModelEntity model;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "configurationId", updatable = false, nullable = false)
    private ConfigurationEntity config;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "benchmarkId")
    private AnalysisBenchmarkEntity benchmark;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private boolean isSafe;

    @Column
    @Min(value = 0)
    @Max( value = 100)
    private int progress;

    @Column
    private String outputFile;

    @Column
    private boolean cexFile;

    @Column
    private String notificationAddress;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private OffsetDateTime creationDate;

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public ConfigurationEntity getConfig() {
        return config;
    }

    public void setConfig(ConfigurationEntity config) {
        this.config = config;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public boolean isCexFile() {
        return cexFile;
    }

    public void setCexFile(boolean cexFile) {
        this.cexFile = cexFile;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getNotificationAddress() {
        return notificationAddress;
    }

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getJobId() {
        return jobId;
    }

    public AnalysisBenchmarkEntity getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(AnalysisBenchmarkEntity benchmark) {
        this.benchmark = benchmark;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }
}
