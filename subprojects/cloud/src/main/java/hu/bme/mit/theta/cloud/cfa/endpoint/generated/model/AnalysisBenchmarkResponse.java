package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AnalysisBenchmarkResponse
 */



public class AnalysisBenchmarkResponse  implements OneOfinlineResponse200 {
  @JsonProperty("analysisBenchmark")
  private AnalysisBenchmark analysisBenchmark = null;

  @JsonProperty("cex")
  private byte[] cex = null;

  @JsonProperty("visualized")
  private byte[] visualized = null;

  public AnalysisBenchmarkResponse analysisBenchmark(AnalysisBenchmark analysisBenchmark) {
    this.analysisBenchmark = analysisBenchmark;
    return this;
  }

  /**
   * Get analysisBenchmark
   * @return analysisBenchmark
   **/
  @Schema(description = "")
    public AnalysisBenchmark getAnalysisBenchmark() {
    return analysisBenchmark;
  }

  public void setAnalysisBenchmark(AnalysisBenchmark analysisBenchmark) {
    this.analysisBenchmark = analysisBenchmark;
  }

  public AnalysisBenchmarkResponse cex(byte[] cex) {
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

  public AnalysisBenchmarkResponse visualized(byte[] visualized) {
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
    AnalysisBenchmarkResponse analysisBenchmarkResponse = (AnalysisBenchmarkResponse) o;
    return Objects.equals(this.analysisBenchmark, analysisBenchmarkResponse.analysisBenchmark) &&
        Objects.equals(this.cex, analysisBenchmarkResponse.cex) &&
        Objects.equals(this.visualized, analysisBenchmarkResponse.visualized);
  }

  @Override
  public int hashCode() {
    return Objects.hash(analysisBenchmark, cex, visualized);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisBenchmarkResponse {\n");
    
    sb.append("    analysisBenchmark: ").append(toIndentedString(analysisBenchmark)).append("\n");
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
