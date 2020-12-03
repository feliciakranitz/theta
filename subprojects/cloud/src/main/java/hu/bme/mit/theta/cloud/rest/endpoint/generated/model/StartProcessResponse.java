package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * StartProcessResponse
 */



public class StartProcessResponse   {
  @JsonProperty("processId")
  private UUID processId = null;

  public StartProcessResponse processId(UUID processId) {
    this.processId = processId;
    return this;
  }

  /**
   * the generated id of process
   * @return processId
   **/
  @Schema(required = true, description = "the generated id of process")
    public UUID getProcessId() {
    return processId;
  }

  public void setProcessId(UUID processId) {
    this.processId = processId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartProcessResponse startProcessResponse = (StartProcessResponse) o;
    return Objects.equals(this.processId, startProcessResponse.processId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartProcessResponse {\n");
    
    sb.append("    processId: ").append(toIndentedString(processId)).append("\n");
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
