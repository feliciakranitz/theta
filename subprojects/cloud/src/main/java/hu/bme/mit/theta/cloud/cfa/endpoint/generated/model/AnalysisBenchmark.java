package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * AnalysisBenchmark
 */



public class AnalysisBenchmark   {
  @JsonProperty("safe")
  private Boolean safe = null;

  @JsonProperty("timeElapsed")
  private BigDecimal timeElapsed = null;

  @JsonProperty("algorithmTimeMs")
  private BigDecimal algorithmTimeMs = null;

  @JsonProperty("abstractorTimeMs")
  private BigDecimal abstractorTimeMs = null;

  @JsonProperty("refinerTimeMs")
  private BigDecimal refinerTimeMs = null;

  @JsonProperty("iterations")
  private BigDecimal iterations = null;

  @JsonProperty("argSize")
  private BigDecimal argSize = null;

  @JsonProperty("argDepth")
  private BigDecimal argDepth = null;

  @JsonProperty("argMeanBranchingFactor")
  private BigDecimal argMeanBranchingFactor = null;

  public AnalysisBenchmark safe(Boolean safe) {
    this.safe = safe;
    return this;
  }

  /**
   * Get safe
   * @return safe
   **/
  @Schema(description = "")
    public Boolean isSafe() {
    return safe;
  }

  public void setSafe(Boolean safe) {
    this.safe = safe;
  }

  public AnalysisBenchmark timeElapsed(BigDecimal timeElapsed) {
    this.timeElapsed = timeElapsed;
    return this;
  }

  /**
   * Get timeElapsed
   * @return timeElapsed
   **/
  @Schema(description = "")
    public BigDecimal getTimeElapsed() {
    return timeElapsed;
  }

  public void setTimeElapsed(BigDecimal timeElapsed) {
    this.timeElapsed = timeElapsed;
  }

  public AnalysisBenchmark algorithmTimeMs(BigDecimal algorithmTimeMs) {
    this.algorithmTimeMs = algorithmTimeMs;
    return this;
  }

  /**
   * Get algorithmTimeMs
   * @return algorithmTimeMs
   **/
  @Schema(description = "")
    public BigDecimal getAlgorithmTimeMs() {
    return algorithmTimeMs;
  }

  public void setAlgorithmTimeMs(BigDecimal algorithmTimeMs) {
    this.algorithmTimeMs = algorithmTimeMs;
  }

  public AnalysisBenchmark abstractorTimeMs(BigDecimal abstractorTimeMs) {
    this.abstractorTimeMs = abstractorTimeMs;
    return this;
  }

  /**
   * Get abstractorTimeMs
   * @return abstractorTimeMs
   **/
  @Schema(description = "")
    public BigDecimal getAbstractorTimeMs() {
    return abstractorTimeMs;
  }

  public void setAbstractorTimeMs(BigDecimal abstractorTimeMs) {
    this.abstractorTimeMs = abstractorTimeMs;
  }

  public AnalysisBenchmark refinerTimeMs(BigDecimal refinerTimeMs) {
    this.refinerTimeMs = refinerTimeMs;
    return this;
  }

  /**
   * Get refinerTimeMs
   * @return refinerTimeMs
   **/
  @Schema(description = "")
    public BigDecimal getRefinerTimeMs() {
    return refinerTimeMs;
  }

  public void setRefinerTimeMs(BigDecimal refinerTimeMs) {
    this.refinerTimeMs = refinerTimeMs;
  }

  public AnalysisBenchmark iterations(BigDecimal iterations) {
    this.iterations = iterations;
    return this;
  }

  /**
   * Get iterations
   * @return iterations
   **/
  @Schema(description = "")
    public BigDecimal getIterations() {
    return iterations;
  }

  public void setIterations(BigDecimal iterations) {
    this.iterations = iterations;
  }

  public AnalysisBenchmark argSize(BigDecimal argSize) {
    this.argSize = argSize;
    return this;
  }

  /**
   * Get argSize
   * @return argSize
   **/
  @Schema(description = "")
    public BigDecimal getArgSize() {
    return argSize;
  }

  public void setArgSize(BigDecimal argSize) {
    this.argSize = argSize;
  }

  public AnalysisBenchmark argDepth(BigDecimal argDepth) {
    this.argDepth = argDepth;
    return this;
  }

  /**
   * Get argDepth
   * @return argDepth
   **/
  @Schema(description = "")
    public BigDecimal getArgDepth() {
    return argDepth;
  }

  public void setArgDepth(BigDecimal argDepth) {
    this.argDepth = argDepth;
  }

  public AnalysisBenchmark argMeanBranchingFactor(BigDecimal argMeanBranchingFactor) {
    this.argMeanBranchingFactor = argMeanBranchingFactor;
    return this;
  }

  /**
   * Get argMeanBranchingFactor
   * @return argMeanBranchingFactor
   **/
  @Schema(description = "")
    public BigDecimal getArgMeanBranchingFactor() {
    return argMeanBranchingFactor;
  }

  public void setArgMeanBranchingFactor(BigDecimal argMeanBranchingFactor) {
    this.argMeanBranchingFactor = argMeanBranchingFactor;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisBenchmark analysisBenchmark = (AnalysisBenchmark) o;
    return Objects.equals(this.safe, analysisBenchmark.safe) &&
        Objects.equals(this.timeElapsed, analysisBenchmark.timeElapsed) &&
        Objects.equals(this.algorithmTimeMs, analysisBenchmark.algorithmTimeMs) &&
        Objects.equals(this.abstractorTimeMs, analysisBenchmark.abstractorTimeMs) &&
        Objects.equals(this.refinerTimeMs, analysisBenchmark.refinerTimeMs) &&
        Objects.equals(this.iterations, analysisBenchmark.iterations) &&
        Objects.equals(this.argSize, analysisBenchmark.argSize) &&
        Objects.equals(this.argDepth, analysisBenchmark.argDepth) &&
        Objects.equals(this.argMeanBranchingFactor, analysisBenchmark.argMeanBranchingFactor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(safe, timeElapsed, algorithmTimeMs, abstractorTimeMs, refinerTimeMs, iterations, argSize, argDepth, argMeanBranchingFactor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisBenchmark {\n");
    
    sb.append("    safe: ").append(toIndentedString(safe)).append("\n");
    sb.append("    timeElapsed: ").append(toIndentedString(timeElapsed)).append("\n");
    sb.append("    algorithmTimeMs: ").append(toIndentedString(algorithmTimeMs)).append("\n");
    sb.append("    abstractorTimeMs: ").append(toIndentedString(abstractorTimeMs)).append("\n");
    sb.append("    refinerTimeMs: ").append(toIndentedString(refinerTimeMs)).append("\n");
    sb.append("    iterations: ").append(toIndentedString(iterations)).append("\n");
    sb.append("    argSize: ").append(toIndentedString(argSize)).append("\n");
    sb.append("    argDepth: ").append(toIndentedString(argDepth)).append("\n");
    sb.append("    argMeanBranchingFactor: ").append(toIndentedString(argMeanBranchingFactor)).append("\n");
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
