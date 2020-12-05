package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * JobResponse
 */


public class JobResponse   {
  @JsonProperty("jobId")
  private UUID jobId = null;

  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("isSafe")
  private Boolean isSafe = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("hasCex")
  private Boolean hasCex = null;

  @JsonProperty("analysisBenchmark")
  private AnalysisBenchmark analysisBenchmark = null;

  public JobResponse jobId(UUID jobId) {
    this.jobId = jobId;
    return this;
  }

  /**
   * Get jobId
   * @return jobId
   **/
  @Schema(description = "")
    public UUID getJobId() {
    return jobId;
  }

  public void setJobId(UUID jobId) {
    this.jobId = jobId;
  }

  public JobResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
   **/
  @Schema(description = "")
    public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public JobResponse isSafe(Boolean isSafe) {
    this.isSafe = isSafe;
    return this;
  }

  /**
   * Get isSafe
   * @return isSafe
   **/
  @Schema(description = "")
    public Boolean isIsSafe() {
    return isSafe;
  }

  public void setIsSafe(Boolean isSafe) {
    this.isSafe = isSafe;
  }

  public JobResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(description = "")
    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public JobResponse hasCex(Boolean hasCex) {
    this.hasCex = hasCex;
    return this;
  }

  /**
   * Get hasCex
   * @return hasCex
   **/
  @Schema(description = "")
    public Boolean isHasCex() {
    return hasCex;
  }

  public void setHasCex(Boolean hasCex) {
    this.hasCex = hasCex;
  }

  public JobResponse analysisBenchmark(AnalysisBenchmark analysisBenchmark) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JobResponse jobResponse = (JobResponse) o;
    return Objects.equals(this.jobId, jobResponse.jobId) &&
        Objects.equals(this.fileName, jobResponse.fileName) &&
        Objects.equals(this.isSafe, jobResponse.isSafe) &&
        Objects.equals(this.status, jobResponse.status) &&
        Objects.equals(this.hasCex, jobResponse.hasCex) &&
        Objects.equals(this.analysisBenchmark, jobResponse.analysisBenchmark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, fileName, isSafe, status, hasCex, analysisBenchmark);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobResponse {\n");
    
    sb.append("    jobId: ").append(toIndentedString(jobId)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    isSafe: ").append(toIndentedString(isSafe)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    hasCex: ").append(toIndentedString(hasCex)).append("\n");
    sb.append("    analysisBenchmark: ").append(toIndentedString(analysisBenchmark)).append("\n");
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
