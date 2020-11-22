package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(updatable = false, columnDefinition = "char(36)")
    private UUID jobId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "modelId", updatable = false, nullable = false)
    private ModelEntity model;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "configurationId", updatable = false, nullable = false)
    private ConfigurationEntity config;

    @Column(nullable = false)
    private String outputFile;

    @Column
    private String cexFile;

    @Column
    private String visualizedModel;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private OffsetDateTime creationDate;
}
