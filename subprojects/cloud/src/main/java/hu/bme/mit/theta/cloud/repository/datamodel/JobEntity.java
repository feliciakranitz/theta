package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "job")
@NamedQueries({
        @NamedQuery(name = "hu.bme.mit.theta.cloud.repository.datamodel.JobEntity.findAll",
                query = "select c from JobEntity c")
})
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "job_id")
    private UUID jobId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "model_id", updatable = false, nullable = false)
    private ModelEntity model;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "configuration_id", updatable = false, nullable = false)
    private ConfigurationEntity config;

    @OneToOne(targetEntity = AnalysisBenchmarkEntity.class, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    @JoinColumn(name = "benchmark_id")
    private AnalysisBenchmarkEntity benchmark;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, name = "is_safe")
    private boolean isSafe;

    @Column
    @Min(value = 0)
    @Max( value = 100)
    private int progress;

    @Column(name = "output_file")
    private String outputFile;

    @Column(name = "cex_file")
    private boolean cexFile;

    @Column(name = "notification_address")
    private String notificationAddress;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "creation_date")
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
