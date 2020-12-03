package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AnalysisConfig
 */



public class AnalysisConfig   {
  /**
   * Abstract domain
   */
  public enum DomainEnum {
    EXPL("EXPL"),
    
    PRED_BOOL("PRED_BOOL"),
    
    PRED_CART("PRED_CART"),
    
    PRED_SPLIT("PRED_SPLIT");

    private String value;

    DomainEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DomainEnum fromValue(String text) {
      for (DomainEnum b : DomainEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("domain")
  private DomainEnum domain = DomainEnum.PRED_CART;

  /**
   * Refinement strategy
   */
  public enum RefinementEnum {
    FW_BIN_ITP("FW_BIN_ITP"),
    
    BW_BIN_ITP("BW_BIN_ITP"),
    
    SEQ_ITP("SEQ_ITP"),
    
    MULTI_SEQ("MULTI_SEQ"),
    
    UNSAT_CORE("UNSAT_CORE");

    private String value;

    RefinementEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RefinementEnum fromValue(String text) {
      for (RefinementEnum b : RefinementEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("refinement")
  private RefinementEnum refinement = RefinementEnum.SEQ_ITP;

  /**
   * Search strategy
   */
  public enum SearchEnum {
    BFS("BFS"),
    
    DFS("DFS"),
    
    ERR("ERR");

    private String value;

    SearchEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SearchEnum fromValue(String text) {
      for (SearchEnum b : SearchEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("search")
  private SearchEnum search = SearchEnum.BFS;

  /**
   * Predicate splitting (for predicate abstraction)
   */
  public enum PredSplitEnum {
    WHOLE("WHOLE"),
    
    CONJUNCTS("CONJUNCTS"),
    
    ATOMS("ATOMS");

    private String value;

    PredSplitEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PredSplitEnum fromValue(String text) {
      for (PredSplitEnum b : PredSplitEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("predSplit")
  private PredSplitEnum predSplit = PredSplitEnum.WHOLE;

  /**
   * Precision granularity
   */
  public enum PrecGranularityEnum {
    GLOBAL("GLOBAL"),
    
    LOCAL("LOCAL");

    private String value;

    PrecGranularityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PrecGranularityEnum fromValue(String text) {
      for (PrecGranularityEnum b : PrecGranularityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("precGranularity")
  private PrecGranularityEnum precGranularity = PrecGranularityEnum.GLOBAL;

  /**
   * Block encoding
   */
  public enum EncodingEnum {
    SBE("SBE"),
    
    LBE("LBE");

    private String value;

    EncodingEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EncodingEnum fromValue(String text) {
      for (EncodingEnum b : EncodingEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("encoding")
  private EncodingEnum encoding = EncodingEnum.LBE;

  /**
   * Strategy for pruning the ARG after refinement
   */
  public enum PruneStrategyEnum {
    LAZY("LAZY"),
    
    FULL("FULL");

    private String value;

    PruneStrategyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PruneStrategyEnum fromValue(String text) {
      for (PruneStrategyEnum b : PruneStrategyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("pruneStrategy")
  private PruneStrategyEnum pruneStrategy = PruneStrategyEnum.LAZY;

  /**
   * Initial precision of abstraction
   */
  public enum InitPrecEnum {
    EMPTY("EMPTY"),
    
    ALLVARS("ALLVARS");

    private String value;

    InitPrecEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InitPrecEnum fromValue(String text) {
      for (InitPrecEnum b : InitPrecEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("initPrec")
  private InitPrecEnum initPrec = InitPrecEnum.EMPTY;

  @JsonProperty("maxEnum")
  private Integer maxEnum = 0;

  @JsonProperty("notificationAddress")
  private String notificationAddress;

  @Schema(description = "Email address of the analysis creator")
  public String getNotificationAddress() {
    return notificationAddress;
  }

  public void setNotificationAddress(String notificationAddress) {
    this.notificationAddress = notificationAddress;
  }

  @JsonProperty("cexFile")
  private Boolean cexFile = false;

  @JsonProperty("benchmark")
  private Boolean benchmark = false;

  @JsonProperty("stacktrace")
  private Boolean stacktrace = false;

  /**
   * Detailedness of logging
   */
  public enum LogLevelEnum {
    RESULT("RESULT"),
    
    MAINSTEP("MAINSTEP"),
    
    SUBSTEP("SUBSTEP"),
    
    INFO("INFO"),
    
    DETAIL("DETAIL"),
    
    VERBOSE("VERBOSE");

    private String value;

    LogLevelEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LogLevelEnum fromValue(String text) {
      for (LogLevelEnum b : LogLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("logLevel")
  private LogLevelEnum logLevel = LogLevelEnum.SUBSTEP;

  public AnalysisConfig domain(DomainEnum domain) {
    this.domain = domain;
    return this;
  }

  /**
   * Abstract domain
   * @return domain
   **/
  @Schema(description = "Abstract domain")
    public DomainEnum getDomain() {
    return domain;
  }

  public void setDomain(DomainEnum domain) {
    this.domain = domain;
  }

  public AnalysisConfig refinement(RefinementEnum refinement) {
    this.refinement = refinement;
    return this;
  }

  /**
   * Refinement strategy
   * @return refinement
   **/
  @Schema(description = "Refinement strategy")
    public RefinementEnum getRefinement() {
    return refinement;
  }

  public void setRefinement(RefinementEnum refinement) {
    this.refinement = refinement;
  }

  public AnalysisConfig search(SearchEnum search) {
    this.search = search;
    return this;
  }

  /**
   * Search strategy
   * @return search
   **/
  @Schema(description = "Search strategy")
    public SearchEnum getSearch() {
    return search;
  }

  public void setSearch(SearchEnum search) {
    this.search = search;
  }

  public AnalysisConfig predSplit(PredSplitEnum predSplit) {
    this.predSplit = predSplit;
    return this;
  }

  /**
   * Predicate splitting (for predicate abstraction)
   * @return predSplit
   **/
  @Schema(description = "Predicate splitting (for predicate abstraction)")
    public PredSplitEnum getPredSplit() {
    return predSplit;
  }

  public void setPredSplit(PredSplitEnum predSplit) {
    this.predSplit = predSplit;
  }

  public AnalysisConfig precGranularity(PrecGranularityEnum precGranularity) {
    this.precGranularity = precGranularity;
    return this;
  }

  /**
   * Precision granularity
   * @return precGranularity
   **/
  @Schema(description = "Precision granularity")
    public PrecGranularityEnum getPrecGranularity() {
    return precGranularity;
  }

  public void setPrecGranularity(PrecGranularityEnum precGranularity) {
    this.precGranularity = precGranularity;
  }

  public AnalysisConfig encoding(EncodingEnum encoding) {
    this.encoding = encoding;
    return this;
  }

  /**
   * Block encoding
   * @return encoding
   **/
  @Schema(description = "Block encoding")
    public EncodingEnum getEncoding() {
    return encoding;
  }

  public void setEncoding(EncodingEnum encoding) {
    this.encoding = encoding;
  }

  public AnalysisConfig pruneStrategy(PruneStrategyEnum pruneStrategy) {
    this.pruneStrategy = pruneStrategy;
    return this;
  }

  /**
   * Strategy for pruning the ARG after refinement
   * @return pruneStrategy
   **/
  @Schema(description = "Strategy for pruning the ARG after refinement")
    public PruneStrategyEnum getPruneStrategy() {
    return pruneStrategy;
  }

  public void setPruneStrategy(PruneStrategyEnum pruneStrategy) {
    this.pruneStrategy = pruneStrategy;
  }

  public AnalysisConfig initPrec(InitPrecEnum initPrec) {
    this.initPrec = initPrec;
    return this;
  }

  /**
   * Initial precision of abstraction
   * @return initPrec
   **/
  @Schema(description = "Initial precision of abstraction")
    public InitPrecEnum getInitPrec() {
    return initPrec;
  }

  public void setInitPrec(InitPrecEnum initPrec) {
    this.initPrec = initPrec;
  }

  public AnalysisConfig maxEnum(Integer maxEnum) {
    this.maxEnum = maxEnum;
    return this;
  }

  /**
   * Maximal number of explicitly enumerated successors ( zero is  unlimited)
   * @return maxEnum
   **/
  @Schema(description = "Maximal number of explicitly enumerated successors ( zero is  unlimited)")
    public Integer getMaxEnum() {
    return maxEnum;
  }

  public void setMaxEnum(Integer maxEnum) {
    this.maxEnum = maxEnum;
  }

  public AnalysisConfig cexFile(Boolean cexFile) {
    this.cexFile = cexFile;
    return this;
  }

  /**
   * Should create counterexample
   * @return cexFile
   **/
  @Schema(description = "Should create counterexample")
    public Boolean isCexFile() {
    return cexFile;
  }

  public void setCexFile(Boolean cexFile) {
    this.cexFile = cexFile;
  }

  public AnalysisConfig benchmark(Boolean benchmark) {
    this.benchmark = benchmark;
    return this;
  }

  /**
   * Benchmark mode (only print metrics)
   * @return benchmark
   **/
  @Schema(description = "Benchmark mode (only print metrics)")
    public Boolean isBenchmark() {
    return benchmark;
  }

  public void setBenchmark(Boolean benchmark) {
    this.benchmark = benchmark;
  }

  public AnalysisConfig stacktrace(Boolean stacktrace) {
    this.stacktrace = stacktrace;
    return this;
  }

  /**
   * Print full stack trace in case of exception
   * @return stacktrace
   **/
  @Schema(description = "Print full stack trace in case of exception")
    public Boolean isStacktrace() {
    return stacktrace;
  }

  public void setStacktrace(Boolean stacktrace) {
    this.stacktrace = stacktrace;
  }

  public AnalysisConfig logLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
    return this;
  }

  /**
   * Detailedness of logging
   * @return logLevel
   **/
  @Schema(description = "Detailedness of logging")
    public LogLevelEnum getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisConfig analysisConfig = (AnalysisConfig) o;
    return Objects.equals(this.domain, analysisConfig.domain) &&
        Objects.equals(this.refinement, analysisConfig.refinement) &&
        Objects.equals(this.search, analysisConfig.search) &&
        Objects.equals(this.predSplit, analysisConfig.predSplit) &&
        Objects.equals(this.precGranularity, analysisConfig.precGranularity) &&
        Objects.equals(this.encoding, analysisConfig.encoding) &&
        Objects.equals(this.pruneStrategy, analysisConfig.pruneStrategy) &&
        Objects.equals(this.initPrec, analysisConfig.initPrec) &&
        Objects.equals(this.maxEnum, analysisConfig.maxEnum) &&
        Objects.equals(this.cexFile, analysisConfig.cexFile) &&
        Objects.equals(this.benchmark, analysisConfig.benchmark) &&
        Objects.equals(this.stacktrace, analysisConfig.stacktrace) &&
        Objects.equals(this.logLevel, analysisConfig.logLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domain, refinement, search, predSplit, precGranularity, encoding, pruneStrategy, initPrec, maxEnum, cexFile, benchmark, stacktrace, logLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisConfig {\n");
    
    sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
    sb.append("    refinement: ").append(toIndentedString(refinement)).append("\n");
    sb.append("    search: ").append(toIndentedString(search)).append("\n");
    sb.append("    predSplit: ").append(toIndentedString(predSplit)).append("\n");
    sb.append("    precGranularity: ").append(toIndentedString(precGranularity)).append("\n");
    sb.append("    encoding: ").append(toIndentedString(encoding)).append("\n");
    sb.append("    pruneStrategy: ").append(toIndentedString(pruneStrategy)).append("\n");
    sb.append("    initPrec: ").append(toIndentedString(initPrec)).append("\n");
    sb.append("    maxEnum: ").append(toIndentedString(maxEnum)).append("\n");
    sb.append("    cexFile: ").append(toIndentedString(cexFile)).append("\n");
    sb.append("    benchmark: ").append(toIndentedString(benchmark)).append("\n");
    sb.append("    stacktrace: ").append(toIndentedString(stacktrace)).append("\n");
    sb.append("    logLevel: ").append(toIndentedString(logLevel)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
