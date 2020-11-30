package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AnalysisLog
 */



public class AnalysisLog  implements OneOfinlineResponse200 {
  @JsonProperty("safe")
  private String safe = null;

  @JsonProperty("log")
  private byte[] log = null;

  @JsonProperty("cex")
  private byte[] cex = null;

  @JsonProperty("visualized")
  private byte[] visualized = null;

  public AnalysisLog safe(String safe) {
    this.safe = safe;
    return this;
  }

  /**
   * Get safe
   * @return safe
   **/
  @Schema(description = "")
    public String getSafe() {
    return safe;
  }

  public void setSafe(String safe) {
    this.safe = safe;
  }

  public AnalysisLog log(byte[] log) {
    this.log = log;
    return this;
  }

  /**
   * Log file
   * @return log
   **/
  @Schema(description = "Log file")
    public byte[] getLog() {
    return log;
  }

  public void setLog(byte[] log) {
    this.log = log;
  }

  public AnalysisLog cex(byte[] cex) {
    this.cex = cex;
    return this;
  }

  /**
   * Counterexample file
   * @return cex
   **/
  @Schema(description = "Counterexample file")
    public byte[] getCex() {
    return cex;
  }

  public void setCex(byte[] cex) {
    this.cex = cex;
  }

  public AnalysisLog visualized(byte[] visualized) {
    this.visualized = visualized;
    return this;
  }

  /**
   * Visualized model file
   * @return visualized
   **/
  @Schema(description = "Visualized model file")
    public byte[] getVisualized() {
    return visualized;
  }

  public void setVisualized(byte[] visualized) {
    this.visualized = visualized;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisLog analysisLog = (AnalysisLog) o;
    return Objects.equals(this.safe, analysisLog.safe) &&
        Objects.equals(this.log, analysisLog.log) &&
        Objects.equals(this.cex, analysisLog.cex) &&
        Objects.equals(this.visualized, analysisLog.visualized);
  }

  @Override
  public int hashCode() {
    return Objects.hash(safe, log, cex, visualized);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisLog {\n");
    
    sb.append("    safe: ").append(toIndentedString(safe)).append("\n");
    sb.append("    log: ").append(toIndentedString(log)).append("\n");
    sb.append("    cex: ").append(toIndentedString(cex)).append("\n");
    sb.append("    visualized: ").append(toIndentedString(visualized)).append("\n");
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
