package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Embeddable
@Entity
@Table(name = "analysis_benchmark")
@NamedQueries({
        @NamedQuery(name = "hu.bme.mit.theta.cloud.repository.datamodel.AnalysisBenchmarkEntity.findAll",
                query = "select c from AnalysisBenchmarkEntity c")
})
public class AnalysisBenchmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "benchmark_id")
    private UUID benchmarkId;

    @Column(name = "time_elapsed")
    private long timeElapsed;

    @Column(name = "algorithm_time_ms")
    private long algorithmTimeMs;

    @Column(name = "abstractor_time_ms")
    private long abstractorTimeMs;

    @Column(name = "refiner_time_ms")
    private long refinerTimeMs;

    @Column
    private int iterations;

    @Column(name = "arg_size")
    private long argSize;

    @Column(name = "arg_depth")
    private int argDepth;

    @Column(name = "arg_mean_branching_factor")
    private double argMeanBranchingFactor;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "creation_date")
    private OffsetDateTime creationDate;


    public UUID getBenchmarkId() {
        return benchmarkId;
    }

    public void setBenchmarkId(UUID benchmarkId) {
        this.benchmarkId = benchmarkId;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public long getAlgorithmTimeMs() {
        return algorithmTimeMs;
    }

    public void setAlgorithmTimeMs(long algorithmTimeMs) {
        this.algorithmTimeMs = algorithmTimeMs;
    }

    public long getAbstractorTimeMs() {
        return abstractorTimeMs;
    }

    public void setAbstractorTimeMs(long abstractorTimeMs) {
        this.abstractorTimeMs = abstractorTimeMs;
    }

    public long getRefinerTimeMs() {
        return refinerTimeMs;
    }

    public void setRefinerTimeMs(long refinerTimeMs) {
        this.refinerTimeMs = refinerTimeMs;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public long getArgSize() {
        return argSize;
    }

    public void setArgSize(long argSize) {
        this.argSize = argSize;
    }

    public int getArgDepth() {
        return argDepth;
    }

    public void setArgDepth(int argDepth) {
        this.argDepth = argDepth;
    }

    public double getArgMeanBranchingFactor() {
        return argMeanBranchingFactor;
    }

    public void setArgMeanBranchingFactor(double argMeanBranchingFactor) {
        this.argMeanBranchingFactor = argMeanBranchingFactor;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
