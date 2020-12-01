package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "configurationId")
    private UUID configurationId;

    @Column
    private String domainName;

    @Column
    private String refinement;

    @Column
    private String predSplit;

    @Column
    private String errorLoc;

    @Column
    private String precGranularity;

    @Column
    private String search;

    @Column
    private String encoding;

    @Column
    private Integer maxEnum;

    @Column
    private String initPrec;

    @Column
    private String pruneStrategy;

    @Column
    private String logLevel;

    @Column
    private Boolean benchmarkMode;

    @Column
    private Boolean cexFile;

    @Column
    private Boolean stacktrace;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private OffsetDateTime creationDate;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getRefinement() {
        return refinement;
    }

    public void setRefinement(String refinement) {
        this.refinement = refinement;
    }

    public String getPredSplit() {
        return predSplit;
    }

    public void setPredSplit(String predSplit) {
        this.predSplit = predSplit;
    }

    public String getErrorLoc() {
        return errorLoc;
    }

    public void setErrorLoc(String errorLoc) {
        this.errorLoc = errorLoc;
    }

    public String getPrecGranularity() {
        return precGranularity;
    }

    public void setPrecGranularity(String precGranularity) {
        this.precGranularity = precGranularity;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getInitPrec() {
        return initPrec;
    }

    public void setInitPrec(String initPrec) {
        this.initPrec = initPrec;
    }

    public String getPruneStrategy() {
        return pruneStrategy;
    }

    public void setPruneStrategy(String pruneStrategy) {
        this.pruneStrategy = pruneStrategy;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Boolean getBenchmarkMode() {
        return benchmarkMode;
    }

    public void setBenchmarkMode(Boolean benchmarkMode) {
        this.benchmarkMode = benchmarkMode;
    }

    public Boolean getCexFile() {
        return cexFile;
    }

    public void setCexFile(Boolean cexFile) {
        this.cexFile = cexFile;
    }

    public Boolean getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(Boolean stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getMaxEnum() {
        return maxEnum;
    }

    public void setMaxEnum(Integer maxEnum) {
        this.maxEnum = maxEnum;
    }
}
