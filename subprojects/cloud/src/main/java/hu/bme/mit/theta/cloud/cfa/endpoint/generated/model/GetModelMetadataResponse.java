package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * GetModelMetadataResponse
 */



public class GetModelMetadataResponse   {
  @JsonProperty("modelId")
  private UUID modelId = null;

  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("modelType")
  private String modelType = null;

  public GetModelMetadataResponse modelId(UUID modelId) {
    this.modelId = modelId;
    return this;
  }

  /**
   * the generated id of the model file
   * @return modelId
   **/
  @Schema(description = "the generated id of the model file")
    public UUID getModelId() {
    return modelId;
  }

  public void setModelId(UUID modelId) {
    this.modelId = modelId;
  }

  public GetModelMetadataResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * fileName of the newly uploaded model
   * @return fileName
   **/
  @Schema(description = "fileName of the newly uploaded model")
    public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public GetModelMetadataResponse modelType(String modelType) {
    this.modelType = modelType;
    return this;
  }

  /**
   * type of the model
   * @return modelType
   **/
  @Schema(description = "type of the model")
    public String getModelType() {
    return modelType;
  }

  public void setModelType(String modelType) {
    this.modelType = modelType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetModelMetadataResponse getModelMetadataResponse = (GetModelMetadataResponse) o;
    return Objects.equals(this.modelId, getModelMetadataResponse.modelId) &&
        Objects.equals(this.fileName, getModelMetadataResponse.fileName) &&
        Objects.equals(this.modelType, getModelMetadataResponse.modelType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelId, fileName, modelType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetModelMetadataResponse {\n");
    
    sb.append("    modelId: ").append(toIndentedString(modelId)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    modelType: ").append(toIndentedString(modelType)).append("\n");
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
