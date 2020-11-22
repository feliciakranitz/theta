package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * CreateModelResponse
 */



public class CreateModelResponse {
  @JsonProperty("modelId")
  private UUID modelId = null;

  @JsonProperty("fileName")
  private String fileName = null;

  public CreateModelResponse modelId(UUID modelId) {
    this.modelId = modelId;
    return this;
  }

  /**
   * the generated id of the model file
   * @return modelId
   **/
  @Schema(required = true, description = "the generated id of the model file")
    public UUID getModelId() {
    return modelId;
  }

  public void setModelId(UUID modelId) {
    this.modelId = modelId;
  }

  public CreateModelResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * fileName of the newly uploaded CFA model
   * @return fileName
   **/
  @Schema(description = "fileName of the newly uploaded CFA model")
    public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateModelResponse createModelResponse = (CreateModelResponse) o;
    return Objects.equals(this.modelId, createModelResponse.modelId) &&
        Objects.equals(this.fileName, createModelResponse.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelId, fileName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModelResponse {\n");
    
    sb.append("    modelId: ").append(toIndentedString(modelId)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
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
