package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * JobResponse
 */



public class JobResponse   {
  @JsonProperty("jobId")
  private UUID jobId = null;

  @JsonProperty("modelId")
  private UUID modelId = null;

  @JsonProperty("configId")
  private UUID configId = null;

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

  public JobResponse modelId(UUID modelId) {
    this.modelId = modelId;
    return this;
  }

  /**
   * Get modelId
   * @return modelId
   **/
  @Schema(description = "")
    public UUID getModelId() {
    return modelId;
  }

  public void setModelId(UUID modelId) {
    this.modelId = modelId;
  }

  public JobResponse configId(UUID configId) {
    this.configId = configId;
    return this;
  }

  /**
   * Get configId
   * @return configId
   **/
  @Schema(description = "")
    public UUID getConfigId() {
    return configId;
  }

  public void setConfigId(UUID configId) {
    this.configId = configId;
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
        Objects.equals(this.modelId, jobResponse.modelId) &&
        Objects.equals(this.configId, jobResponse.configId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, modelId, configId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobResponse {\n");
    
    sb.append("    jobId: ").append(toIndentedString(jobId)).append("\n");
    sb.append("    modelId: ").append(toIndentedString(modelId)).append("\n");
    sb.append("    configId: ").append(toIndentedString(configId)).append("\n");
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
