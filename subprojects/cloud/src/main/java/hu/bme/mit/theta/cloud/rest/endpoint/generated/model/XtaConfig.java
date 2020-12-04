package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * XtaConfig
 */



public class XtaConfig   {
  /**
   * Refinement strategy for discrete variables
   */
  public enum DataStrategyEnum {
    NONE("NONE"),
    
    FWITP("FWITP"),
    
    BWITP("BWITP");

    private String value;

    DataStrategyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DataStrategyEnum fromValue(String text) {
      for (DataStrategyEnum b : DataStrategyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("dataStrategy")
  private DataStrategyEnum dataStrategy = DataStrategyEnum.NONE;

  /**
   * Refinement strategy for clock variables
   */
  public enum ClockStrategyEnum {
    LU("LU"),
    
    FWITP("FWITP"),
    
    BWITP("BWITP");

    private String value;

    ClockStrategyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ClockStrategyEnum fromValue(String text) {
      for (ClockStrategyEnum b : ClockStrategyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("clockStrategy")
  private ClockStrategyEnum clockStrategy = null;

  /**
   * Search strategy
   */
  public enum SearchStrategyEnum {
    BFS("BFS"),
    
    DFS("DFS"),
    
    RANDOM("RANDOM");

    private String value;

    SearchStrategyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SearchStrategyEnum fromValue(String text) {
      for (SearchStrategyEnum b : SearchStrategyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("searchStrategy")
  private SearchStrategyEnum searchStrategy = SearchStrategyEnum.BFS;

  @JsonProperty("visualize")
  private Boolean visualize = false;

  @JsonProperty("stacktrace")
  private Boolean stacktrace = false;

  public XtaConfig dataStrategy(DataStrategyEnum dataStrategy) {
    this.dataStrategy = dataStrategy;
    return this;
  }

  /**
   * Refinement strategy for discrete variables
   * @return dataStrategy
   **/
  @Schema(description = "Refinement strategy for discrete variables")
    public DataStrategyEnum getDataStrategy() {
    return dataStrategy;
  }

  public void setDataStrategy(DataStrategyEnum dataStrategy) {
    this.dataStrategy = dataStrategy;
  }

  public XtaConfig clockStrategy(ClockStrategyEnum clockStrategy) {
    this.clockStrategy = clockStrategy;
    return this;
  }

  /**
   * Refinement strategy for clock variables
   * @return clockStrategy
   **/
  @Schema(required = true, description = "Refinement strategy for clock variables")
    public ClockStrategyEnum getClockStrategy() {
    return clockStrategy;
  }

  public void setClockStrategy(ClockStrategyEnum clockStrategy) {
    this.clockStrategy = clockStrategy;
  }

  public XtaConfig searchStrategy(SearchStrategyEnum searchStrategy) {
    this.searchStrategy = searchStrategy;
    return this;
  }

  /**
   * Search strategy
   * @return searchStrategy
   **/
  @Schema(required = true, description = "Search strategy")
    public SearchStrategyEnum getSearchStrategy() {
    return searchStrategy;
  }

  public void setSearchStrategy(SearchStrategyEnum searchStrategy) {
    this.searchStrategy = searchStrategy;
  }

  public XtaConfig visualize(Boolean visualize) {
    this.visualize = visualize;
    return this;
  }

  /**
   * Should create counterexample
   * @return visualize
   **/
  @Schema(description = "Should create counterexample")
    public Boolean isVisualize() {
    return visualize;
  }

  public void setVisualize(Boolean visualize) {
    this.visualize = visualize;
  }

  public XtaConfig stacktrace(Boolean stacktrace) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XtaConfig xtaConfig = (XtaConfig) o;
    return Objects.equals(this.dataStrategy, xtaConfig.dataStrategy) &&
        Objects.equals(this.clockStrategy, xtaConfig.clockStrategy) &&
        Objects.equals(this.searchStrategy, xtaConfig.searchStrategy) &&
        Objects.equals(this.visualize, xtaConfig.visualize) &&
        Objects.equals(this.stacktrace, xtaConfig.stacktrace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataStrategy, clockStrategy, searchStrategy, visualize, stacktrace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class XtaConfig {\n");
    
    sb.append("    dataStrategy: ").append(toIndentedString(dataStrategy)).append("\n");
    sb.append("    clockStrategy: ").append(toIndentedString(clockStrategy)).append("\n");
    sb.append("    searchStrategy: ").append(toIndentedString(searchStrategy)).append("\n");
    sb.append("    visualize: ").append(toIndentedString(visualize)).append("\n");
    sb.append("    stacktrace: ").append(toIndentedString(stacktrace)).append("\n");
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
