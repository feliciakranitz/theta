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
    private String encoding;

    @Column
    private Long maxEnum;

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
    private Boolean visualize;

    @Column
    private Boolean metrics;

    @Column
    private Boolean stacktrace;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private OffsetDateTime creationDate;
}
