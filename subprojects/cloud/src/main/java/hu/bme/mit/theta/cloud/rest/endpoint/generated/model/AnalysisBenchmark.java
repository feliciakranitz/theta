package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AnalysisBenchmark
 */



public class AnalysisBenchmark   {
  @JsonProperty("timeElapsed")
  private long timeElapsed;

  @JsonProperty("algorithmTimeMs")
  private long algorithmTimeMs;

  @JsonProperty("abstractorTimeMs")
  private long abstractorTimeMs;

  @JsonProperty("refinerTimeMs")
  private long refinerTimeMs;

  @JsonProperty("iterations")
  private long iterations;

  @JsonProperty("argSize")
  private long argSize;

  @JsonProperty("argDepth")
  private long argDepth;

  @JsonProperty("argMeanBranchingFactor")
  private long argMeanBranchingFactor;

  public AnalysisBenchmark timeElapsed(long timeElapsed) {
    this.timeElapsed = timeElapsed;
    return this;
  }

  /**
   * Get timeElapsed
   * @return timeElapsed
   **/
  @Schema(description = "")
    public long getTimeElapsed() {
    return timeElapsed;
  }

  public void setTimeElapsed(long timeElapsed) {
    this.timeElapsed = timeElapsed;
  }

  public AnalysisBenchmark algorithmTimeMs(long algorithmTimeMs) {
    this.algorithmTimeMs = algorithmTimeMs;
    return this;
  }

  /**
   * Get algorithmTimeMs
   * @return algorithmTimeMs
   **/
  @Schema(description = "")
    public long getAlgorithmTimeMs() {
    return algorithmTimeMs;
  }

  public void setAlgorithmTimeMs(long algorithmTimeMs) {
    this.algorithmTimeMs = algorithmTimeMs;
  }

  public AnalysisBenchmark abstractorTimeMs(long abstractorTimeMs) {
    this.abstractorTimeMs = abstractorTimeMs;
    return this;
  }

  /**
   * Get abstractorTimeMs
   * @return abstractorTimeMs
   **/
  @Schema(description = "")
    public long getAbstractorTimeMs() {
    return abstractorTimeMs;
  }

  public void setAbstractorTimeMs(long abstractorTimeMs) {
    this.abstractorTimeMs = abstractorTimeMs;
  }

  public AnalysisBenchmark refinerTimeMs(long refinerTimeMs) {
    this.refinerTimeMs = refinerTimeMs;
    return this;
  }

  /**
   * Get refinerTimeMs
   * @return refinerTimeMs
   **/
  @Schema(description = "")
    public long getRefinerTimeMs() {
    return refinerTimeMs;
  }

  public void setRefinerTimeMs(long refinerTimeMs) {
    this.refinerTimeMs = refinerTimeMs;
  }

  public AnalysisBenchmark iterations(long iterations) {
    this.iterations = iterations;
    return this;
  }

  /**
   * Get iterations
   * @return iterations
   **/
  @Schema(description = "")
    public long getIterations() {
    return iterations;
  }

  public void setIterations(long iterations) {
    this.iterations = iterations;
  }

  public AnalysisBenchmark argSize(long argSize) {
    this.argSize = argSize;
    return this;
  }

  /**
   * Get argSize
   * @return argSize
   **/
  @Schema(description = "")
    public long getArgSize() {
    return argSize;
  }

  public void setArgSize(long argSize) {
    this.argSize = argSize;
  }

  public AnalysisBenchmark argDepth(long argDepth) {
    this.argDepth = argDepth;
    return this;
  }

  /**
   * Get argDepth
   * @return argDepth
   **/
  @Schema(description = "")
    public long getArgDepth() {
    return argDepth;
  }

  public void setArgDepth(long argDepth) {
    this.argDepth = argDepth;
  }

  public AnalysisBenchmark argMeanBranchingFactor(long argMeanBranchingFactor) {
    this.argMeanBranchingFactor = argMeanBranchingFactor;
    return this;
  }

  /**
   * Get argMeanBranchingFactor
   * @return argMeanBranchingFactor
   **/
  @Schema(description = "")
    public long getArgMeanBranchingFactor() {
    return argMeanBranchingFactor;
  }

  public void setArgMeanBranchingFactor(long argMeanBranchingFactor) {
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
    return Objects.equals(this.timeElapsed, analysisBenchmark.timeElapsed) &&
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
    return Objects.hash(timeElapsed, algorithmTimeMs, abstractorTimeMs, refinerTimeMs, iterations, argSize, argDepth, argMeanBranchingFactor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisBenchmark {\n");
    
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
